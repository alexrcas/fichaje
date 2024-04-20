package net.avantic.utils;

import jakarta.annotation.PostConstruct;
import net.avantic.domain.dao.*;
import net.avantic.domain.model.*;
import net.avantic.domain.model.dto.EnumDiaSemana;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
        /*
        diaRepository.findAll().stream()
                .map(dia -> new JornadaEmpleado(empleado, dia))
                .map(jornadaEmpleadoRepository::save)
                .map(je -> new EntradaJornada(empleado, je))
                .map(fichajeRepository::save)
                .map(f -> new Extemporaneo(f, LocalDateTime.now()))
                .forEach(extemporaneoRepository::save);


 */
    }

    private boolean esFestivo(LocalDate date) {
        return date.getDayOfWeek().getValue() >= 6;
    }

    private EnumDiaSemana getDiaSemana(LocalDate date) {
        return EnumDiaSemana.fromLocalDate(date);
    }
}
