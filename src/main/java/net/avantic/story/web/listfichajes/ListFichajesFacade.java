package net.avantic.story.web.listfichajes;

import net.avantic.domain.model.dto.DiaDto;
import net.avantic.domain.model.dto.JornadasEmpleadoDto;

import java.util.List;

public interface ListFichajesFacade {

    List<DiaDto> listDias();

    List<JornadasEmpleadoDto> listJornadas();
}
