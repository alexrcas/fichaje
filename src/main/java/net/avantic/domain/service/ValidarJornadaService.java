package net.avantic.domain.service;

import net.avantic.domain.model.Empleado;
import net.avantic.domain.model.JornadaEmpleado;

import java.util.List;

public interface ValidarJornadaService {

    void validarTodas(Empleado empleado);

    List<String> validar(JornadaEmpleado jornadaEmpleado);
}
