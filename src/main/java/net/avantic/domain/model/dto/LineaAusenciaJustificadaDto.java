package net.avantic.domain.model.dto;

import java.time.LocalDate;

public class LineaAusenciaJustificadaDto {

    private final EmpleadoDto empleado;
    private final LocalDate fecha;
    private final double horas;
    private final String motivo;

    public LineaAusenciaJustificadaDto(EmpleadoDto empleado, LocalDate fecha, double horas, String motivo) {
        this.empleado = empleado;
        this.fecha = fecha;
        this.horas = horas;
        this.motivo = motivo;
    }

    public EmpleadoDto getEmpleado() {
        return empleado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public double getHoras() {
        return horas;
    }

    public String getMotivo() {
        return motivo;
    }
}
