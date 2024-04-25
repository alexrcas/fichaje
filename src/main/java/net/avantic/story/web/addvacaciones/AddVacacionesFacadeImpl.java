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
    public void addVacaciones(AddVacacionesCommand command) {
        assertCommand(command);

        Empleado empleado = empleadoRepository.getReferenceById(command.getIdEmpleado());
        LocalDate fechaInicio = command.getFechaInicio();
        LocalDate fechaRegreso = command.getFechaFin();

        Vacaciones vacaciones = new Vacaciones();
        vacacionesRepository.save(vacaciones);

        for (LocalDate fecha = fechaInicio; fecha.isBefore(fechaRegreso); fecha = fecha.plusDays(1)) {
            Dia dia = diaService.getByFecha(fecha);
            DiaLibre diaLibre = new DiaLibre(empleado, dia, vacaciones);
            diaLibreRepository.save(diaLibre);
        }
    }

    private void assertCommand(AddVacacionesCommand command) {
        if (command.getFechaInicio() == null) {
            throw new RuntimeException("");
        }

        if (command.getFechaFin() == null) {
            throw new RuntimeException("");
        }

        if (command.getIdEmpleado() == null) {
            throw new RuntimeException("");
        }

        if (command.getFechaFin().isEqual(command.getFechaInicio())) {
            throw new RuntimeException("");
        }

        if (command.getFechaFin().isBefore(command.getFechaInicio())) {
            throw new RuntimeException("");
        }
    }
}
