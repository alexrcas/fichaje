package net.avantic.domain.model.dto.factory;

import net.avantic.domain.model.*;
import net.avantic.domain.model.dto.FichajeOrdenJornadaSpecification;
import net.avantic.domain.model.dto.JornadaDto;
import net.avantic.domain.service.FichajeService;
import net.avantic.domain.service.ValidarJornadaService;
import net.avantic.utils.FichajeVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class JornadaEmpleadoDtoFactory {

    private final FichajeService fichajeService;
    private final ValidarJornadaService validarJornadaService;

    @Autowired
    public JornadaEmpleadoDtoFactory(FichajeService fichajeService,
                                     ValidarJornadaService validarJornadaService) {
        this.fichajeService = fichajeService;
        this.validarJornadaService = validarJornadaService;
    }

    public JornadaDto newDto(JornadaEmpleado jornadaEmpleado) {

        validarJornadaService.validar(jornadaEmpleado);

        if (!jornadaEmpleado.isValidada()) {
            return new JornadaDto(jornadaEmpleado.getId(), "E");
        }

        CalcularJornadaVisitor visitor = new CalcularJornadaVisitor();

        fichajeService.listFichajesOrdenJornada(jornadaEmpleado).stream()
                        .forEach(visitor::popular);

        return new JornadaDto(jornadaEmpleado.getId(), String.valueOf(visitor.getDuracionJornada()));
    }

    public static double calcularTiempoTranscurrido(LocalDateTime inicio, LocalDateTime fin) {
        Duration duracion = Duration.between(inicio, fin);
        double horasTranscurridas = duracion.toHours();
        long minutosExtras = duracion.minusHours((long) horasTranscurridas).toMinutes();
        double fraccionHora = minutosExtras / 60.0;
        return horasTranscurridas + fraccionHora;
    }


    class CalcularJornadaVisitor implements FichajeVisitor {

        private LocalDateTime entradaJornada;
        private LocalDateTime salidaJornada;

        private LocalDateTime entradaDesayuno;
        private LocalDateTime salidaDesayuno;

        private LocalDateTime entradaComida;
        private LocalDateTime salidaComida;

        private LocalDateTime hora;

        void popular(FichajeOrdenJornadaSpecification fichajeOrdenJornadaSpecification) {
            this.hora = fichajeOrdenJornadaSpecification.getHoraFichaje();
            fichajeOrdenJornadaSpecification.getFichaje().accept(this);
        }

        double getDuracionJornada() {
            //todo arodriguez: restar comida
            double tiempoJornada = calcularTiempoTranscurrido(entradaJornada, salidaJornada);

            //solo compruebo uno de los dos tiempos porque si llegamos a este punto de calcular la jornada es porque el validador la ha dado por buena, por tanto
            //sé que su contraparte va a existir
            double tiempoDesayuno = entradaDesayuno == null ? 0.0 : calcularTiempoTranscurrido(salidaDesayuno, entradaDesayuno);
            return tiempoJornada - tiempoDesayuno;
        }

        @Override
        public void visit(EntradaJornada entradaJornada) {
            this.entradaJornada = hora;
        }

        @Override
        public void visit(SalidaJornada salidaJornada) {
            this.salidaJornada = hora;
        }

        @Override
        public void visit(EntradaDesayuno entradaDesayuno) {
            this.entradaDesayuno = hora;
        }

        @Override
        public void visit(SalidaDesayuno salidaDesayuno) {
            this.salidaDesayuno = hora;
        }

        @Override
        public void visit(EntradaComida entradaComida) {
            this.entradaComida = hora;
        }

        @Override
        public void visit(SalidaComida salidaComida) {
            this.salidaComida = hora;
        }
    }


    class InformacionFichaje {

        private final Fichaje fichaje;
        private final LocalDateTime hora;

        public InformacionFichaje(Fichaje fichaje, LocalDateTime hora) {
            this.fichaje = fichaje;
            this.hora = hora;
        }

        public Fichaje getFichaje() {
            return fichaje;
        }

        public LocalDateTime getHora() {
            return hora;
        }
    }
}
