package net.avantic.story.web.showcalendario;

import net.avantic.domain.model.dto.DiaDto;
import net.avantic.domain.model.dto.VacacionesDto;

import java.util.List;

public interface ShowCalendarioFacade {

    List<VacacionesDto> listVacaciones();

    List<DiaDto> listFestivos();
}
