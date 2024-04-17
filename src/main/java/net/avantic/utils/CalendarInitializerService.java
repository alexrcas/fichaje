package net.avantic.utils;

import jakarta.annotation.PostConstruct;
import net.avantic.domain.dao.DiaRepository;
import net.avantic.domain.dao.EmpleadoRepository;
import net.avantic.domain.dao.FichajeRepository;
import net.avantic.domain.model.Dia;
import net.avantic.domain.model.Empleado;
import net.avantic.domain.model.JornadaEmpleado;
import net.avantic.domain.model.dto.EnumDiaSemana;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CalendarInitializerService {

    private final DiaRepository diaRepository;
    private final FichajeRepository fichajeRepository;
    private final EmpleadoRepository empleadoRepository;

    @Autowired
    public CalendarInitializerService(DiaRepository diaRepository,
                                      FichajeRepository fichajeRepository,
                                      EmpleadoRepository empleadoRepository) {
        this.diaRepository = diaRepository;
        this.fichajeRepository = fichajeRepository;
        this.empleadoRepository = empleadoRepository;
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

    }

    private boolean esFestivo(LocalDate date) {
        return date.getDayOfWeek().getValue() >= 6;
    }

    private EnumDiaSemana getDiaSemana(LocalDate date) {
        return EnumDiaSemana.fromLocalDate(date);
    }
}
