package net.avantic.domain.model.dto.factory;

import net.avantic.domain.model.EnumTipoFichaje;
import net.avantic.domain.model.SolicitudAnulacion;
import net.avantic.domain.model.dto.FichajeDto;
import net.avantic.domain.model.dto.SolicitudAnulacionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SolicitudAnulacionDtoFactory {

    public SolicitudAnulacionDto newDto(SolicitudAnulacion solicitudAnulacion) {
        return new SolicitudAnulacionDto(solicitudAnulacion.getFichaje().getJornadaEmpleado().getEmpleado().getEmail(),
                solicitudAnulacion.getFichaje().getId(),
                EnumTipoFichaje.ENTRADA_DESAYUNO);
    }
}
