package net.avantic.story.web.showdetallejornada;

import net.avantic.domain.dao.ExtemporaneoRepository;
import net.avantic.domain.dao.FichajeRepository;
import net.avantic.domain.dao.JornadaEmpleadoRepository;
import net.avantic.domain.model.*;
import net.avantic.domain.model.dto.DiaDto;
import net.avantic.domain.model.dto.FichajeDto;
import net.avantic.domain.model.dto.ResultadoValidacionJornadaDto;
import net.avantic.domain.model.dto.factory.FichajeDtoFactory;
import net.avantic.domain.service.FichajeService;
import net.avantic.domain.service.ValidarJornadaService;
import net.avantic.utils.FichajeVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShowDetalleJornadaFacadeImpl implements ShowDetalleJornadaFacade {

    private final JornadaEmpleadoRepository jornadaEmpleadoRepository;
    private final ValidarJornadaService validarJornadaService;
    private final FichajeService fichajeService;
    private final FichajeDtoFactory fichajeDtoFactory;

    @Autowired
    public ShowDetalleJornadaFacadeImpl(JornadaEmpleadoRepository jornadaEmpleadoRepository,
                                        ValidarJornadaService validarJornadaService,
                                        FichajeService fichajeService,
                                        FichajeDtoFactory fichajeDtoFactory) {
        this.jornadaEmpleadoRepository = jornadaEmpleadoRepository;
        this.validarJornadaService = validarJornadaService;
        this.fichajeService = fichajeService;
        this.fichajeDtoFactory = fichajeDtoFactory;
    }

    @Override
    public List<FichajeDto> listFichajesJornada(Long id) {
        JornadaEmpleado jornada = jornadaEmpleadoRepository.getById(id);

        return fichajeService.listFichajesOrdenJornada(jornada).stream()
                .map(fichajeDtoFactory::newDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResultadoValidacionJornadaDto listValidacionesJornada(Long idJornada) {
        JornadaEmpleado jornadaEmpleado = jornadaEmpleadoRepository.getById(idJornada);
        return validarJornadaService.validar(jornadaEmpleado);
    }

    @Override
    public DiaDto getFechaJornada(Long idJornada) {
        JornadaEmpleado jornadaEmpleado = jornadaEmpleadoRepository.getById(idJornada);
        return new DiaDto(jornadaEmpleado.getDia().getId(), jornadaEmpleado.getDia().getFecha(), jornadaEmpleado.getDia().getDiaSemana());
    }

}
