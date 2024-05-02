package net.avantic.story.web.supervisarfichaje;

import net.avantic.domain.model.EnumTipoFichaje;
import net.avantic.domain.model.dto.SemanaJornadaDto;

import java.util.List;

public interface SupervisarFichajeFacade {

    List<SemanaJornadaDto> listJornadas(Long idEmpleado);

    boolean isAdmin();

    String getAuthenticatedUsername();
}
