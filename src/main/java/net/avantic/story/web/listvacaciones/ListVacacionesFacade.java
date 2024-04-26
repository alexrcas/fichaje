package net.avantic.story.web.listvacaciones;

import net.avantic.domain.model.EnumTipoFichaje;
import net.avantic.domain.model.dto.SemanaJornadaDto;
import net.avantic.domain.model.dto.VacacionesDto;

import java.util.List;

public interface ListVacacionesFacade {

    List<VacacionesDto> listVacaciones();

}
