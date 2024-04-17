package net.avantic.story.web.listempleados;

import net.avantic.domain.dao.EmpleadoRepository;
import net.avantic.domain.model.dto.EmpleadoDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListEmpleadosFacadeImpl implements ListEmpleadosFacade {

    private final EmpleadoRepository empleadoRepository;

    public ListEmpleadosFacadeImpl(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public List<EmpleadoDto> listEmpleados() {
        return empleadoRepository.findAll().stream()
                .map(e -> new EmpleadoDto(e.getId(), e.getEmail()))
                .collect(Collectors.toList());
    }
}
