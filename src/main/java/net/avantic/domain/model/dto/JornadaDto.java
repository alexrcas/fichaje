package net.avantic.domain.model.dto;

import net.avantic.domain.model.JornadaEmpleado;

import java.time.LocalDate;

public class JornadaDto {

    private final Long id;
    private final String horas;
    private final LocalDate fecha;

    public JornadaDto(Long id, String horas, LocalDate fecha) {
        this.id = id;
        this.horas = horas;
        this.fecha = fecha;
    }

    public static JornadaDto emptyDto() {
        return new JornadaDto(-1L, "", null);
    }

    public Long getId() {
        return id;
    }

    public String getHoras() {
        return horas;
    }

    public LocalDate getFecha() {
        return fecha;
    }
}
