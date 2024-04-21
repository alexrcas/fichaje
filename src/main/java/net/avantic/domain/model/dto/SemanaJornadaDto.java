package net.avantic.domain.model.dto;

import java.util.List;

public class SemanaJornadaDto {

    private final List<JornadaDto> jornadas;

    public SemanaJornadaDto(List<JornadaDto> jornadas) {
        this.jornadas = jornadas;
    }

    public List<JornadaDto> getJornadas() {
        return jornadas;
    }
}
