package net.avantic.domain.model.dto.factory;

import net.avantic.domain.model.*;
import net.avantic.domain.model.dto.FichajeDto;
import net.avantic.utils.FichajeVisitor;
import org.springframework.stereotype.Component;

@Component
public class FichajeDtoFactory {

    public FichajeDto newDto(Fichaje fichaje) {
        TipoFichajeVisitor visitor = new TipoFichajeVisitor(fichaje);
        return new FichajeDto(fichaje.getCreated(), visitor.getTipoFichaje());
    }

    class TipoFichajeVisitor implements FichajeVisitor {

        private EnumTipoFichaje tipoFichaje;

        public TipoFichajeVisitor(Fichaje fichaje) {
            fichaje.accept(this);
        }

        public EnumTipoFichaje getTipoFichaje() {
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
}
