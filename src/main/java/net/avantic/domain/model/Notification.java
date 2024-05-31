package net.avantic.domain.model;

import jakarta.persistence.*;
import net.avantic.utils.NotificationVisitor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Notification implements Serializable {

    private Long id;
    private LocalDateTime created;
    private Empleado empleado;

    private boolean vista;
    private boolean notificada;

    @Transient
    public abstract void accept(NotificationVisitor visitor);

    protected Notification() {
    }

    public Notification(boolean vista, boolean notificada, Empleado empleado) {
        this.created = LocalDateTime.now();
        this.vista = vista;
        this.notificada = notificada;
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
    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEmpleado", nullable = false)
    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @Column(nullable = false)
    public boolean isVista() {
        return vista;
    }

    public void setVista(boolean vista) {
        this.vista = vista;
    }

    @Column(nullable = false)
    public boolean isNotificada() {
        return notificada;
    }

    public void setNotificada(boolean notificada) {
        this.notificada = notificada;
    }
}
