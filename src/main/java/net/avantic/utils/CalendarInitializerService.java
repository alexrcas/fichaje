package net.avantic.utils;

import jakarta.annotation.PostConstruct;
import net.avantic.domain.dao.*;
import net.avantic.domain.model.*;
import net.avantic.domain.model.dto.EnumDiaSemana;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;

@Component
public class CalendarInitializerService {

    private final DiaRepository diaRepository;
    private final FichajeRepository fichajeRepository;
    private final EmpleadoRepository empleadoRepository;
    private final ExtemporaneoRepository extemporaneoRepository;
    private final JornadaEmpleadoRepository jornadaEmpleadoRepository;

    @Autowired
    public CalendarInitializerService(DiaRepository diaRepository,
                                      FichajeRepository fichajeRepository,
                                      EmpleadoRepository empleadoRepository,
                                      ExtemporaneoRepository extemporaneoRepository,
                                      JornadaEmpleadoRepository jornadaEmpleadoRepository) {
        this.diaRepository = diaRepository;
        this.fichajeRepository = fichajeRepository;
        this.empleadoRepository = empleadoRepository;
        this.extemporaneoRepository = extemporaneoRepository;
        this.jornadaEmpleadoRepository = jornadaEmpleadoRepository;
    }


    @PostConstruct
    public void init() {

        LocalDate today = LocalDate.now();
        int year = today.getYear();
        LocalDate startDate = LocalDate.of(year, 4, 1);
        LocalDate endDate = LocalDate.of(year, 6, 30);

        for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
            Dia dia = new Dia(date, getDiaSemana(date), esFestivo(date));
            diaRepository.save(dia);
        }

        Empleado empleado = new Empleado("arodriguez");
        empleadoRepository.save(empleado);

        diaRepository.findAll().stream()
                .map(dia -> new JornadaEmpleado(empleado, dia))
                .map(jornadaEmpleadoRepository::save)
                .forEach(this::crearFichajes);

    }
    
    private void crearFichajes(JornadaEmpleado jornadaEmpleado) {
        Random random = new Random();

        LocalDate fechaBase = jornadaEmpleado.getDia().getFecha();

        LocalDateTime horaEntradaJornada = LocalDateTime.of(fechaBase, LocalTime.of(random.nextInt(3) + 7, 0, 0));
        EntradaJornada entradaJornada = new EntradaJornada(jornadaEmpleado);
        Extemporaneo ex1 = new Extemporaneo(entradaJornada, horaEntradaJornada);

        LocalDateTime horaSalidaJornada = LocalDateTime.of(fechaBase, LocalTime.of(random.nextInt(3) + 15, 0, 0));
        SalidaJornada salidaJornada = new SalidaJornada(jornadaEmpleado);
        Extemporaneo ex2 = new Extemporaneo(salidaJornada, horaSalidaJornada);

        fichajeRepository.save(entradaJornada);
        fichajeRepository.save(salidaJornada);
        extemporaneoRepository.save(ex1);
        extemporaneoRepository.save(ex2);
    }

    private boolean esFestivo(LocalDate date) {
        return date.getDayOfWeek().getValue() >= 6;
    }

    private EnumDiaSemana getDiaSemana(LocalDate date) {
        return EnumDiaSemana.fromLocalDate(date);
    }
}
