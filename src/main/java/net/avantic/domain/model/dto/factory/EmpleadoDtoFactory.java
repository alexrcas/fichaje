package net.avantic.domain.model.dto.factory;

import net.avantic.domain.model.Empleado;
import net.avantic.domain.model.dto.EmpleadoDto;
import org.springframework.stereotype.Component;

public class EmpleadoDtoFactory {

    public static EmpleadoDto newDto(Empleado empleado) {
        return new EmpleadoDto(empleado.getId(), empleado.getEmail(), empleado.getNombre(), empleado.getApellidos());
    }
}
