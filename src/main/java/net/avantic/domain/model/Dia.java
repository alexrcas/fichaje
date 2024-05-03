package net.avantic.domain.model;

import jakarta.persistence.*;
import net.avantic.domain.model.dto.EnumDiaSemana;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(indexes = {@Index(columnList = "fecha"), @Index(columnList = "idSemana")})
public class Dia implements Serializable {

    private Long id;

    private LocalDate fecha;
    private EnumDiaSemana diaSemana;
    private boolean finSemana;
    private boolean festivo;

    private Semana semana;

    protected Dia() {
    }

    public Dia(LocalDate fecha, EnumDiaSemana diaSemana, boolean finSemana, boolean festivo, Semana semana) {
        this.fecha = fecha;
        this.diaSemana = diaSemana;
        this.finSemana = finSemana;
        this.festivo = festivo;
        this.semana = semana;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
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

    @Column(nullable = false)
    public boolean isFinSemana() {
        return finSemana;
    }

    public void setFinSemana(boolean finSemana) {
        this.finSemana = finSemana;
    }

    @Column(nullable = false)
    public boolean isFestivo() {
        return festivo;
    }

    public void setFestivo(boolean festivo) {
        this.festivo = festivo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSemana", nullable = false)
    public Semana getSemana() {
        return semana;
    }

    public void setSemana(Semana semana) {
        this.semana = semana;
    }
}
