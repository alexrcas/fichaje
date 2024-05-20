package net.avantic.domain.model.dto.factory;

import net.avantic.domain.dao.AnulacionFichajeRepository;
import net.avantic.domain.dao.SolicitudAnulacionRepository;
import net.avantic.domain.model.*;
import net.avantic.domain.model.dto.FichajeDto;
import net.avantic.domain.model.dto.FichajeOrdenJornadaSpecification;
import net.avantic.utils.FichajeVisitor;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class FichajeDtoFactory {

    private final SolicitudAnulacionRepository solicitudAnulacionRepository;
    private final AnulacionFichajeRepository anulacionFichajeRepository;

    @Autowired
    public FichajeDtoFactory(SolicitudAnulacionRepository solicitudAnulacionRepository,
                             AnulacionFichajeRepository anulacionFichajeRepository) {
        this.solicitudAnulacionRepository = solicitudAnulacionRepository;
        this.anulacionFichajeRepository = anulacionFichajeRepository;
    }

    public FichajeDto newDto(FichajeOrdenJornadaSpecification specification) {

        TipoFichajeVisitor visitor = new TipoFichajeVisitor();

        Fichaje fichaje = specification.getFichaje();
        boolean pendienteAnulacion = !solicitudAnulacionRepository.findAllByFichaje(fichaje).isEmpty();

        boolean anulado = solicitudAnulacionRepository.findAllByFichaje(fichaje).stream()
                .map(anulacionFichajeRepository::findAllBySolicitudAnulacion)
                .flatMap(Collection::stream)
                .toList()
                .size() > 0;

        return new FichajeDto(fichaje.getId(), fichaje.getCreated(), specification.isExtemporaneo(),
                specification.getHoraFichaje(), visitor.getTipoFichaje(specification.getFichaje()), pendienteAnulacion, anulado);
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
