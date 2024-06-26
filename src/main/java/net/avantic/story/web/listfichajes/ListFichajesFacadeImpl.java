package net.avantic.story.web.listfichajes;

import jakarta.transaction.Transactional;
import net.avantic.domain.dao.*;
import net.avantic.domain.model.*;
import net.avantic.domain.model.dto.*;
import net.avantic.domain.model.dto.factory.EmpleadoDtoFactory;
import net.avantic.domain.model.dto.factory.SemanaJornadaDtoFactory;
import net.avantic.domain.service.DiaService;
import net.avantic.domain.service.FechaService;
import net.avantic.domain.service.FichajeService;
import net.avantic.domain.service.SecurityUtilsService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class ListFichajesFacadeImpl implements ListFichajesFacade {

    private final JornadaEmpleadoRepository jornadaEmpleadoRepository;
    private final FichajeService fichajeService;
    private final DiaService diaService;
    private final SemanaRepository semanaRepository;
    private final FechaService fechaService;
    private final SemanaJornadaDtoFactory semanaJornadaDtoFactory;
    private final SecurityUtilsService securityUtilsService;
    private final EmpleadoRepository empleadoRepository;

    public ListFichajesFacadeImpl(JornadaEmpleadoRepository jornadaEmpleadoRepository,
                                  FichajeService fichajeService,
                                  DiaService diaService,
                                  SemanaRepository semanaRepository,
                                  FechaService fechaService,
                                  SemanaJornadaDtoFactory semanaJornadaDtoFactory,
                                  SecurityUtilsService securityUtilsService,
                                  EmpleadoRepository empleadoRepository) {
        this.jornadaEmpleadoRepository = jornadaEmpleadoRepository;
        this.fichajeService = fichajeService;
        this.diaService = diaService;
        this.semanaRepository = semanaRepository;
        this.fechaService = fechaService;
        this.semanaJornadaDtoFactory = semanaJornadaDtoFactory;
        this.securityUtilsService = securityUtilsService;
        this.empleadoRepository = empleadoRepository;
    }




    @Override
    @Transactional
    public List<SemanaJornadaDto> listJornadas() {
        Empleado empleado = securityUtilsService.getAuthenticatedUser();
        return semanaRepository.findAllByFechaBetween(fechaService.getStartOfYear(), fechaService.getEndOfYear()).stream()
                .map(s -> semanaJornadaDtoFactory.newDto(s, empleado))
                .toList();
    }

    @Override
    public List<EnumTipoFichaje> listOpciones() {
        return Arrays.asList(EnumTipoFichaje.values());
    }

    public EnumTipoFichaje getOpcionSugerida() {
        Dia dia = diaService.getByFecha(LocalDate.now());
        Empleado empleado = securityUtilsService.getAuthenticatedUser();
        Optional<JornadaEmpleado> jornadaEmpleado = jornadaEmpleadoRepository.findByDiaAndEmpleado(dia, empleado);
        if (jornadaEmpleado.isEmpty()) {
            return EnumTipoFichaje.ENTRADA_JORNADA;
        }

        return fichajeService.getFichajeSugerido(jornadaEmpleado.get());
    }

    @Override
    public boolean isAdmin() {
        return securityUtilsService.listAuthenticatedUserRoles().stream()
                .map(Role::getName)
                .anyMatch("ROLE_ADMIN"::equals);
    }

    @Override
    public String getAuthenticatedUsername() {
        return securityUtilsService.getAuthenticatedUser().getEmail();
    }

    @Override
    public List<EmpleadoDto> listEmpleados() {
        Empleado empleadoAutenticado = securityUtilsService.getAuthenticatedUser();

        return empleadoRepository.findAll().stream()
                .filter(e -> !empleadoAutenticado.getEmail().equals(e.getEmail()))
                .map(EmpleadoDtoFactory::newDto)
                .toList();
    }


}
