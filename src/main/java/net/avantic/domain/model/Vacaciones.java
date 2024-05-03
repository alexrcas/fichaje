package net.avantic.domain.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(indexes = @Index(columnList = "fechaInicio"))
public class Vacaciones {

    private Long id;
    private LocalDate fechaInicio;
    private LocalDate fechaRegreso;

    protected Vacaciones() {
    }

    public Vacaciones(LocalDate fechaInicio, LocalDate fechaRegreso) {
        this.fechaInicio = fechaInicio;
        this.fechaRegreso = fechaRegreso;
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
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Column(nullable = false)
    public LocalDate getFechaRegreso() {
        return fechaRegreso;
    }

    public void setFechaRegreso(LocalDate fechaRegreso) {
        this.fechaRegreso = fechaRegreso;
    }
}
