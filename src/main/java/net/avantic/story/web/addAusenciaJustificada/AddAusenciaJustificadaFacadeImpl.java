package net.avantic.story.web.addAusenciaJustificada;

import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import net.avantic.domain.dao.*;
import net.avantic.domain.model.AusenciaJustificada;
import net.avantic.domain.model.Dia;
import net.avantic.domain.model.Empleado;
import net.avantic.domain.model.JornadaEmpleado;
import net.avantic.domain.service.DiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddAusenciaJustificadaFacadeImpl implements AddAusenciaJustificadaFacade {

    private final EmpleadoRepository empleadoRepository;
    private final DiaLibreRepository diaLibreRepository;
    private final VacacionesRepository vacacionesRepository;
    private final DiaRepository diaRepository;
    private final DiaService diaService;
    private final FestivoRepository festivoRepository;
    private final AusenciaJustificadaRepository ausenciaJustificadaRepository;
    private final JornadaEmpleadoRepository jornadaEmpleadoRepository;

    @Autowired
    public AddAusenciaJustificadaFacadeImpl(EmpleadoRepository empleadoRepository,
                                            DiaLibreRepository diaLibreRepository,
                                            VacacionesRepository vacacionesRepository,
                                            DiaRepository diaRepository,
                                            DiaService diaService,
                                            FestivoRepository festivoRepository,
                                            AusenciaJustificadaRepository ausenciaJustificadaRepository,
                                            JornadaEmpleadoRepository jornadaEmpleadoRepository) {
        this.empleadoRepository = empleadoRepository;
        this.diaLibreRepository = diaLibreRepository;
        this.vacacionesRepository = vacacionesRepository;
        this.diaRepository = diaRepository;
        this.diaService = diaService;
        this.festivoRepository = festivoRepository;
        this.ausenciaJustificadaRepository = ausenciaJustificadaRepository;
        this.jornadaEmpleadoRepository = jornadaEmpleadoRepository;
    }

    @Transactional
    @Override
    public void addAusenciaJustificada(AddAusenciaJustificadaCommand command) throws Exception {

        assertCommand(command);

        Empleado empleado = empleadoRepository.getById(command.getIdEmpleado());
        Dia dia = diaService.getByFecha(command.getFecha());

        jornadaEmpleadoRepository.findByDiaAndEmpleado(dia, empleado)
                .map(je -> new AusenciaJustificada(je, command.getHoras(), command.getMotivo()))
                .map(ausenciaJustificadaRepository::save)
                .orElseThrow(() -> new RuntimeException("No existe jornada para el empleado el día seleccionado. Debe justificar las horas sobre una jornada existente"));
    }


    private void assertCommand(AddAusenciaJustificadaCommand command) throws Exception {

        if (command.getIdEmpleado() == null) {
            throw new RuntimeException("No se ha especificado un empleado");
        }

        if (command.getFecha() == null) {
            throw new RuntimeException("No se ha especificado una fecha");
        }

        if (command.getHoras() <= 0) {
            throw new RuntimeException("El número de horas debe ser mayor que cero");
        }

        if (StringUtils.isBlank(command.getMotivo())) {
            throw new RuntimeException("No se ha especificado un motivo");
        }

        Dia dia = diaService.getByFecha(command.getFecha());
        if (dia.isFinSemana() || dia.isFestivo()) {
            throw new RuntimeException("No pueden justificarse horas un día festivo o fin de semana");
        }

        Empleado empleado = empleadoRepository.getById(command.getIdEmpleado());
        if (diaLibreRepository.findByDiaAndEmpleado(dia, empleado).isPresent()) {
            throw new RuntimeException("El empleado está de vacaciones el día seleccionado. No puede justificarse horas un día vacacional");
        }
    }



}
