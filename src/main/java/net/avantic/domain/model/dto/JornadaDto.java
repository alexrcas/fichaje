package net.avantic.domain.model.dto;

import net.avantic.domain.model.Dia;
import net.avantic.domain.model.JornadaEmpleado;

import java.time.LocalDate;

public class JornadaDto {

    private final Long id;
    private final String horas;
    private final LocalDate fecha;
    private final boolean ausenciaJustificada;

    public JornadaDto(Long id, String horas, LocalDate fecha, boolean ausenciaJustificada) {
        this.id = id;
        this.horas = horas;
        this.fecha = fecha;
        this.ausenciaJustificada = ausenciaJustificada;
    }

    public static JornadaDto emptyDto(Dia dia) {
        return new JornadaDto(-1L, "", dia.getFecha(), false);
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

    public boolean isAusenciaJustificada() {
        return ausenciaJustificada;
    }
}
