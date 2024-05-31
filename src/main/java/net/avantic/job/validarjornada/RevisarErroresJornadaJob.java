package net.avantic.job.validarjornada;

import net.avantic.domain.dao.JornadaEmpleadoRepository;
import net.avantic.domain.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RevisarErroresJornadaJob {

    private final JornadaEmpleadoRepository jornadaEmpleadoRepository;
    private final NotificacionService notificacionService;

    @Autowired
    public RevisarErroresJornadaJob(JornadaEmpleadoRepository jornadaEmpleadoRepository,
                                    NotificacionService notificacionService) {
        this.jornadaEmpleadoRepository = jornadaEmpleadoRepository;
        this.notificacionService = notificacionService;
    }

    @Scheduled(fixedDelay = 30000)
    public void notificarErroresJornada() {
        System.out.println("Revisando errores de jornada...");
        jornadaEmpleadoRepository.findAllByNotValid().stream()
                .forEach(notificacionService::generarNotificacionJornadaErronea);
    }
}
