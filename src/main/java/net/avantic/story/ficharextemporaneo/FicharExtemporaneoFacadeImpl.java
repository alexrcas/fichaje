package net.avantic.story.ficharextemporaneo;

import jakarta.transaction.Transactional;
import net.avantic.domain.model.Dia;
import net.avantic.domain.model.Empleado;
import net.avantic.domain.model.EnumTipoFichaje;
import net.avantic.domain.service.DiaService;
import net.avantic.domain.service.EmpleadoService;
import net.avantic.domain.service.FichajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class FicharExtemporaneoFacadeImpl implements FicharExtemporaneoFacade {

    private final EmpleadoService empleadoService;
    private final DiaService diaService;
    private final FichajeService fichajeService;

    @Autowired
    public FicharExtemporaneoFacadeImpl(EmpleadoService empleadoService,
                                        DiaService diaService,
                                        FichajeService fichajeService) {
        this.empleadoService = empleadoService;
        this.diaService = diaService;
        this.fichajeService = fichajeService;
    }

    @Override
    @Transactional
    public void fichar(Long idEmpleado, EnumTipoFichaje tipoFichaje, LocalDateTime hora) {
        Empleado empleado = empleadoService.getById(idEmpleado);
        Dia dia = diaService.getByFecha(hora.toLocalDate());
        fichajeService.ficharExtemporaneo(empleado, dia, tipoFichaje, hora);
    }
}
