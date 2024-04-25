package net.avantic.domain.model.dto;

import java.time.LocalDate;

public class DiaCalendarioDto {

    private final JornadaDto jornada;
    private final boolean festivo;
    private final boolean vacaciones;
    private final LocalDate fecha;

    public DiaCalendarioDto(JornadaDto jornada, boolean festivo, boolean vacaciones, LocalDate fecha) {
        this.jornada = jornada;
        this.festivo = festivo;
        this.vacaciones = vacaciones;
        this.fecha = fecha;
    }

    public JornadaDto getJornada() {
        return jornada;
    }

    public boolean isFestivo() {
        return festivo;
    }

    public boolean isVacaciones() {
        return vacaciones;
    }

    public LocalDate getFecha() {
        return fecha;
    }
}
