package net.avantic.domain.model.dto;

import java.time.LocalDate;

public enum EnumDiaSemana {
    LUNES("Lunes", "L"),
    MARTES("Martes", "M"),
    MIERCOLES("Miércoles", "X"),
    JUEVES("Jueves", "J"),
    VIERNES("Viernes", "V"),
    SABADO("Sábado", "S"),
    DOMINGO("Domingo", "D");

    private final String name;
    private final String codigo;

    EnumDiaSemana(String name, String codigo) {
        this.name = name;
        this.codigo = codigo;
    }

    public String getName() {
        return name;
    }

    public String getCodigo() {
        return codigo;
    }

    public static EnumDiaSemana fromLocalDate(LocalDate date) {
        return EnumDiaSemana.values()[date.getDayOfWeek().getValue() - 1];
    }
}
