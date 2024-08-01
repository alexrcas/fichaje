package net.avantic.domain.model;

import jakarta.persistence.*;
import net.avantic.utils.NotificationVisitor;

import java.io.Serializable;

@Entity
@PrimaryKeyJoinColumn(name = "idNotification")
public class NotificationErrorDiaSinFichaje extends Notification implements Serializable {

    private Dia dia;

    @Override
    public void accept(NotificationVisitor visitor) {
        visitor.visit(this);
    }

    protected NotificationErrorDiaSinFichaje() {
    }

    public NotificationErrorDiaSinFichaje(boolean vista, boolean notificada, Empleado empleado, Dia dia) {
        super(vista, notificada, empleado);
        this.dia = dia;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDia", nullable = false)
    public Dia getDia() {
        return dia;
    }

    public void setDia(Dia dia) {
        this.dia = dia;
    }
}
