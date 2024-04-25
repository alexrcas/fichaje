package net.avantic.domain.model.dto;

import java.util.List;

public class SemanaJornadaDto {

    private final List<DiaCalendarioDto> dias;
    private final boolean isSemanaActual;
    private final double tiempoSemana;
    private final double horas;

    public SemanaJornadaDto(List<DiaCalendarioDto> dias, boolean isSemanaActual, double tiempoSemana, double horas) {
        this.dias = dias;
        this.isSemanaActual = isSemanaActual;
        this.tiempoSemana = tiempoSemana;
        this.horas = horas;
    }

    public List<DiaCalendarioDto> getDias() {
        return dias;
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
