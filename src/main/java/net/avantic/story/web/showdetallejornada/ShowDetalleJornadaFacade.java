package net.avantic.story.web.showdetallejornada;

import net.avantic.domain.model.dto.*;

import java.util.List;

public interface ShowDetalleJornadaFacade {

    List<FichajeDto> listFichajesJornada(Long id);

    ResultadoValidacionJornadaDto listValidacionesJornada(Long idJornada);

    DiaDto getFechaJornada(Long idJornada);

    ComputoDto getComputo(Long idJornada);

    List<AusenciaJustificadaDto> listAusenciasJustificadas(Long idJornada);
}
