package net.avantic.domain.model.dto.factory;

import net.avantic.domain.dao.DiaLibreRepository;
import net.avantic.domain.dao.DiaRepository;
import net.avantic.domain.dao.EmpleadoRepository;
import net.avantic.domain.model.Empleado;
import net.avantic.domain.model.Semana;
import net.avantic.domain.model.dto.JornadaDto;
import net.avantic.domain.service.FechaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SemanaJornadaDtoFactory {

    private final DiaRepository diaRepository;
    private final DiaLibreRepository diaLibreRepository;
    private final EmpleadoRepository empleadoRepository;

    @Autowired
    public SemanaJornadaDtoFactory(DiaRepository diaRepository,
                                   DiaLibreRepository diaLibreRepository,
                                   EmpleadoRepository empleadoRepository) {
        this.diaRepository = diaRepository;
        this.diaLibreRepository = diaLibreRepository;
        this.empleadoRepository = empleadoRepository;
    }

    public static boolean isSemanaActual(List<JornadaDto> jornadasSemana) {
        LocalDate today = FechaService.findPrimerLunes(LocalDate.now());
        return jornadasSemana.stream()
                .map(JornadaDto::getFecha)
                .anyMatch(today::isEqual);
    }

    public static double calcularTiempoSemana(List<JornadaDto> jornadasSemana) {
        return jornadasSemana.stream()
                .map(JornadaDto::getHoras)
                .filter(s -> !s.isBlank() && !s.equals("E"))
                .map(Double::parseDouble)
                .reduce((double) 0, Double::sum);
    }


    public double calcularHorasSemana(Semana semana) {
        //todo arodriguez: completar cuando se implementen los casos restantes
        // Formula: (días NO festivos * 8) - (días vacaciones * 8) - horas justificadas
        //todo: arodriguez: parametrizar empleado
        Empleado empleado = empleadoRepository.findAll().get(0);

        int diasNoFestivos = diaRepository.findAllBySemanaAndNotFinSemanaOrderById(semana).stream()
                .filter(d -> !d.isFestivo())
                .toList()
                .size();

        int diasLibres = diaRepository.findAllBySemanaAndNotFinSemanaOrderById(semana).stream()
                .map(d -> diaLibreRepository.findByDiaAndEmpleado(d, empleado))
                .filter(Optional::isPresent)
                .toList()
                .size();

        return (diasNoFestivos - diasLibres) * 8;
    }
}
