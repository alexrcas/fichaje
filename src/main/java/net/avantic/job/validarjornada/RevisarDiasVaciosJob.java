package net.avantic.job.validarjornada;

import net.avantic.domain.dao.DiaLibreRepository;
import net.avantic.domain.dao.DiaRepository;
import net.avantic.domain.dao.EmpleadoRepository;
import net.avantic.domain.dao.JornadaEmpleadoRepository;
import net.avantic.domain.model.Dia;
import net.avantic.domain.model.Empleado;
import net.avantic.domain.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class RevisarDiasVaciosJob {

    private final JornadaEmpleadoRepository jornadaEmpleadoRepository;
    private final DiaRepository diaRepository;
    private final DiaLibreRepository diaLibreRepository;
    private final EmpleadoRepository empleadoRepository;
    private final NotificacionService notificacionService;

    @Autowired
    public RevisarDiasVaciosJob(JornadaEmpleadoRepository jornadaEmpleadoRepository,
                                DiaRepository diaRepository,
                                DiaLibreRepository diaLibreRepository,
                                EmpleadoRepository empleadoRepository,
                                NotificacionService notificacionService) {
        this.jornadaEmpleadoRepository = jornadaEmpleadoRepository;
        this.diaRepository = diaRepository;
        this.diaLibreRepository = diaLibreRepository;
        this.empleadoRepository = empleadoRepository;
        this.notificacionService = notificacionService;
    }

    @Scheduled(fixedDelay = 10000)
    public void notificarDiaVacio() {

        //todo arodriguez: usar bucle para iterar por empleado
        Empleado empleado = empleadoRepository.findByEmail("arodriguez")
                .orElseThrow();

        diaRepository
            .findAllLaborablesSinJornadaByEmpleadoAndFechaBeforeOrderByIdAsc(empleado, LocalDate.now()).stream()
            .forEach(d -> notificacionService.generarNotificacionDiaSinFichar(d, empleado));

        System.out.println("dias");
    }
}
