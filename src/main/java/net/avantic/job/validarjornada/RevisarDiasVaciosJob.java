package net.avantic.job.validarjornada;

import net.avantic.domain.dao.DiaLibreRepository;
import net.avantic.domain.dao.DiaRepository;
import net.avantic.domain.dao.EmpleadoRepository;
import net.avantic.domain.dao.JornadaEmpleadoRepository;
import net.avantic.domain.model.Dia;
import net.avantic.domain.model.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RevisarDiasVaciosJob {

    private final JornadaEmpleadoRepository jornadaEmpleadoRepository;
    private final DiaRepository diaRepository;
    private final DiaLibreRepository diaLibreRepository;
    private final EmpleadoRepository empleadoRepository;

    @Autowired
    public RevisarDiasVaciosJob(JornadaEmpleadoRepository jornadaEmpleadoRepository,
                                DiaRepository diaRepository,
                                DiaLibreRepository diaLibreRepository,
                                EmpleadoRepository empleadoRepository) {
        this.jornadaEmpleadoRepository = jornadaEmpleadoRepository;
        this.diaRepository = diaRepository;
        this.diaLibreRepository = diaLibreRepository;
        this.empleadoRepository = empleadoRepository;
    }

    @Scheduled(fixedDelay = 10000)
    public void notificarDiaVacio() {

        Empleado empleado = empleadoRepository.findByEmail("arodriguez")
                .orElseThrow();

        List<Dia> dias = diaRepository
                .findAllLaborablesSinJornadaByEmpleadoAndFechaBeforeOrderByIdAsc(empleado, LocalDate.now()).stream()
                .toList();

        System.out.println("dias");
    }
}
