package net.avantic.domain.service.impl;

import net.avantic.domain.dao.NotificationRepository;
import net.avantic.domain.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificacionService implements net.avantic.domain.service.NotificacionService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificacionService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void generarNotificacionJornadaErronea(JornadaEmpleado jornadaEmpleado) {
        NotificationErrorJornadaEmpleado notification = new NotificationErrorJornadaEmpleado(false, false, jornadaEmpleado);
        notificationRepository.save(notification);
    }

    @Override
    public void generarNotificacionDiaSinFichar(Dia dia, Empleado empleado) {
        NotificationErrorDiaSinFichaje notification = new NotificationErrorDiaSinFichaje(false, false, empleado, dia);
        notificationRepository.save(notification);
    }
}
