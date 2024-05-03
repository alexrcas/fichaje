package net.avantic.domain.model;

import jakarta.persistence.*;

@Entity
@Table(indexes = {@Index(columnList = "idVacaciones"), @Index(columnList = "idDia, idEmpleado")})
public class DiaLibre {

    private Long id;

    private Empleado empleado;
    private Dia dia;
    private Vacaciones vacaciones;

    protected DiaLibre() {}

    public DiaLibre(Empleado empleado, Dia dia, Vacaciones vacaciones) {
        this.empleado = empleado;
        this.dia = dia;
        this.vacaciones = vacaciones;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idVacaciones", nullable = false)
    public Vacaciones getVacaciones() {
        return vacaciones;
    }

    public void setVacaciones(Vacaciones vacaciones) {
        this.vacaciones = vacaciones;
    }
}
