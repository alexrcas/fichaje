package net.avantic.story.web.listsolicitudesanulacion;

import net.avantic.domain.model.dto.SolicitudAnulacionDto;
import net.avantic.domain.model.dto.VacacionesDto;

import java.util.List;

public interface ListSolicitudesAnulacionFacade {

    List<SolicitudAnulacionDto> listSolicitudesAnulacion();

    String getAuthenticatedUsername();
}
