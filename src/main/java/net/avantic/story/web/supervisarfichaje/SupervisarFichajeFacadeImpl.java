package net.avantic.story.web.supervisarfichaje;

import jakarta.transaction.Transactional;
import net.avantic.domain.dao.EmpleadoRepository;
import net.avantic.domain.dao.JornadaEmpleadoRepository;
import net.avantic.domain.dao.SemanaRepository;
import net.avantic.domain.model.*;
import net.avantic.domain.model.dto.SemanaJornadaDto;
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
public class SupervisarFichajeFacadeImpl implements SupervisarFichajeFacade {

    private final EmpleadoRepository empleadoRepository;
    private final SemanaRepository semanaRepository;
    private final FechaService fechaService;
    private final SemanaJornadaDtoFactory semanaJornadaDtoFactory;
    private final SecurityUtilsService securityUtilsService;

    public SupervisarFichajeFacadeImpl(EmpleadoRepository empleadoRepository,
                                       SemanaRepository semanaRepository,
                                       FechaService fechaService,
                                       SemanaJornadaDtoFactory semanaJornadaDtoFactory,
                                       SecurityUtilsService securityUtilsService) {
        this.empleadoRepository = empleadoRepository;
        this.semanaRepository = semanaRepository;
        this.fechaService = fechaService;
        this.semanaJornadaDtoFactory = semanaJornadaDtoFactory;
        this.securityUtilsService = securityUtilsService;
    }




    @Override
    @Transactional
    public List<SemanaJornadaDto> listJornadas(Long idEmpleado) {
        Empleado empleado = empleadoRepository.getById(idEmpleado);
        return semanaRepository.findAllByFechaBetween(fechaService.getStartOfYear(), fechaService.getEndOfYear()).stream()
                .map(s -> semanaJornadaDtoFactory.newDto(s, empleado))
                .toList();
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


}
