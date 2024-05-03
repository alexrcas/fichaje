package net.avantic.story.web.listsolicitudesanulacion;

import net.avantic.domain.dao.DiaLibreRepository;
import net.avantic.domain.dao.VacacionesRepository;
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
import net.avantic.domain.service.SecurityUtilsService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListSolicitudesAnulacionFacadeImpl implements ListSolicitudesAnulacionFacade {

    private final DiaService diaService;
    private final FechaService fechaService;
    private final VacacionesRepository vacacionesRepository;
    private final DiaLibreRepository diaLibreRepository;
    private final SecurityUtilsService securityUtilsService;

    public ListSolicitudesAnulacionFacadeImpl(DiaService diaService,
                                              FechaService fechaService,
                                              VacacionesRepository vacacionesRepository,
                                              DiaLibreRepository diaLibreRepository,
                                              SecurityUtilsService securityUtilsService) {
        this.diaService = diaService;
        this.fechaService = fechaService;
        this.vacacionesRepository = vacacionesRepository;
        this.diaLibreRepository = diaLibreRepository;
        this.securityUtilsService = securityUtilsService;
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

    @Override
    public String getAuthenticatedUsername() {
        return securityUtilsService.getAuthenticatedUser().getEmail();
    }


}
