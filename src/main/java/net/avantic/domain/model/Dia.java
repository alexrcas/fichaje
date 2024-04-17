package net.avantic.domain.model;

import jakarta.persistence.*;
import net.avantic.domain.model.dto.EnumDiaSemana;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Dia implements Serializable {

    private Long id;

    private LocalDate fecha;
    private EnumDiaSemana diaSemana;
    private boolean festivo;

    protected Dia() {
    }

    public Dia(LocalDate fecha, EnumDiaSemana diaSemana, boolean festivo) {
        this.fecha = fecha;
        this.diaSemana = diaSemana;
        this.festivo = festivo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Enumerated(EnumType.STRING)
    public EnumDiaSemana getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(EnumDiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }

    public boolean isFestivo() {
        return festivo;
    }

    public void setFestivo(boolean festivo) {
        this.festivo = festivo;
    }
}
