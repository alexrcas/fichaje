package net.avantic.job.validarjornada;

import net.avantic.domain.dao.EmpleadoRepository;
import net.avantic.domain.dao.NotificationRepository;
import net.avantic.domain.model.Empleado;
import net.avantic.domain.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificarViaEmailJob {

    private final NotificationRepository notificationRepository;
    private final EmpleadoRepository empleadoRepository;

    @Autowired
    public NotificarViaEmailJob(NotificationRepository notificationRepository,
                                EmpleadoRepository empleadoRepository) {
        this.notificationRepository = notificationRepository;
        this.empleadoRepository = empleadoRepository;
    }

    @Scheduled(fixedDelay = 5000)
    public void notificar() {
        empleadoRepository.findAll().stream()
                    .forEach(emp -> {
                        try {
                            List<Notification> notifications = notificationRepository.findAllByNotNotificadaAndEmpleado(emp);
                            buildAndSendEmailFromNotifications(emp, notifications);
                        } catch (Exception e) {

                        }
                    });
    }


    private void buildAndSendEmailFromNotifications(Empleado empleado, List<Notification> notifications) {
        //todo arodriguez
        //emailService.buildAndSend(empleado, notifications);
        notifications.stream()
                .forEach(n -> {
                    n.setNotificada(true);
                    notificationRepository.save(n);
                });
    }

}
