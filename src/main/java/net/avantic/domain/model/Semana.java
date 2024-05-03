package net.avantic.domain.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Semana {

    private Long id;
    private LocalDate fecha;

    protected Semana() {
    }

    public Semana(LocalDate fecha) {
        this.fecha = fecha;
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
}
