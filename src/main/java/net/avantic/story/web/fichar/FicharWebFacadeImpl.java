package net.avantic.story.web.fichar;

import jakarta.transaction.Transactional;
import net.avantic.domain.dao.EmpleadoRepository;
import net.avantic.domain.model.Dia;
import net.avantic.domain.model.Empleado;
import net.avantic.domain.model.EnumTipoFichaje;
import net.avantic.domain.service.DiaService;
import net.avantic.domain.service.FichajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class FicharWebFacadeImpl implements FicharWebFacade {

    private final EmpleadoRepository empleadoRepository;
    private final FichajeService fichajeService;
    private final DiaService diaService;

    @Autowired
    public FicharWebFacadeImpl(EmpleadoRepository empleadoRepository, FichajeService fichajeService,
                               DiaService diaService) {
        this.empleadoRepository = empleadoRepository;
        this.fichajeService = fichajeService;
        this.diaService = diaService;
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
        Empleado empleado = empleadoRepository.getReferenceById(1L);
        Dia dia = diaService.getByFecha(LocalDate.of(2024, 4, 22));
        fichajeService.fichar(empleado, dia, tipoFichaje);
    }

    private void ficharExtemporaneo(EnumTipoFichaje tipoFichaje, LocalDateTime fecha) {
        Empleado empleado = empleadoRepository.getReferenceById(1L);
        Dia dia = diaService.getByFecha(LocalDate.of(2024, 4, 22));
        fichajeService.ficharExtemporaneo(empleado, dia, tipoFichaje, fecha);
    }
}
