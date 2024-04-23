package net.avantic.story.web.listfichajes;

import jakarta.transaction.Transactional;
import net.avantic.domain.dao.DiaRepository;
import net.avantic.domain.dao.EmpleadoRepository;
import net.avantic.domain.dao.FichajeRepository;
import net.avantic.domain.dao.JornadaEmpleadoRepository;
import net.avantic.domain.model.*;
import net.avantic.domain.model.dto.*;
import net.avantic.domain.model.dto.factory.JornadaEmpleadoDtoFactory;
import net.avantic.domain.model.dto.factory.SemanaJornadaDtoFactory;
import net.avantic.domain.service.DiaService;
import net.avantic.domain.service.FichajeService;
import net.avantic.utils.FichajeVisitor;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ListFichajesFacadeImpl implements ListFichajesFacade {

    private final EmpleadoRepository empleadoRepository;
    private final DiaRepository diaRepository;
    private final JornadaEmpleadoRepository jornadaEmpleadoRepository;
    private final JornadaEmpleadoDtoFactory jornadaEmpleadoDtoFactory;
    private final FichajeService fichajeService;
    private final DiaService diaService;

    public ListFichajesFacadeImpl(EmpleadoRepository empleadoRepository,
                                  DiaRepository diaRepository,
                                  JornadaEmpleadoRepository jornadaEmpleadoRepository,
                                  JornadaEmpleadoDtoFactory jornadaEmpleadoDtoFactory,
                                  FichajeService fichajeService,
                                  DiaService diaService) {
        this.empleadoRepository = empleadoRepository;
        this.diaRepository = diaRepository;
        this.jornadaEmpleadoRepository = jornadaEmpleadoRepository;
        this.jornadaEmpleadoDtoFactory = jornadaEmpleadoDtoFactory;
        this.fichajeService = fichajeService;
        this.diaService = diaService;
    }




    @Override
    @Transactional
    public List<SemanaJornadaDto> listJornadas() {
        //todo: parametrizar empleado
        LocalDate today = LocalDate.now();
        List<JornadaDto> jornadas = diaRepository.findAllByFechaGreaterThanEqualAndFestivoOrderByIdAsc(SemanaJornadaDtoFactory.findPrimerLunes(LocalDate.of(today.getYear(), 1, 1)), false).stream()
                .map(d -> jornadaEmpleadoRepository.findByDiaAndEmpleado(d, empleadoRepository.findAll().get(0))
                            .map(jornadaEmpleadoDtoFactory::newDto)
                            .orElse(JornadaDto.emptyDto(d))
                )
                .collect(Collectors.toList());

        return SemanaJornadaDtoFactory.agruparLista(jornadas);
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
