package net.avantic.utils;

import jakarta.annotation.PostConstruct;
import net.avantic.domain.dao.*;
import net.avantic.domain.model.*;
import net.avantic.domain.model.dto.EnumDiaSemana;
import net.avantic.domain.service.DiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
public class CalendarInitializerService {

    private final DiaRepository diaRepository;
    private final FichajeRepository fichajeRepository;
    private final EmpleadoRepository empleadoRepository;
    private final ExtemporaneoRepository extemporaneoRepository;
    private final JornadaEmpleadoRepository jornadaEmpleadoRepository;
    private final SemanaRepository semanaRepository;
    private final DiaService diaService;
    private final VacacionesRepository vacacionesRepository;
    private final DiaLibreRepository diaLibreRepository;
    private final AusenciaJustificadaRepository ausenciaJustificadaRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public CalendarInitializerService(DiaRepository diaRepository,
                                      FichajeRepository fichajeRepository,
                                      EmpleadoRepository empleadoRepository,
                                      ExtemporaneoRepository extemporaneoRepository,
                                      JornadaEmpleadoRepository jornadaEmpleadoRepository,
                                      SemanaRepository semanaRepository,
                                      DiaService diaService,
                                      VacacionesRepository vacacionesRepository,
                                      DiaLibreRepository diaLibreRepository,
                                      AusenciaJustificadaRepository ausenciaJustificadaRepository,
                                      RoleRepository roleRepository) {
        this.diaRepository = diaRepository;
        this.fichajeRepository = fichajeRepository;
        this.empleadoRepository = empleadoRepository;
        this.extemporaneoRepository = extemporaneoRepository;
        this.jornadaEmpleadoRepository = jornadaEmpleadoRepository;
        this.semanaRepository = semanaRepository;
        this.diaService = diaService;
        this.vacacionesRepository = vacacionesRepository;
        this.diaLibreRepository = diaLibreRepository;
        this.ausenciaJustificadaRepository = ausenciaJustificadaRepository;
        this.roleRepository = roleRepository;
    }


    @PostConstruct
    public void init() {

        LocalDate today = LocalDate.now();
        int year = today.getYear();

        Optional<Dia> dia = diaRepository.findByFecha(today);
        if (dia.isEmpty()) {

            //popular calendario
            LocalDate startDate = LocalDate.of(year -1, 12, 24);
            Optional<Dia> startDay = diaRepository.findByFecha(startDate);
            if (startDay.isEmpty()) {
                //Si el año pasado tampoco tengo registros es la primera vez que se arranca la app
                populateCalendar(startDate);
            } else {
                //Si no, ya se había arrancado pero se ha quedado sin registros
                populateCalendar(LocalDate.of(year, 1, 8));
            }

        }


        Empleado empleado = new Empleado("arodriguez", "Alexis", "Rodríguez Casañas", "$2y$10$zKBRDvB6vZirAEkQ4Mye4uiqF64Ss7KGhwwrEIO2/UODlq9Uksdq2", false);
        empleadoRepository.save(empleado);

        Role role = new Role("ROLE_ADMIN", empleado);
        roleRepository.save(role);

        Empleado empleado2 = new Empleado("tutu", "Turing", "Turruto", "$2y$10$zKBRDvB6vZirAEkQ4Mye4uiqF64Ss7KGhwwrEIO2/UODlq9Uksdq2", false);
        empleadoRepository.save(empleado2);

        Role role2 = new Role("ROLE_USER", empleado2);
        roleRepository.save(role2);

        diaRepository.findAll().stream()
                .filter(d -> d.getFecha().isBefore(LocalDate.now()))
                .filter(d -> !d.isFinSemana())
                .map(d -> new JornadaEmpleado(empleado, d, true))
                .map(jornadaEmpleadoRepository::save)
                .forEach(this::crearFichajes);

    }

    private void populateCalendar(LocalDate startDate) {
        LocalDate endDate = LocalDate.of(startDate.getYear() + 6, 1, 7);
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
    }


    private void crearFichajes(JornadaEmpleado jornadaEmpleado) {
        Random random = new Random();

        LocalDate fechaBase = jornadaEmpleado.getDia().getFecha();

        LocalDateTime horaEntradaJornada = LocalDateTime.of(fechaBase, LocalTime.of(random.nextInt(1) + 7, 0, 0));
        EntradaJornada entradaJornada = new EntradaJornada(jornadaEmpleado);
        Extemporaneo ex1 = new Extemporaneo(entradaJornada, horaEntradaJornada);

        LocalDateTime horaSalidaJornada = LocalDateTime.of(fechaBase, LocalTime.of(random.nextInt(2) + 15, 0, 0));
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
