package net.avantic.domain.model.dto.factory;

import net.avantic.domain.dao.ExtemporaneoRepository;
import net.avantic.domain.model.*;
import net.avantic.domain.model.dto.FichajeDto;
import net.avantic.domain.model.dto.FichajeOrdenJornadaSpecification;
import net.avantic.utils.FichajeVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FichajeDtoFactory {

    public FichajeDto newDto(FichajeOrdenJornadaSpecification specification) {
        TipoFichajeVisitor visitor = new TipoFichajeVisitor();
        return new FichajeDto(specification.getFichaje().getId(), specification.getFichaje().getCreated(), specification.isExtemporaneo(),
                specification.getHoraFichaje(), visitor.getTipoFichaje(specification.getFichaje()));
    }
}

    class TipoFichajeVisitor implements FichajeVisitor {

        private EnumTipoFichaje tipoFichaje;

        public EnumTipoFichaje getTipoFichaje(Fichaje fichaje) {
            fichaje.accept(this);
            return tipoFichaje;
        }

        @Override
        public void visit(EntradaJornada entradaJornada) {
            this.tipoFichaje = EnumTipoFichaje.ENTRADA_JORNADA;
        }

        @Override
        public void visit(SalidaJornada salidaJornada) {
            this.tipoFichaje = EnumTipoFichaje.SALIDA_JORNADA;
        }

        @Override
        public void visit(EntradaDesayuno entradaDesayuno) {
            this.tipoFichaje = EnumTipoFichaje.ENTRADA_DESAYUNO;
        }

        @Override
        public void visit(SalidaDesayuno salidaDesayuno) {
            this.tipoFichaje = EnumTipoFichaje.SALIDA_DESAYUNO;
        }

        @Override
        public void visit(EntradaComida entradaComida) {
            this.tipoFichaje = EnumTipoFichaje.ENTRADA_COMIDA;
        }

        @Override
        public void visit(SalidaComida salidaComida) {
            this.tipoFichaje = EnumTipoFichaje.SALIDA_COMIDA;
        }

}
