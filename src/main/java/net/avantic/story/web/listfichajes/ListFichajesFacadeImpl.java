package net.avantic.story.web.listfichajes;

import jakarta.transaction.Transactional;
import net.avantic.domain.dao.DiaRepository;
import net.avantic.domain.dao.EmpleadoRepository;
import net.avantic.domain.dao.JornadaEmpleadoRepository;
import net.avantic.domain.model.Empleado;
import net.avantic.domain.model.dto.*;
import net.avantic.domain.model.dto.factory.JornadaEmpleadoDtoFactory;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ListFichajesFacadeImpl implements ListFichajesFacade {

    private final EmpleadoRepository empleadoRepository;
    private final DiaRepository diaRepository;
    private final JornadaEmpleadoRepository jornadaEmpleadoRepository;
    private final JornadaEmpleadoDtoFactory jornadaEmpleadoDtoFactory;

    public ListFichajesFacadeImpl(EmpleadoRepository empleadoRepository,
                                  DiaRepository diaRepository,
                                  JornadaEmpleadoRepository jornadaEmpleadoRepository,
                                  JornadaEmpleadoDtoFactory jornadaEmpleadoDtoFactory) {
        this.empleadoRepository = empleadoRepository;
        this.diaRepository = diaRepository;
        this.jornadaEmpleadoRepository = jornadaEmpleadoRepository;
        this.jornadaEmpleadoDtoFactory = jornadaEmpleadoDtoFactory;
    }


    private static LocalDate findPrimerLunes(LocalDate fecha) {
        DayOfWeek diaSemana = fecha.getDayOfWeek();
        int diasRetroceder = diaSemana.getValue() - DayOfWeek.MONDAY.getValue();
        return fecha.minusDays(diasRetroceder);
    }

    @Override
    @Transactional
    public List<SemanaJornadaDto> listJornadas() {
        //todo: parametrizar empleado
        List<JornadaDto> jornadas = diaRepository.findAllByFechaGreaterThanEqualAndFestivoOrderByIdAsc(findPrimerLunes(LocalDate.of(2024, 1, 1)), false).stream()
                .map(d -> jornadaEmpleadoRepository.findByDiaAndEmpleado(d, empleadoRepository.findAll().get(0))
                            .map(jornadaEmpleadoDtoFactory::newDto)
                            .orElse(JornadaDto.emptyDto(d))
                )
                .collect(Collectors.toList());

        return agruparLista(jornadas, 5);
    }

    //todo arodriguez: trasladar a factory
    public static List<SemanaJornadaDto> agruparLista(List<JornadaDto> lista, int tamanoSublista) {
        LocalDate today = findPrimerLunes(LocalDate.now());

        List<SemanaJornadaDto> semanaJornadaDtoList = new ArrayList<>();
        for (int i = 0; i < lista.size(); i += tamanoSublista) {

            List<JornadaDto> jornadasSemana = lista.subList(i, Math.min(i + tamanoSublista, lista.size()));

            boolean isSemanaActual = jornadasSemana.stream()
                    .map(JornadaDto::getFecha)
                    .anyMatch(today::isEqual);

            double tiempoSemana = jornadasSemana.stream()
                    .map(JornadaDto::getHoras)
                    .filter(s -> !s.isBlank())
                    .map(Double::parseDouble)
                            .reduce((double) 0, Double::sum);

            semanaJornadaDtoList.add(new SemanaJornadaDto(jornadasSemana, isSemanaActual, tiempoSemana));
        }
        return semanaJornadaDtoList;
    }

}
