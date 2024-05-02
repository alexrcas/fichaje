package net.avantic.story.web.listvacaciones;

import net.avantic.domain.dao.*;
import net.avantic.domain.model.Dia;
import net.avantic.domain.model.DiaLibre;
import net.avantic.domain.model.Empleado;
import net.avantic.domain.model.Vacaciones;
import net.avantic.domain.model.dto.DiaDto;
import net.avantic.domain.model.dto.EmpleadoDto;
import net.avantic.domain.model.dto.VacacionesDto;
import net.avantic.domain.model.dto.factory.EmpleadoDtoFactory;
import net.avantic.domain.service.DiaService;
import net.avantic.domain.service.FechaService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListVacacionesFacadeImpl implements ListVacacionesFacade {

    private final DiaService diaService;
    private final FechaService fechaService;
    private final VacacionesRepository vacacionesRepository;
    private final DiaLibreRepository diaLibreRepository;

    public ListVacacionesFacadeImpl(DiaService diaService,
                                    FechaService fechaService,
                                    VacacionesRepository vacacionesRepository,
                                    DiaLibreRepository diaLibreRepository) {
        this.diaService = diaService;
        this.fechaService = fechaService;
        this.vacacionesRepository = vacacionesRepository;
        this.diaLibreRepository = diaLibreRepository;
    }


    @Override
    public List<VacacionesDto> listVacaciones() {

        List<Vacaciones> vacaciones = vacacionesRepository
                .findAllByFechaInicioBetweenOrderByFechaInicioAsc(fechaService.getStartOfYear(), fechaService.getEndOfYear());

        List<VacacionesDto> vacacionesDtos = new ArrayList<>();
        for (Vacaciones vacacion : vacaciones) {

            List<DiaLibre> diasLibres = diaLibreRepository.findAllByVacacionesOrderByVacaciones_fechaInicioAsc(vacacion);
            Dia diaInicio = diaService.getByFecha(vacacion.getFechaInicio());
            Dia diaRegreso = diaService.getByFecha(vacacion.getFechaRegreso());

            DiaDto diaInicioDto = new DiaDto(diaInicio.getId(), diaInicio.getFecha(), diaInicio.getDiaSemana());
            DiaDto diaRegresoDto = new DiaDto(diaRegreso.getId(), diaRegreso.getFecha(), diaRegreso.getDiaSemana());

            Empleado empleado = diasLibres.get(0).getEmpleado();
            EmpleadoDto empleadoDto = EmpleadoDtoFactory.newDto(empleado);

            vacacionesDtos.add(new VacacionesDto(diaInicioDto, diaRegresoDto, diasLibres.size(), empleadoDto));
        }

        return vacacionesDtos;
    }


}
