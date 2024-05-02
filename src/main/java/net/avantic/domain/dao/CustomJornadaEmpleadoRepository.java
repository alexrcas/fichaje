package net.avantic.domain.dao;

import net.avantic.domain.model.Empleado;
import net.avantic.domain.model.JornadaEmpleado;

public interface CustomJornadaEmpleadoRepository {

    JornadaEmpleado get(Long id);
}
