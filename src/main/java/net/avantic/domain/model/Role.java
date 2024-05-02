package net.avantic.domain.model;

import jakarta.persistence.*;

@Entity
public class Role {

    private Long id;
    private String name;
    private Empleado empleado;

    protected Role() {
    }

    public Role(String name, Empleado empleado) {
        this.name = name;
        this.empleado = empleado;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "idEmpleado")
    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
