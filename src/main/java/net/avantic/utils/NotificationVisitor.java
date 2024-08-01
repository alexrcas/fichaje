package net.avantic.utils;

import net.avantic.domain.model.NotificationErrorDiaSinFichaje;
import net.avantic.domain.model.NotificationErrorJornadaEmpleado;

public interface NotificationVisitor {

    void visit(NotificationErrorJornadaEmpleado notificationErrorJornadaEmpleado);

    void visit(NotificationErrorDiaSinFichaje notificationErrorDiaSinFichaje);
}
