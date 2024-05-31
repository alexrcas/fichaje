package net.avantic.job.validarjornada;

import net.avantic.domain.dao.NotificationRepository;
import net.avantic.domain.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NotificarViaEmailJob {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificarViaEmailJob(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    //todo arodriguez
    /*
        Para no enviar un email por cada incidencia lo interesante sería:
        1. iterar para cada empleado y hacer un notificationRepository.findAllByNotNotificadaAndEmpleado
        2. construimos un solo emailDto con todas las incidencias y lo enviamos
        3. Marcamos las incidencias como notificadas si el envío ha tenido éxito
     */
    @Scheduled(fixedDelay = 5000)
    public void notificar() {
        notificationRepository.findAllByNotNotificada().stream()
                .forEach(this::internalNotificar);
    }


    private void internalNotificar(Notification notification) {

        notification.setNotificada(true);
        notificationRepository.save(notification);
    }
}
