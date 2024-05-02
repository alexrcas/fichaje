package net.avantic.story.web.listausenciasjustificadas;

import net.avantic.domain.dao.*;
import net.avantic.domain.model.dto.*;
import net.avantic.domain.model.dto.factory.EmpleadoDtoFactory;
import net.avantic.domain.service.DiaService;
import net.avantic.domain.service.FechaService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListAusenciasJustificadasFacadeImpl implements ListAusenciasJustificadasFacade {

    private final EmpleadoRepository empleadoRepository;
    private final JornadaEmpleadoRepository jornadaEmpleadoRepository;
    private final AusenciaJustificadaRepository ausenciaJustificadaRepository;

    public ListAusenciasJustificadasFacadeImpl(EmpleadoRepository empleadoRepository,
                                               JornadaEmpleadoRepository jornadaEmpleadoRepository,
                                               AusenciaJustificadaRepository ausenciaJustificadaRepository) {
        this.empleadoRepository = empleadoRepository;
        this.jornadaEmpleadoRepository = jornadaEmpleadoRepository;
        this.ausenciaJustificadaRepository = ausenciaJustificadaRepository;
    }


    @Override
    public List<LineaAusenciaJustificadaDto> listAusencias() {
        return empleadoRepository.findAll().stream()
                    .map(empleado -> {
                        EmpleadoDto empleadoDto = EmpleadoDtoFactory.newDto(empleado);
                        return jornadaEmpleadoRepository.findAllByEmpleado(empleado).stream()
                                .map(ausenciaJustificadaRepository::findAllByJornadaEmpleado)
                                .flatMap(List::stream)
                                .map(a -> new LineaAusenciaJustificadaDto(empleadoDto, a.getJornadaEmpleado().getDia().getFecha(), a.getHoras(), a.getMotivo()))
                                .toList();
                    })
                    .flatMap(List::stream)
                    .toList();

    }


}
