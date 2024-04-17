package net.avantic.story.web.showdetallejornada;

import net.avantic.domain.dao.FichajeRepository;
import net.avantic.domain.dao.JornadaEmpleadoRepository;
import net.avantic.domain.model.JornadaEmpleado;
import net.avantic.domain.model.dto.FichajeDto;
import net.avantic.domain.model.dto.factory.FichajeDtoFactory;
import net.avantic.domain.service.ValidarJornadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShowDetalleJornadaFacadeImpl implements ShowDetalleJornadaFacade {

    private final JornadaEmpleadoRepository jornadaEmpleadoRepository;
    private final ValidarJornadaService validarJornadaService;
    private final FichajeRepository fichajeRepository;
    private final FichajeDtoFactory fichajeDtoFactory;

    @Autowired
    public ShowDetalleJornadaFacadeImpl(JornadaEmpleadoRepository jornadaEmpleadoRepository,
                                        ValidarJornadaService validarJornadaService,
                                        FichajeRepository fichajeRepository,
                                        FichajeDtoFactory fichajeDtoFactory) {
        this.jornadaEmpleadoRepository = jornadaEmpleadoRepository;
        this.validarJornadaService = validarJornadaService;
        this.fichajeRepository = fichajeRepository;
        this.fichajeDtoFactory = fichajeDtoFactory;
    }

    @Override
    public List<FichajeDto> listFichajesJornada(Long id) {
        JornadaEmpleado jornada = jornadaEmpleadoRepository.getById(id);
        return fichajeRepository.findAllByJornadaEmpleadoOrderByCreatedAsc(jornada).stream()
                .map(fichajeDtoFactory::newDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> listValidacionesJornada(Long idJornada) {
        JornadaEmpleado jornadaEmpleado = jornadaEmpleadoRepository.getById(idJornada);
        return validarJornadaService.validar(jornadaEmpleado);
    }
}
