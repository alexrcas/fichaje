package net.avantic.story.web.listfichajes;

import net.avantic.domain.model.EnumTipoFichaje;
import net.avantic.domain.model.dto.*;

import java.util.List;

public interface ListFichajesFacade {

    List<SemanaJornadaDto> listJornadas();

    List<EnumTipoFichaje> listOpciones();

    EnumTipoFichaje getOpcionSugerida();

    boolean isAdmin();

}
