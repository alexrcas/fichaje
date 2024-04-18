package net.avantic.domain.model.dto;

import java.time.LocalDate;

public enum EnumDiaSemana {
    LUNES("Lunes"),
    MARTES("Martes"),
    MIERCOLES("Miércoles"),
    JUEVES("Jueves"),
    VIERNES("Viernes"),
    SABADO("Sábado"),
    DOMINGO("Domingo");

    private final String name;

    EnumDiaSemana(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static EnumDiaSemana fromLocalDate(LocalDate date) {
        return EnumDiaSemana.values()[date.getDayOfWeek().getValue() - 1];
    }
}
