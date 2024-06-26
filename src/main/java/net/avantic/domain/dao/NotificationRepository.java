package net.avantic.domain.dao;

import net.avantic.domain.model.Empleado;
import net.avantic.domain.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("From Notification n where n.notificada = false and n.empleado = :empleado")
    List<Notification> findAllByNotNotificadaAndEmpleado(Empleado empleado);
}
