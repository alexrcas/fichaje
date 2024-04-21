package net.avantic.domain.model.dto;

import net.avantic.domain.model.Dia;
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

    public static JornadaDto emptyDto(Dia dia) {
        return new JornadaDto(-1L, "", dia.getFecha());
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
