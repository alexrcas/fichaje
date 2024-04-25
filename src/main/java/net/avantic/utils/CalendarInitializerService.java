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
    private final SemanaRepository semanaRepository;

    @Autowired
    public CalendarInitializerService(DiaRepository diaRepository,
                                      FichajeRepository fichajeRepository,
                                      EmpleadoRepository empleadoRepository,
                                      ExtemporaneoRepository extemporaneoRepository,
                                      JornadaEmpleadoRepository jornadaEmpleadoRepository,
                                      SemanaRepository semanaRepository) {
        this.diaRepository = diaRepository;
        this.fichajeRepository = fichajeRepository;
        this.empleadoRepository = empleadoRepository;
        this.extemporaneoRepository = extemporaneoRepository;
        this.jornadaEmpleadoRepository = jornadaEmpleadoRepository;
        this.semanaRepository = semanaRepository;
    }


    @PostConstruct
    public void init() {

        //todo arodriguez: comprobar si la base de datos tiene valores y si no popularla

        LocalDate today = LocalDate.now();
        int year = today.getYear();
        LocalDate startDate = LocalDate.of(year -1, 12, 29);
        LocalDate endDate = LocalDate.of(year, 12, 31);

        Semana semana = new Semana(startDate);
        semanaRepository.save(semana);

        for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
            Dia dia = new Dia(date, getDiaSemana(date), esFinSemana(date), false, semana);
            diaRepository.save(dia);

            if (dia.getDiaSemana().equals(EnumDiaSemana.DOMINGO)) {
                semana = new Semana(date);
                semanaRepository.save(semana);
            }
        }

        Empleado empleado = new Empleado("arodriguez");
        empleadoRepository.save(empleado);

        Empleado empleado2 = new Empleado("tutu");
        empleadoRepository.save(empleado2);


        diaRepository.findAll().stream()
                .filter(d -> d.getFecha().isBefore(LocalDate.now()))
                .filter(d -> !d.isFinSemana())
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

        LocalDateTime horaSalidaDesayuno = LocalDateTime.of(fechaBase, LocalTime.of(random.nextInt(1) + 10, 0, 0));
        SalidaDesayuno salidaDesayuno = new SalidaDesayuno(jornadaEmpleado);
        Extemporaneo ex3 = new Extemporaneo(salidaDesayuno, horaSalidaDesayuno);

        LocalDateTime horaEntradaDesayuno = horaSalidaDesayuno.plusMinutes(random.nextLong(16) + 15);
        EntradaDesayuno entradaDesayuno = new EntradaDesayuno(jornadaEmpleado);
        Extemporaneo ex4 = new Extemporaneo(entradaDesayuno, horaEntradaDesayuno);

        LocalDateTime horaSalidaComida = LocalDateTime.of(fechaBase, LocalTime.of(random.nextInt(1) + 13, 0, 0));
        SalidaComida salidaComida = new SalidaComida(jornadaEmpleado);
        Extemporaneo ex5 = new Extemporaneo(salidaComida, horaSalidaComida);

        LocalDateTime horaEntradaComida = horaSalidaComida.plusMinutes(random.nextLong(16) + 15);
        EntradaComida entradaComida = new EntradaComida(jornadaEmpleado);
        Extemporaneo ex6 = new Extemporaneo(entradaComida, horaEntradaComida);

        fichajeRepository.save(entradaJornada);
        fichajeRepository.save(salidaJornada);
        fichajeRepository.save(salidaDesayuno);
        fichajeRepository.save(entradaDesayuno);
        fichajeRepository.save(salidaComida);
        fichajeRepository.save(entradaComida);
        extemporaneoRepository.save(ex1);
        extemporaneoRepository.save(ex2);
        extemporaneoRepository.save(ex3);
        extemporaneoRepository.save(ex4);
        extemporaneoRepository.save(ex5);
        extemporaneoRepository.save(ex6);
    }

    private boolean esFinSemana(LocalDate date) {
        return date.getDayOfWeek().getValue() >= 6;
    }

    private EnumDiaSemana getDiaSemana(LocalDate date) {
        return EnumDiaSemana.fromLocalDate(date);
    }
}
