package net.avantic.story.web.addvacaciones;

import jakarta.transaction.Transactional;
import net.avantic.domain.dao.DiaLibreRepository;
import net.avantic.domain.dao.EmpleadoRepository;
import net.avantic.domain.dao.VacacionesRepository;
import net.avantic.domain.model.Dia;
import net.avantic.domain.model.DiaLibre;
import net.avantic.domain.model.Empleado;
import net.avantic.domain.model.Vacaciones;
import net.avantic.domain.service.DiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class AddVacacionesFacadeImpl implements AddVacacionesFacade {

    private final EmpleadoRepository empleadoRepository;
    private final DiaLibreRepository diaLibreRepository;
    private final VacacionesRepository vacacionesRepository;
    private final DiaService diaService;

    @Autowired
    public AddVacacionesFacadeImpl(EmpleadoRepository empleadoRepository,
                                   DiaLibreRepository diaLibreRepository,
                                   VacacionesRepository vacacionesRepository,
                                   DiaService diaService) {
        this.empleadoRepository = empleadoRepository;
        this.diaLibreRepository = diaLibreRepository;
        this.vacacionesRepository = vacacionesRepository;
        this.diaService = diaService;
    }

    @Transactional
    @Override
    public void addVacaciones(AddVacacionesCommand command) throws Exception {

        assertCommand(command);

        Empleado empleado = empleadoRepository.getReferenceById(command.getIdEmpleado());
        LocalDate fechaInicio = command.getFechaInicio();
        LocalDate fechaRegreso = command.getFechaFin();

        Vacaciones vacaciones = new Vacaciones(fechaInicio, fechaRegreso);
        vacacionesRepository.save(vacaciones);

        for (LocalDate fecha = fechaInicio; fecha.isBefore(fechaRegreso); fecha = fecha.plusDays(1)) {
            Dia dia = diaService.getByFecha(fecha);
            if (!dia.isFestivo() && !dia.isFinSemana()) {
                DiaLibre diaLibre = new DiaLibre(empleado, dia, vacaciones);
                diaLibreRepository.save(diaLibre);
            }
        }
    }

    private void assertCommand(AddVacacionesCommand command) throws Exception {

        if (command.getFechaInicio() == null) {
            throw new Exception("No se ha especificado una fecha de inicio");
        }

        if (command.getFechaFin() == null) {
            throw new Exception("No se ha especificado una fecha de regreso");
        }


        if (command.getIdEmpleado() == null) {
            throw new Exception("No se ha especificado un empleado");
        }

        if (command.getFechaFin().isEqual(command.getFechaInicio())) {
            throw new Exception("Las fechas de inicio y regreso no pueden coincidir");
        }

        if (command.getFechaFin().isBefore(command.getFechaInicio())) {
            throw new Exception("La fecha de regreso no puede ser anterior a la fecha de inicio");
        }

        if (command.getFechaInicio().isBefore(LocalDate.now())) {
            throw new Exception("La fecha de inicio no puede ser anterior a hoy");
        }

        Dia diaInicio = diaService.getByFecha(command.getFechaInicio());
        if (diaInicio.isFinSemana() || diaInicio.isFestivo()) {
            throw new Exception("El día de inicio no puede ser un festivo o fin de semana");
        }

        Dia diaRegreso = diaService.getByFecha(command.getFechaFin());

        if (diaRegreso.isFinSemana() || diaInicio.isFestivo()) {
            throw new Exception("El día de regreso no puede ser un festivo o fin de semana");
        }

        Empleado empleado = empleadoRepository.getReferenceById(command.getIdEmpleado());
        for (LocalDate fecha = command.getFechaInicio(); fecha.isBefore(command.getFechaFin()); fecha = fecha.plusDays(1)) {
            Dia dia = diaService.getByFecha(fecha);
            Optional<DiaLibre> diaLibre = diaLibreRepository.findByDiaAndEmpleado(dia, empleado);
            if (diaLibre.isPresent()) {
                throw new RuntimeException("Las vacaciones introducidas se solapan con otras vacaciones existentes del mismo empleado");
            }
        }
    }

}
