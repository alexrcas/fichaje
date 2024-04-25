package net.avantic.story.web.listfichajes;

import jakarta.transaction.Transactional;
import net.avantic.domain.dao.*;
import net.avantic.domain.model.*;
import net.avantic.domain.model.dto.*;
import net.avantic.domain.model.dto.factory.JornadaEmpleadoDtoFactory;
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
    private final JornadaEmpleadoDtoFactory jornadaEmpleadoDtoFactory;
    private final FichajeService fichajeService;
    private final DiaService diaService;
    private final SemanaRepository semanaRepository;
    private final FechaService fechaService;
    private final SemanaJornadaDtoFactory semanaJornadaDtoFactory;

    public ListFichajesFacadeImpl(EmpleadoRepository empleadoRepository,
                                  DiaRepository diaRepository,
                                  JornadaEmpleadoRepository jornadaEmpleadoRepository,
                                  JornadaEmpleadoDtoFactory jornadaEmpleadoDtoFactory,
                                  FichajeService fichajeService,
                                  DiaService diaService,
                                  SemanaRepository semanaRepository,
                                  FechaService fechaService,
                                  SemanaJornadaDtoFactory semanaJornadaDtoFactory) {
        this.empleadoRepository = empleadoRepository;
        this.diaRepository = diaRepository;
        this.jornadaEmpleadoRepository = jornadaEmpleadoRepository;
        this.jornadaEmpleadoDtoFactory = jornadaEmpleadoDtoFactory;
        this.fichajeService = fichajeService;
        this.diaService = diaService;
        this.semanaRepository = semanaRepository;
        this.fechaService = fechaService;
        this.semanaJornadaDtoFactory = semanaJornadaDtoFactory;
    }




    @Override
    @Transactional
    public List<SemanaJornadaDto> listJornadas() {
        Empleado empleado = empleadoRepository.findAll().get(0);
        //todo arodriguez: trasladar a factoría
        //ojo, crear un nuevo concepto "fin de semana" en lugar de festivo o no se listarán los festivos entre semana
        List<SemanaJornadaDto> semanasJornadasDtoList = new ArrayList<>();
        List<Semana> semanas = semanaRepository.findAllByFechaDiaGreaterThanEqual(fechaService.getStartOfYear());
        for (Semana semana : semanas) {

            List<JornadaDto> jornadaDtos = diaRepository.findAllBySemanaAndNotFinSemanaOrderById(semana).stream()
                    .map(d -> jornadaEmpleadoRepository.findByDiaAndEmpleado(d, empleado)
                            .map(jornadaEmpleadoDtoFactory::newDto)
                            .orElse(JornadaDto.emptyDto(d))
                    )
                    .toList();

            semanasJornadasDtoList.add(
                    new SemanaJornadaDto(jornadaDtos, SemanaJornadaDtoFactory.isSemanaActual(jornadaDtos),
                    SemanaJornadaDtoFactory.calcularTiempoSemana(jornadaDtos), semanaJornadaDtoFactory.calcularHorasSemana(semana))
            );
        }

        return semanasJornadasDtoList;
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
