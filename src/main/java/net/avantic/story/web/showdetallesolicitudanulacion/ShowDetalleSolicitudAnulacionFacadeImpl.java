package net.avantic.story.web.showdetallesolicitudanulacion;

import net.avantic.domain.dao.AusenciaJustificadaRepository;
import net.avantic.domain.dao.FichajeRepository;
import net.avantic.domain.dao.JornadaEmpleadoRepository;
import net.avantic.domain.model.Fichaje;
import net.avantic.domain.model.JornadaEmpleado;
import net.avantic.domain.model.Role;
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
public class ShowDetalleSolicitudAnulacionFacadeImpl implements ShowDetalleSolicitudAnulacionFacade {

    private final JornadaEmpleadoRepository jornadaEmpleadoRepository;
    private final ValidarJornadaService validarJornadaService;
    private final FichajeService fichajeService;
    private final FichajeDtoFactory fichajeDtoFactory;
    private final FichajeRepository fichajeRepository;
    private final ComputoDtoFactory computoDtoFactory;
    private final AusenciaJustificadaRepository ausenciaJustificadaRepository;
    private final SecurityUtilsService securityUtilsService;

    @Autowired
    public ShowDetalleSolicitudAnulacionFacadeImpl(JornadaEmpleadoRepository jornadaEmpleadoRepository,
                                                   ValidarJornadaService validarJornadaService,
                                                   FichajeService fichajeService,
                                                   FichajeDtoFactory fichajeDtoFactory,
                                                   FichajeRepository fichajeRepository,
                                                   ComputoDtoFactory computoDtoFactory,
                                                   AusenciaJustificadaRepository ausenciaJustificadaRepository,
                                                   SecurityUtilsService securityUtilsService) {
        this.jornadaEmpleadoRepository = jornadaEmpleadoRepository;
        this.validarJornadaService = validarJornadaService;
        this.fichajeService = fichajeService;
        this.fichajeDtoFactory = fichajeDtoFactory;
        this.fichajeRepository = fichajeRepository;
        this.computoDtoFactory = computoDtoFactory;
        this.ausenciaJustificadaRepository = ausenciaJustificadaRepository;
        this.securityUtilsService = securityUtilsService;
    }

    @Override
    public List<FichajeDto> listFichajesJornada(Long id) {
        Fichaje fichaje = fichajeRepository.get(id);
        JornadaEmpleado jornada = fichaje.getJornadaEmpleado();

        return fichajeService.listFichajesOrdenJornada(jornada).stream()
                .map(fichajeDtoFactory::newDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResultadoValidacionJornadaDto listValidacionesJornada(Long idFichaje) {
        Fichaje fichaje = fichajeRepository.get(idFichaje);
        JornadaEmpleado jornada = fichaje.getJornadaEmpleado();
        return validarJornadaService.validar(jornada);
    }

    @Override
    public DiaDto getFechaJornada(Long idFichaje) {
        Fichaje fichaje = fichajeRepository.get(idFichaje);
        JornadaEmpleado jornada = fichaje.getJornadaEmpleado();
        return new DiaDto(jornada.getDia().getId(), jornada.getDia().getFecha(), jornada.getDia().getDiaSemana());
    }

    @Override
    public ComputoDto getComputo(Long idFichaje) {
        Fichaje fichaje = fichajeRepository.get(idFichaje);
        JornadaEmpleado jornada = fichaje.getJornadaEmpleado();
        return computoDtoFactory.newDto(jornada);
    }

    @Override
    public List<AusenciaJustificadaDto> listAusenciasJustificadas(Long idFichaje) {
        Fichaje fichaje = fichajeRepository.get(idFichaje);
        JornadaEmpleado jornada = fichaje.getJornadaEmpleado();
        return ausenciaJustificadaRepository.findAllByJornadaEmpleado(jornada).stream()
                .map(ausencia -> new AusenciaJustificadaDto(ausencia.getHoras(), ausencia.getMotivo()))
                .toList();
    }

    @Override
    public String getUsernameEmpleadoFichaje(Long idFichaje) {
        Fichaje fichaje = fichajeRepository.get(idFichaje);
        JornadaEmpleado jornada = fichaje.getJornadaEmpleado();
        return jornada.getEmpleado().getEmail();
    }

    @Override
    public boolean isAdmin() {
        return securityUtilsService.listAuthenticatedUserRoles().stream()
                .map(Role::getName)
                .anyMatch("ROLE_ADMIN"::equals);
    }

}
