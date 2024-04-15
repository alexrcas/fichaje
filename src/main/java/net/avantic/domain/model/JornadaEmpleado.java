package net.avantic.domain.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class JornadaEmpleado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Empleado empleado;
    private Jornada jornada;

    protected JornadaEmpleado() {
    }

    public JornadaEmpleado(Empleado empleado, Jornada jornada) {
        this.empleado = empleado;
        this.jornada = jornada;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEmpleado")
    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idJornada")
    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }
}
