package net.avantic.story.web.listfichajes;

import jakarta.transaction.Transactional;
import net.avantic.domain.dao.DiaRepository;
import net.avantic.domain.dao.EmpleadoRepository;
import net.avantic.domain.dao.JornadaEmpleadoRepository;
import net.avantic.domain.model.Empleado;
import net.avantic.domain.model.dto.DiaDto;
import net.avantic.domain.model.dto.EmpleadoDto;
import net.avantic.domain.model.dto.JornadaDto;
import net.avantic.domain.model.dto.JornadasEmpleadoDto;
import net.avantic.domain.model.dto.factory.JornadaEmpleadoDtoFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListFichajesFacadeImpl implements ListFichajesFacade {

    private final EmpleadoRepository empleadoRepository;
    private final DiaRepository diaRepository;
    private final JornadaEmpleadoRepository jornadaEmpleadoRepository;
    private final JornadaEmpleadoDtoFactory jornadaEmpleadoDtoFactory;

    public ListFichajesFacadeImpl(EmpleadoRepository empleadoRepository,
                                  DiaRepository diaRepository,
                                  JornadaEmpleadoRepository jornadaEmpleadoRepository,
                                  JornadaEmpleadoDtoFactory jornadaEmpleadoDtoFactory) {
        this.empleadoRepository = empleadoRepository;
        this.diaRepository = diaRepository;
        this.jornadaEmpleadoRepository = jornadaEmpleadoRepository;
        this.jornadaEmpleadoDtoFactory = jornadaEmpleadoDtoFactory;
    }

    @Override
    public List<DiaDto> listDias() {
        return diaRepository.findAllByFestivoOrderByIdAsc(false).stream()
                .map(d -> new DiaDto(d.getId(), d.getFecha(), d.getDiaSemana()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<JornadasEmpleadoDto> listJornadas() {

        List<JornadasEmpleadoDto> jornadasEmpleados = new ArrayList<>();

        for (Empleado empleado : empleadoRepository.findAll()) {

            List<JornadaDto> jornadas = diaRepository.findAllByFestivoOrderByIdAsc(false).stream()
                    .map(d -> jornadaEmpleadoRepository.findByDiaAndEmpleado(d, empleado)
                                .map(jornadaEmpleadoDtoFactory::newDto)
                                .orElse(JornadaDto.emptyDto())
                    )
                    .collect(Collectors.toList());

            EmpleadoDto empleadoDto = new EmpleadoDto(empleado.getId(), empleado.getEmail());
            JornadasEmpleadoDto jornadasEmpleadoDto = new JornadasEmpleadoDto(empleadoDto, jornadas);
            jornadasEmpleados.add(jornadasEmpleadoDto);
        }

        return jornadasEmpleados;
    }
}
