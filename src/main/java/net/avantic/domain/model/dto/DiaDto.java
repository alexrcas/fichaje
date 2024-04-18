package net.avantic.domain.model.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DiaDto {

    private final Long id;
    private final LocalDate fecha;
    private final EnumDiaSemana diaSemana;

    public DiaDto(Long id, LocalDate fecha, EnumDiaSemana diaSemana) {
        this.id = id;
        this.fecha = fecha;
        this.diaSemana = diaSemana;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public EnumDiaSemana getDiaSemana() {
        return diaSemana;
    }

    public String getFechaFormateada() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("%s, %s", diaSemana.getName(), dateFormat.format(fecha));
    }

    public String fechaDDMM() {
        String pattern = "dd/MM";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return dateTimeFormatter.format(fecha);
    }
}
