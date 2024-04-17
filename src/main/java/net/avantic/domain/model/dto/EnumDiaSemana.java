package net.avantic.domain.model.dto;

import java.time.DayOfWeek;
import java.time.LocalDate;

public enum EnumDiaSemana {
    LUNES,
    MARTES,
    MIERCOLES,
    JUEVES,
    VIERNES,
    SABADO,
    DOMINGO;

    public static EnumDiaSemana fromLocalDate(LocalDate date) {
        return EnumDiaSemana.values()[date.getDayOfWeek().getValue() - 1];
    }
}
