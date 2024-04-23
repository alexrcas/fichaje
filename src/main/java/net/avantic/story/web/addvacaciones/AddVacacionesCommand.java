package net.avantic.story.web.addvacaciones;

import net.avantic.domain.model.EnumTipoFichaje;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AddVacacionesCommand {

    private Long idEmpleado;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
}
