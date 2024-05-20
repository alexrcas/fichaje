package net.avantic.domain.model.dto.factory;

import net.avantic.domain.model.*;
import net.avantic.domain.model.dto.ComputoDto;
import net.avantic.domain.model.dto.FichajeOrdenJornadaSpecification;
import net.avantic.domain.service.FichajeService;
import net.avantic.utils.FichajeVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ComputoDtoFactory {

    private final FichajeService fichajeService;

    @Autowired
    public ComputoDtoFactory(FichajeService fichajeService) {
        this.fichajeService = fichajeService;
    }


    public ComputoDto newDto(JornadaEmpleado jornadaEmpleado) {
        CalcularJornadaVisitor visitor = new CalcularJornadaVisitor();
        fichajeService.listFichajesOrdenJornadaNotAnulados(jornadaEmpleado).stream()
                .forEach(visitor::popular);

        return visitor.getComputoDto();
    }



    class CalcularJornadaVisitor implements FichajeVisitor {

        private final ComputoDto computoDto = new ComputoDto();
        private LocalDateTime hora;

        public void popular(FichajeOrdenJornadaSpecification specification) {
            this.hora = specification.getHoraFichaje();
            specification.getFichaje().accept(this);
        }

        public ComputoDto getComputoDto() {
            return computoDto;
        }

        @Override
        public void visit(EntradaJornada entradaJornada) {
            computoDto.setInicioJornada(this.hora);
        }

        @Override
        public void visit(SalidaJornada salidaJornada) {
            computoDto.setFinJornada(this.hora);
        }

        @Override
        public void visit(EntradaDesayuno entradaDesayuno) { computoDto.setFinDesayuno(this.hora); }

        @Override
        public void visit(SalidaDesayuno salidaDesayuno) { computoDto.setInicioDesayuno(this.hora); }

        @Override
        public void visit(EntradaComida entradaComida) {
            computoDto.setFinComida(this.hora);
        }

        @Override
        public void visit(SalidaComida salidaComida) {
            computoDto.setInicioComida(this.hora);
        }
    }
}
