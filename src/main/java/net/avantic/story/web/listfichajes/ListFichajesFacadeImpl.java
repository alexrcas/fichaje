package net.avantic.story.web.listfichajes;

import jakarta.transaction.Transactional;
import net.avantic.domain.dao.*;
import net.avantic.domain.model.*;
import net.avantic.domain.model.dto.*;
import net.avantic.domain.model.dto.factory.DiaCalendarioDtoFactory;
import net.avantic.domain.model.dto.factory.SemanaJornadaDtoFactory;
import net.avantic.domain.service.DiaService;
import net.avantic.domain.service.FechaService;
import net.avantic.domain.service.FichajeService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class ListFichajesFacadeImpl implements ListFichajesFacade {

    private final EmpleadoRepository empleadoRepository;
    private final DiaRepository diaRepository;
    private final JornadaEmpleadoRepository jornadaEmpleadoRepository;
    private final FichajeService fichajeService;
    private final DiaService diaService;
    private final SemanaRepository semanaRepository;
    private final FechaService fechaService;
    private final SemanaJornadaDtoFactory semanaJornadaDtoFactory;
    private final DiaCalendarioDtoFactory diaCalendarioDtoFactory;

    public ListFichajesFacadeImpl(EmpleadoRepository empleadoRepository,
                                  DiaRepository diaRepository,
                                  JornadaEmpleadoRepository jornadaEmpleadoRepository,
                                  FichajeService fichajeService,
                                  DiaService diaService,
                                  SemanaRepository semanaRepository,
                                  FechaService fechaService,
                                  SemanaJornadaDtoFactory semanaJornadaDtoFactory,
                                  DiaCalendarioDtoFactory diaCalendarioDtoFactory) {
        this.empleadoRepository = empleadoRepository;
        this.diaRepository = diaRepository;
        this.jornadaEmpleadoRepository = jornadaEmpleadoRepository;
        this.fichajeService = fichajeService;
        this.diaService = diaService;
        this.semanaRepository = semanaRepository;
        this.fechaService = fechaService;
        this.semanaJornadaDtoFactory = semanaJornadaDtoFactory;
        this.diaCalendarioDtoFactory = diaCalendarioDtoFactory;
    }




    @Override
    @Transactional
    public List<SemanaJornadaDto> listJornadas() {
        //todo arodriguez: parametrizar empleado
        Empleado empleado = empleadoRepository.findAll().get(0);
        return semanaRepository.findAllByFechaBetween(fechaService.getStartOfYear(), fechaService.getEndOfYear()).stream()
                .map(s -> semanaJornadaDtoFactory.newDto(s, empleado))
                .toList();
    }

    @Override
    public List<EnumTipoFichaje> listOpciones() {
        return Arrays.asList(EnumTipoFichaje.values());
    }

    public EnumTipoFichaje getOpcionSugerida() {
        Dia dia = diaService.getByFecha(LocalDate.now());
        Empleado empleado = empleadoRepository.getReferenceById(1L);
        Optional<JornadaEmpleado> jornadaEmpleado = jornadaEmpleadoRepository.findByDiaAndEmpleado(dia, empleado);
        if (jornadaEmpleado.isEmpty()) {
            return EnumTipoFichaje.ENTRADA_JORNADA;
        }

        return fichajeService.getFichajeSugerido(jornadaEmpleado.get());
    }


}
