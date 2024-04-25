package net.avantic.domain.model.dto.factory;

import net.avantic.domain.dao.DiaLibreRepository;
import net.avantic.domain.dao.JornadaEmpleadoRepository;
import net.avantic.domain.model.Dia;
import net.avantic.domain.model.Empleado;
import net.avantic.domain.model.dto.DiaCalendarioDto;
import net.avantic.domain.model.dto.JornadaDto;
import net.avantic.domain.model.dto.JornadasEmpleadoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DiaCalendarioDtoFactory {

    private final JornadaEmpleadoRepository jornadaEmpleadoRepository;
    private final JornadaEmpleadoDtoFactory jornadaEmpleadoDtoFactory;
    private final DiaLibreRepository diaLibreRepository;

    @Autowired
    public DiaCalendarioDtoFactory(JornadaEmpleadoRepository jornadaEmpleadoRepository,
                                   JornadaEmpleadoDtoFactory jornadaEmpleadoDtoFactory,
                                   DiaLibreRepository diaLibreRepository) {
        this.jornadaEmpleadoRepository = jornadaEmpleadoRepository;
        this.jornadaEmpleadoDtoFactory = jornadaEmpleadoDtoFactory;
        this.diaLibreRepository = diaLibreRepository;
    }

    public DiaCalendarioDto newDto(Dia dia, Empleado empleado) {

        boolean vacaciones = diaLibreRepository.findByDiaAndEmpleado(dia, empleado).isPresent();

        JornadaDto jornadaDto = jornadaEmpleadoRepository.findByDiaAndEmpleado(dia, empleado)
                .map(jornadaEmpleadoDtoFactory::newDto)
                .orElse(null);

        return new DiaCalendarioDto(jornadaDto, dia.isFestivo(), vacaciones, dia.getFecha());
    }
}
