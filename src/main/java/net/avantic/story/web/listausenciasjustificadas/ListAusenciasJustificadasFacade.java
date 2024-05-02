package net.avantic.story.web.listausenciasjustificadas;

import net.avantic.domain.model.dto.AusenciaJustificadaDto;
import net.avantic.domain.model.dto.LineaAusenciaJustificadaDto;
import net.avantic.domain.model.dto.VacacionesDto;

import java.util.List;

public interface ListAusenciasJustificadasFacade {

    List<LineaAusenciaJustificadaDto> listAusencias();

    String getAuthenticatedUsername();
}
