package net.avantic.domain.model.dto.factory;

import net.avantic.domain.dao.ExtemporaneoRepository;
import net.avantic.domain.model.*;
import net.avantic.domain.model.dto.FichajeDto;
import net.avantic.domain.model.dto.FichajeOrdenJornadaSpecification;
import net.avantic.domain.model.dto.SolicitudAnulacionDto;
import net.avantic.utils.FichajeVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class SolicitudAnulacionDtoFactory {

    private final ExtemporaneoRepository extemporaneoRepository;

    @Autowired
    public SolicitudAnulacionDtoFactory(ExtemporaneoRepository extemporaneoRepository) {
        this.extemporaneoRepository = extemporaneoRepository;
    }

    public SolicitudAnulacionDto newDto(SolicitudAnulacion solicitudAnulacion) {

        Fichaje fichaje = solicitudAnulacion.getFichaje();

        FichajeOrdenJornadaSpecification specification = extemporaneoRepository.findByFichaje(fichaje)
                .map(e -> new FichajeOrdenJornadaSpecification(fichaje, true, e.getHora()))
                .orElse(new FichajeOrdenJornadaSpecification(fichaje, false, fichaje.getCreated()));


        TipoFichajeVisitor visitor = new TipoFichajeVisitor();

        FichajeDto fichajeDto = new FichajeDto(fichaje.getId(), fichaje.getCreated(), specification.isExtemporaneo(),
                specification.getHoraFichaje(), visitor.getTipoFichaje(specification.getFichaje()), true, false);

        return new SolicitudAnulacionDto(solicitudAnulacion.getCreated(), solicitudAnulacion.getFichaje().getJornadaEmpleado().getEmpleado().getEmail(), fichajeDto);
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
}
