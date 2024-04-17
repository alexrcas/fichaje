package net.avantic.domain.service;

import net.avantic.domain.model.Empleado;
import net.avantic.domain.model.Dia;
import net.avantic.domain.model.JornadaEmpleado;

import java.util.Optional;

public interface JornadaService {

    Optional<JornadaEmpleado> findByDiaAndEmpleado(Dia dia, Empleado empleado);

    JornadaEmpleado createJornadaEmpleado(Dia dia, Empleado empleado);
}
