package net.avantic.story.web.listempleados;

import net.avantic.domain.dao.EmpleadoRepository;
import net.avantic.domain.model.dto.EmpleadoDto;
import net.avantic.domain.model.dto.factory.EmpleadoDtoFactory;
import net.avantic.domain.service.SecurityUtilsService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListEmpleadosFacadeImpl implements ListEmpleadosFacade {

    private final EmpleadoRepository empleadoRepository;
    private final SecurityUtilsService securityUtilsService;

    public ListEmpleadosFacadeImpl(EmpleadoRepository empleadoRepository,
                                   SecurityUtilsService securityUtilsService) {
        this.empleadoRepository = empleadoRepository;
        this.securityUtilsService = securityUtilsService;
    }

    @Override
    public List<EmpleadoDto> listEmpleados() {
        return empleadoRepository.findAll().stream()
                .map(EmpleadoDtoFactory::newDto)
                .collect(Collectors.toList());
    }

    @Override
    public String getAuthenticatedUsername() {
        return securityUtilsService.getAuthenticatedUser().getEmail();
    }
}
