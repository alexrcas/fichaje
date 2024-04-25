package net.avantic.domain.model.dto;

import java.util.List;

public class SemanaJornadaDto {

    private final List<JornadaDto> jornadas;
    private final boolean isSemanaActual;
    private final double tiempoSemana;
    private final double horas;

    public SemanaJornadaDto(List<JornadaDto> jornadas, boolean isSemanaActual, double tiempoSemana, double horas) {
        this.jornadas = jornadas;
        this.isSemanaActual = isSemanaActual;
        this.tiempoSemana = tiempoSemana;
        this.horas = horas;
    }

    public List<JornadaDto> getJornadas() {
        return jornadas;
    }

    public boolean isSemanaActual() {
        return isSemanaActual;
    }

    public double getTiempoSemana() {
        return tiempoSemana;
    }

    public double getHoras() {
        return horas;
    }
}
