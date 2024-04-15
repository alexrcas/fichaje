package net.avantic.domain.model;

import jakarta.persistence.*;
import net.avantic.utils.FichajeVisitor;

import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Fichaje implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Empleado empleado;
    private JornadaEmpleado jornadaEmpleado;

    @Transient
    public abstract void accept(FichajeVisitor visitor);

    protected Fichaje() {
    }

    public Fichaje(Empleado empleado, JornadaEmpleado jornadaEmpleado) {
        this.empleado = empleado;
        this.jornadaEmpleado = jornadaEmpleado;
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
    @JoinColumn(name = "idJornadaEmpleado")
    public JornadaEmpleado getJornadaEmpleado() {
        return jornadaEmpleado;
    }

    public void setJornadaEmpleado(JornadaEmpleado jornadaEmpleado) {
        this.jornadaEmpleado = jornadaEmpleado;
    }
}
