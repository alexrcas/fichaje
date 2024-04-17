package net.avantic.domain.model.dto;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DiaDto {

    private final Long id;
    private final LocalDate fecha;

    public DiaDto(Long id, LocalDate fecha) {
        this.id = id;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String fechaDDMM() {
        String pattern = "dd/MM";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return dateTimeFormatter.format(fecha);
    }
}
