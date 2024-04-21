package net.avantic.story.web.listfichajes;

import net.avantic.domain.model.dto.DiaDto;
import net.avantic.domain.model.dto.JornadaDto;
import net.avantic.domain.model.dto.JornadasEmpleadoDto;
import net.avantic.domain.model.dto.SemanaJornadaDto;

import java.util.List;

public interface ListFichajesFacade {

    List<SemanaJornadaDto> listJornadas();

}
