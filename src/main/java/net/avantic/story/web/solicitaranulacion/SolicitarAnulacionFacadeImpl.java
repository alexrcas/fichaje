package net.avantic.story.web.solicitaranulacion;

import jakarta.transaction.Transactional;
import net.avantic.domain.dao.EmpleadoRepository;
import net.avantic.domain.dao.FichajeRepository;
import net.avantic.domain.dao.SolicitudAnulacionRepository;
import net.avantic.domain.model.Fichaje;
import net.avantic.domain.model.SolicitudAnulacion;
import net.avantic.domain.service.DiaService;
import net.avantic.domain.service.FichajeService;
import net.avantic.domain.service.SecurityUtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SolicitarAnulacionFacadeImpl implements SolicitarAnulacionFacade {

    private final SolicitudAnulacionRepository solicitudAnulacionRepository;
    private final FichajeRepository fichajeRepository;

    @Autowired
    public SolicitarAnulacionFacadeImpl(SolicitudAnulacionRepository solicitudAnulacionRepository,
                                        FichajeRepository fichajeRepository) {

        this.solicitudAnulacionRepository = solicitudAnulacionRepository;
        this.fichajeRepository = fichajeRepository;
    }

    @Transactional
    @Override
    public void solicitarAnulacion(SolicitarAnulacionCommand command) {
        Fichaje fichaje = fichajeRepository.get(command.getIdFichaje());

        assertNoHayAnulacionPendiente(fichaje);

        SolicitudAnulacion solicitudAnulacion = new SolicitudAnulacion(fichaje);
        solicitudAnulacionRepository.save(solicitudAnulacion);
    }


    private void assertNoHayAnulacionPendiente(Fichaje fichaje) {
        if (!solicitudAnulacionRepository.findAllByFichaje(fichaje).isEmpty()) {
            throw new RuntimeException("Ya hay una solicitud de anulación pendiente para este fichaje");
        }
    }

}
