package net.avantic.story.web.listFestivos;

import net.avantic.domain.model.dto.FestivoDto;
import net.avantic.domain.model.dto.VacacionesDto;

import java.util.List;

public interface ListFestivosFacade {

    List<FestivoDto> listFestivos();

    String getAuthenticatedUsername();
}
