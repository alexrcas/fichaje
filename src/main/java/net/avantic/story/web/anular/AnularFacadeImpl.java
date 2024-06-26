package net.avantic.story.web.anular;

import jakarta.transaction.Transactional;
import net.avantic.domain.dao.AnulacionFichajeRepository;
import net.avantic.domain.dao.FichajeRepository;
import net.avantic.domain.dao.JornadaEmpleadoRepository;
import net.avantic.domain.dao.SolicitudAnulacionRepository;
import net.avantic.domain.model.AnulacionFichaje;
import net.avantic.domain.model.Fichaje;
import net.avantic.domain.model.JornadaEmpleado;
import net.avantic.domain.model.SolicitudAnulacion;
import net.avantic.domain.model.dto.FichajeOrdenJornadaSpecification;
import net.avantic.domain.service.FichajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AnularFacadeImpl implements AnularFacade {

    private final SolicitudAnulacionRepository solicitudAnulacionRepository;
    private final AnulacionFichajeRepository anulacionFichajeRepository;
    private final FichajeRepository fichajeRepository;
    private final FichajeService fichajeService;

    @Autowired
    public AnularFacadeImpl(SolicitudAnulacionRepository solicitudAnulacionRepository,
                            AnulacionFichajeRepository anulacionFichajeRepository,
                            FichajeRepository fichajeRepository,
                            FichajeService fichajeService) {

        this.solicitudAnulacionRepository = solicitudAnulacionRepository;
        this.anulacionFichajeRepository = anulacionFichajeRepository;
        this.fichajeRepository = fichajeRepository;
        this.fichajeService = fichajeService;
    }

    @Transactional
    @Override
    public void anular(AnularCommand command) {
        //todo arodriguez: probar
        Fichaje fichaje = fichajeRepository.get(command.getIdFichaje());

        SolicitudAnulacion solicitudAnulacion = solicitudAnulacionRepository.findAllByFichaje(fichaje).stream()
                .findFirst()
                .orElse(new SolicitudAnulacion(fichaje));

        solicitudAnulacionRepository.save(solicitudAnulacion);

        AnulacionFichaje anulacionFichaje = new AnulacionFichaje(solicitudAnulacion);
        anulacionFichajeRepository.save(anulacionFichaje);

        JornadaEmpleado jornadaEmpleado = fichaje.getJornadaEmpleado();
        boolean isEmpty = fichajeService.listFichajesOrdenJornadaNotAnulados(jornadaEmpleado).stream()
                .map(FichajeOrdenJornadaSpecification::getFichaje)
                .toList()
                .isEmpty();

        fichaje.getJornadaEmpleado().setValidada(isEmpty);

        fichajeRepository.save(fichaje);
    }



}
