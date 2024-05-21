package net.avantic.story.web.listsolicitudesanulacion;

import net.avantic.domain.dao.DiaLibreRepository;
import net.avantic.domain.dao.SolicitudAnulacionRepository;
import net.avantic.domain.dao.VacacionesRepository;
import net.avantic.domain.model.dto.SolicitudAnulacionDto;
import net.avantic.domain.model.dto.VacacionesDto;
import net.avantic.domain.model.dto.factory.SolicitudAnulacionDtoFactory;
import net.avantic.domain.service.DiaService;
import net.avantic.domain.service.FechaService;
import net.avantic.domain.service.SecurityUtilsService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListSolicitudesAnulacionFacadeImpl implements ListSolicitudesAnulacionFacade {

    private final SolicitudAnulacionRepository solicitudAnulacionRepository;
    private final SolicitudAnulacionDtoFactory solicitudAnulacionDtoFactory;
    private final SecurityUtilsService securityUtilsService;

    public ListSolicitudesAnulacionFacadeImpl(SolicitudAnulacionRepository solicitudAnulacionRepository,
                                              SolicitudAnulacionDtoFactory solicitudAnulacionDtoFactory,
                                              SecurityUtilsService securityUtilsService) {
        this.solicitudAnulacionRepository = solicitudAnulacionRepository;
        this.solicitudAnulacionDtoFactory = solicitudAnulacionDtoFactory;
        this.securityUtilsService = securityUtilsService;
    }


    @Override
    public List<SolicitudAnulacionDto> listSolicitudesAnulacion() {
        return solicitudAnulacionRepository.findAllByPendienteAnular().stream()
                .map(solicitudAnulacionDtoFactory::newDto)
                .collect(Collectors.toList());
    }

    @Override
    public String getAuthenticatedUsername() {
        return securityUtilsService.getAuthenticatedUser().getEmail();
    }


}
