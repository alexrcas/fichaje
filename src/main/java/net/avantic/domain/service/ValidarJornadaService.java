package net.avantic.domain.service;

import net.avantic.domain.model.JornadaEmpleado;
import net.avantic.domain.model.dto.ResultadoValidacionJornadaDto;

public interface ValidarJornadaService {

    ResultadoValidacionJornadaDto validar(JornadaEmpleado jornadaEmpleado);
}
