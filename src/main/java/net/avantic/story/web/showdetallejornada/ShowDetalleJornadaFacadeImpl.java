package net.avantic.story.web.showdetallejornada;

import net.avantic.domain.dao.AusenciaJustificadaRepository;
import net.avantic.domain.dao.JornadaEmpleadoRepository;
import net.avantic.domain.model.*;
import net.avantic.domain.model.dto.*;
import net.avantic.domain.model.dto.factory.ComputoDtoFactory;
import net.avantic.domain.model.dto.factory.FichajeDtoFactory;
import net.avantic.domain.service.FichajeService;
import net.avantic.domain.service.SecurityUtilsService;
import net.avantic.domain.service.ValidarJornadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShowDetalleJornadaFacadeImpl implements ShowDetalleJornadaFacade {

    private final JornadaEmpleadoRepository jornadaEmpleadoRepository;
    private final ValidarJornadaService validarJornadaService;
    private final FichajeService fichajeService;
    private final FichajeDtoFactory fichajeDtoFactory;
    private final ComputoDtoFactory computoDtoFactory;
    private final AusenciaJustificadaRepository ausenciaJustificadaRepository;
    private final SecurityUtilsService securityUtilsService;

    @Autowired
    public ShowDetalleJornadaFacadeImpl(JornadaEmpleadoRepository jornadaEmpleadoRepository,
                                        ValidarJornadaService validarJornadaService,
                                        FichajeService fichajeService,
                                        FichajeDtoFactory fichajeDtoFactory,
                                        ComputoDtoFactory computoDtoFactory,
                                        AusenciaJustificadaRepository ausenciaJustificadaRepository,
                                        SecurityUtilsService securityUtilsService) {
        this.jornadaEmpleadoRepository = jornadaEmpleadoRepository;
        this.validarJornadaService = validarJornadaService;
        this.fichajeService = fichajeService;
        this.fichajeDtoFactory = fichajeDtoFactory;
        this.computoDtoFactory = computoDtoFactory;
        this.ausenciaJustificadaRepository = ausenciaJustificadaRepository;
        this.securityUtilsService = securityUtilsService;
    }

    @Override
    public List<FichajeDto> listFichajesJornada(Long id) {
        JornadaEmpleado jornada = jornadaEmpleadoRepository.get(id);

        return fichajeService.listFichajesOrdenJornada(jornada).stream()
                .map(fichajeDtoFactory::newDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResultadoValidacionJornadaDto listValidacionesJornada(Long idJornada) {
        JornadaEmpleado jornadaEmpleado = jornadaEmpleadoRepository.get(idJornada);
        return validarJornadaService.validar(jornadaEmpleado);
    }

    @Override
    public DiaDto getFechaJornada(Long idJornada) {
        JornadaEmpleado jornadaEmpleado = jornadaEmpleadoRepository.get(idJornada);
        return new DiaDto(jornadaEmpleado.getDia().getId(), jornadaEmpleado.getDia().getFecha(), jornadaEmpleado.getDia().getDiaSemana());
    }

    @Override
    public ComputoDto getComputo(Long idJornada) {
        JornadaEmpleado jornadaEmpleado = jornadaEmpleadoRepository.get(idJornada);
        return computoDtoFactory.newDto(jornadaEmpleado);
    }

    @Override
    public List<AusenciaJustificadaDto> listAusenciasJustificadas(Long idJornada) {
        JornadaEmpleado jornadaEmpleado = jornadaEmpleadoRepository.get(idJornada);
        return ausenciaJustificadaRepository.findAllByJornadaEmpleado(jornadaEmpleado).stream()
                .map(ausencia -> new AusenciaJustificadaDto(ausencia.getHoras(), ausencia.getMotivo()))
                .toList();
    }

    @Override
    public String getUsernameEmpleadoFichaje(Long idJornada) {
        JornadaEmpleado jornadaEmpleado = jornadaEmpleadoRepository.get(idJornada);
        return jornadaEmpleado.getEmpleado().getEmail();
    }

    @Override
    public boolean isAdmin() {
        return securityUtilsService.listAuthenticatedUserRoles().stream()
                .map(Role::getName)
                .anyMatch("ROLE_ADMIN"::equals);
    }

}
