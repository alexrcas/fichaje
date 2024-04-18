package net.avantic.story.web.showdetallejornada;

import net.avantic.domain.model.dto.DiaDto;
import net.avantic.domain.model.dto.FichajeDto;
import net.avantic.domain.model.dto.ResultadoValidacionJornadaDto;

import java.util.List;

public interface ShowDetalleJornadaFacade {

    List<FichajeDto> listFichajesJornada(Long id);

    ResultadoValidacionJornadaDto listValidacionesJornada(Long idJornada);

    DiaDto getFechaJornada(Long idJornada);
}
