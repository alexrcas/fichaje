package net.avantic.story.web.showdetallesolicitudanulacion;

import net.avantic.domain.model.dto.*;

import java.util.List;

public interface ShowDetalleSolicitudAnulacionFacade {

    List<FichajeDto> listFichajesJornada(Long idFichaje);

    ResultadoValidacionJornadaDto listValidacionesJornada(Long idFichaje);

    DiaDto getFechaJornada(Long idFichaje);

    ComputoDto getComputo(Long idFichaje);

    List<AusenciaJustificadaDto> listAusenciasJustificadas(Long idFichaje);

    String getUsernameEmpleadoFichaje(Long idFichaje);

    boolean isAdmin();
}
