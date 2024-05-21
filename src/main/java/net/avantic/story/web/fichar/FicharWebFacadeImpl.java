package net.avantic.story.web.fichar;

import jakarta.transaction.Transactional;
import net.avantic.domain.dao.EmpleadoRepository;
import net.avantic.domain.model.Dia;
import net.avantic.domain.model.Empleado;
import net.avantic.domain.model.EnumTipoFichaje;
import net.avantic.domain.service.DiaService;
import net.avantic.domain.service.FichajeService;
import net.avantic.domain.service.SecurityUtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class FicharWebFacadeImpl implements FicharWebFacade {

    private final FichajeService fichajeService;
    private final DiaService diaService;
    private final SecurityUtilsService securityUtilsService;

    @Autowired
    public FicharWebFacadeImpl(FichajeService fichajeService,
                               DiaService diaService,
                               SecurityUtilsService securityUtilsService) {
        this.fichajeService = fichajeService;
        this.diaService = diaService;
        this.securityUtilsService = securityUtilsService;
    }

    @Transactional
    @Override
    public void fichar(FicharWebCommand command) {
        if (command.getFecha() == null) {
            ficharTiempoReal(command.getFichaje());
            return;
        }

        ficharExtemporaneo(command.getFichaje(), command.getFecha());
    }

    private void ficharTiempoReal(EnumTipoFichaje tipoFichaje) {
        Empleado empleado = securityUtilsService.getAuthenticatedUser();
        Dia dia = diaService.getByFecha(LocalDate.now());
        fichajeService.fichar(empleado, dia, tipoFichaje);
    }

    private void ficharExtemporaneo(EnumTipoFichaje tipoFichaje, LocalDateTime fecha) {
        Empleado empleado = securityUtilsService.getAuthenticatedUser();
        Dia dia = diaService.getByFecha(fecha.toLocalDate());
        fichajeService.ficharExtemporaneo(empleado, dia, tipoFichaje, fecha);
    }
}
