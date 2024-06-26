package net.avantic.domain.service.impl;

import net.avantic.domain.dao.ExtemporaneoRepository;
import net.avantic.domain.dao.FichajeRepository;
import net.avantic.domain.dao.JornadaEmpleadoRepository;
import net.avantic.domain.model.*;
import net.avantic.domain.model.dto.FichajeOrdenJornadaSpecification;
import net.avantic.domain.service.FichajeService;
import net.avantic.domain.service.JornadaService;
import net.avantic.utils.FichajeVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FichajeServiceImpl implements FichajeService {

    private final JornadaService jornadaService;
    private final FichajeRepository fichajeRepository;
    private final JornadaEmpleadoRepository jornadaEmpleadoRepository;
    private final ExtemporaneoRepository extemporaneoRepository;

    @Autowired
    public FichajeServiceImpl(JornadaService jornadaService,
                              FichajeRepository fichajeRepository,
                              JornadaEmpleadoRepository jornadaEmpleadoRepository,
                              ExtemporaneoRepository extemporaneoRepository) {
        this.jornadaService = jornadaService;
        this.fichajeRepository = fichajeRepository;
        this.jornadaEmpleadoRepository = jornadaEmpleadoRepository;
        this.extemporaneoRepository = extemporaneoRepository;
    }

    @Override
    public void fichar(Empleado empleado, Dia dia, EnumTipoFichaje tipoFichaje) {

        JornadaEmpleado jornadaEmpleado = jornadaService.findByDiaAndEmpleado(dia, empleado)
                .orElseGet(() -> jornadaService.createJornadaEmpleado(dia, empleado));

        Fichaje fichaje = createFichaje(jornadaEmpleado, tipoFichaje);

        jornadaEmpleado.setValidada(false);
        jornadaEmpleadoRepository.save(jornadaEmpleado);
        fichajeRepository.save(fichaje);
    }

    @Override
    public void ficharExtemporaneo(Empleado empleado, Dia dia, EnumTipoFichaje tipoFichaje, LocalDateTime hora) {
        JornadaEmpleado jornadaEmpleado = jornadaService.findByDiaAndEmpleado(dia, empleado)
                .orElseGet(() -> jornadaService.createJornadaEmpleado(dia, empleado));

        Fichaje fichaje = createFichaje(jornadaEmpleado, tipoFichaje);
        fichajeRepository.save(fichaje);
        Extemporaneo extemporaneo = new Extemporaneo(fichaje, hora);
        extemporaneoRepository.save(extemporaneo);

        jornadaEmpleado.setValidada(false);
        jornadaEmpleadoRepository.save(jornadaEmpleado);
        fichajeRepository.save(fichaje);
    }

    @Override
    public List<FichajeOrdenJornadaSpecification> listFichajesOrdenJornadaNotAnulados(JornadaEmpleado jornadaEmpleado) {
        return fichajeRepository.findAllByJornadaEmpleadoNotAnulado(jornadaEmpleado).stream()
                .map(
                        f -> extemporaneoRepository.findByFichaje(f)
                                .map(e -> new FichajeOrdenJornadaSpecification(f, true, e.getHora()))
                                .orElse(new FichajeOrdenJornadaSpecification(f, false, f.getCreated()))
                )
                .sorted(Comparator.comparing(FichajeOrdenJornadaSpecification::getHoraFichaje))
                .collect(Collectors.toList());
    }

    @Override
    public List<FichajeOrdenJornadaSpecification> listFichajesOrdenJornada(JornadaEmpleado jornadaEmpleado) {
        return fichajeRepository.findAllByJornadaEmpleado(jornadaEmpleado).stream()
                .map(
                        f -> extemporaneoRepository.findByFichaje(f)
                                .map(e -> new FichajeOrdenJornadaSpecification(f, true, e.getHora()))
                                .orElse(new FichajeOrdenJornadaSpecification(f, false, f.getCreated()))
                )
                .sorted(Comparator.comparing(FichajeOrdenJornadaSpecification::getHoraFichaje))
                .collect(Collectors.toList());
    }


    @Override
    public EnumTipoFichaje getFichajeSugerido(JornadaEmpleado jornadaEmpleado) {
        List<FichajeOrdenJornadaSpecification> fichajes = listFichajesOrdenJornadaNotAnulados(jornadaEmpleado);
        if (fichajes.isEmpty()) {
            return EnumTipoFichaje.ENTRADA_JORNADA;
        }

        Fichaje ultimoFichaje = fichajes.get(fichajes.size() -1).getFichaje();
        SugerirFichajeVisitor visitor = new SugerirFichajeVisitor(ultimoFichaje);
        return visitor.getTipoFichaje();
    }


    private Fichaje createFichaje(JornadaEmpleado jornadaEmpleado, EnumTipoFichaje tipoFichaje) {

        if (tipoFichaje.equals(EnumTipoFichaje.ENTRADA_JORNADA)) {
            return new EntradaJornada(jornadaEmpleado);
        }

        if (tipoFichaje.equals(EnumTipoFichaje.SALIDA_JORNADA)) {
            return new SalidaJornada(jornadaEmpleado);
        }

        if (tipoFichaje.equals(EnumTipoFichaje.ENTRADA_DESAYUNO)) {
            return new EntradaDesayuno(jornadaEmpleado);
        }

        if (tipoFichaje.equals(EnumTipoFichaje.SALIDA_DESAYUNO)) {
            return new SalidaDesayuno(jornadaEmpleado);
        }

        if (tipoFichaje.equals(EnumTipoFichaje.ENTRADA_COMIDA)) {
            return new EntradaComida(jornadaEmpleado);
        }

        if (tipoFichaje.equals(EnumTipoFichaje.SALIDA_COMIDA)) {
            return new SalidaComida(jornadaEmpleado);
        }

        throw new RuntimeException("No está soportado el EnumTipoFichaje [" + tipoFichaje + "]");
    }

    class SugerirFichajeVisitor implements FichajeVisitor {

        private EnumTipoFichaje tipoFichaje;

        public SugerirFichajeVisitor(Fichaje fichaje) {
            fichaje.accept(this);
        }

        public EnumTipoFichaje getTipoFichaje() {
            return this.tipoFichaje;
        }

        @Override
        public void visit(EntradaJornada entradaJornada) {
            this.tipoFichaje = EnumTipoFichaje.SALIDA_DESAYUNO;
        }

        @Override
        public void visit(SalidaJornada salidaJornada) {
            this.tipoFichaje = EnumTipoFichaje.ENTRADA_JORNADA;
        }

        @Override
        public void visit(EntradaDesayuno entradaDesayuno) {
            this.tipoFichaje = EnumTipoFichaje.SALIDA_COMIDA;
        }

        @Override
        public void visit(SalidaDesayuno salidaDesayuno) {
            this.tipoFichaje = EnumTipoFichaje.ENTRADA_DESAYUNO;
        }

        @Override
        public void visit(EntradaComida entradaComida) {
            this.tipoFichaje = EnumTipoFichaje.SALIDA_JORNADA;
        }

        @Override
        public void visit(SalidaComida salidaComida) {
            this.tipoFichaje = EnumTipoFichaje.ENTRADA_COMIDA;
        }
    }

}
