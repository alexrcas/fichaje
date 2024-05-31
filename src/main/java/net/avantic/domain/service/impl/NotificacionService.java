package net.avantic.domain.service.impl;

import net.avantic.domain.dao.NotificationRepository;
import net.avantic.domain.model.JornadaEmpleado;
import net.avantic.domain.model.NotificationErrorJornadaEmpleado;
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
}
