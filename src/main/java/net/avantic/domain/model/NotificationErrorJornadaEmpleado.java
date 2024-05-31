package net.avantic.domain.model;

import jakarta.persistence.*;
import net.avantic.utils.NotificationVisitor;

import java.io.Serializable;

@Entity
@PrimaryKeyJoinColumn(name = "idNotification")
public class NotificationErrorJornadaEmpleado extends Notification implements Serializable {

    private JornadaEmpleado jornadaEmpleado;

    @Override
    public void accept(NotificationVisitor visitor) {
        visitor.visit(this);
    }

    protected NotificationErrorJornadaEmpleado() {
    }

    public NotificationErrorJornadaEmpleado(boolean vista, boolean notificada, JornadaEmpleado jornadaEmpleado) {
        super(vista, notificada, jornadaEmpleado.getEmpleado());
        this.jornadaEmpleado = jornadaEmpleado;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idJornadaEmpleado", nullable = false)
    public JornadaEmpleado getJornadaEmpleado() {
        return jornadaEmpleado;
    }

    public void setJornadaEmpleado(JornadaEmpleado jornadaEmpleado) {
        this.jornadaEmpleado = jornadaEmpleado;
    }
}
