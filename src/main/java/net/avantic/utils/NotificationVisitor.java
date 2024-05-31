package net.avantic.utils;

import net.avantic.domain.model.NotificationErrorJornadaEmpleado;

public interface NotificationVisitor {

    void visit(NotificationErrorJornadaEmpleado notificationErrorJornadaEmpleado);
}
