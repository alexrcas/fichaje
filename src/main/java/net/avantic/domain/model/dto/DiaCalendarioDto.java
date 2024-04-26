package net.avantic.domain.model.dto;

import java.time.LocalDate;

public class DiaCalendarioDto {

    private final JornadaDto jornada;
    private final boolean festivo;
    private final boolean vacaciones;
    private final LocalDate fecha;
    private boolean today;

    public DiaCalendarioDto(JornadaDto jornada, boolean festivo, boolean vacaciones, LocalDate fecha, boolean today) {
        this.jornada = jornada;
        this.festivo = festivo;
        this.vacaciones = vacaciones;
        this.fecha = fecha;
        this.today = today;
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

    public boolean isToday() {
        return today;
    }
}
