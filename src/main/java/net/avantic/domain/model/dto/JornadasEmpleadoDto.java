package net.avantic.domain.model.dto;

import java.util.List;

public class JornadasEmpleadoDto {

    private final EmpleadoDto empleado;
    private final List<JornadaDto> jornadas;

    public JornadasEmpleadoDto(EmpleadoDto empleado, List<JornadaDto> jornadas) {
        this.empleado = empleado;
        this.jornadas = jornadas;
    }

    public EmpleadoDto getEmpleado() {
        return empleado;
    }

    public List<JornadaDto> getJornadas() {
        return jornadas;
    }
}
