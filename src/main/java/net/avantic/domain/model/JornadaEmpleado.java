package net.avantic.domain.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(indexes = @Index(columnList = "idDia, idEmpleado"))
public class JornadaEmpleado implements Serializable {

    private Long id;

    private Empleado empleado;
    private Dia dia;

    private boolean validada;

    protected JornadaEmpleado() {
    }

    public JornadaEmpleado(Empleado empleado, Dia dia) {
        this.empleado = empleado;
        this.dia = dia;
        this.validada = false;
    }

    public JornadaEmpleado(Empleado empleado, Dia dia, boolean validada) {
        this.empleado = empleado;
        this.dia = dia;
        this.validada = validada;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEmpleado", nullable = false)
    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDia", nullable = false)
    public Dia getDia() {
        return dia;
    }

    public void setDia(Dia dia) {
        this.dia = dia;
    }

    @Column(nullable = false)
    public boolean isValidada() {
        return validada;
    }

    public void setValidada(boolean validada) {
        this.validada = validada;
    }
}
