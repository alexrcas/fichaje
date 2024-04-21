package net.avantic.domain.model.dto;

import java.util.List;

public class SemanaJornadaDto {

    private final List<JornadaDto> jornadas;
    private final boolean isSemanaActual;

    public SemanaJornadaDto(List<JornadaDto> jornadas, boolean isSemanaActual) {
        this.jornadas = jornadas;
        this.isSemanaActual = isSemanaActual;
    }

    public List<JornadaDto> getJornadas() {
        return jornadas;
    }

    public boolean isSemanaActual() {
        return isSemanaActual;
    }
}
