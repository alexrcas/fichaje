package net.avantic.story.web.showdetallejornada;

import net.avantic.domain.model.dto.FichajeDto;

import java.util.List;

public interface ShowDetalleJornadaFacade {

    List<FichajeDto> listFichajesJornada(Long id);

    List<String> listValidacionesJornada(Long idJornada);
}
