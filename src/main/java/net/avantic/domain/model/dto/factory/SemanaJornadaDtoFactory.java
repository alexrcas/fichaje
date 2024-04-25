package net.avantic.domain.model.dto.factory;

import net.avantic.domain.dao.*;
import net.avantic.domain.model.AusenciaJustificada;
import net.avantic.domain.model.Empleado;
import net.avantic.domain.model.Semana;
import net.avantic.domain.model.dto.DiaCalendarioDto;
import net.avantic.domain.model.dto.JornadaDto;
import net.avantic.domain.service.FechaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class SemanaJornadaDtoFactory {

    private final DiaRepository diaRepository;
    private final DiaLibreRepository diaLibreRepository;
    private final EmpleadoRepository empleadoRepository;
    private final JornadaEmpleadoRepository jornadaEmpleadoRepository;
    private final AusenciaJustificadaRepository ausenciaJustificadaRepository;

    @Autowired
    public SemanaJornadaDtoFactory(DiaRepository diaRepository,
                                   DiaLibreRepository diaLibreRepository,
                                   EmpleadoRepository empleadoRepository,
                                   JornadaEmpleadoRepository jornadaEmpleadoRepository,
                                   AusenciaJustificadaRepository ausenciaJustificadaRepository) {
        this.diaRepository = diaRepository;
        this.diaLibreRepository = diaLibreRepository;
        this.empleadoRepository = empleadoRepository;
        this.jornadaEmpleadoRepository = jornadaEmpleadoRepository;
        this.ausenciaJustificadaRepository = ausenciaJustificadaRepository;
    }

    public static boolean isSemanaActual(List<DiaCalendarioDto> diasCalendario) {
        LocalDate today = FechaService.findPrimerLunes(LocalDate.now());
        return diasCalendario.stream()
                .map(DiaCalendarioDto::getJornada)
                .filter(Objects::nonNull)
                .map(JornadaDto::getFecha)
                .anyMatch(today::isEqual);
    }

    public static double calcularTiempoSemana(List<DiaCalendarioDto> diasCalendario) {
        return diasCalendario.stream()
                .filter(d -> !d.isFestivo())
                .filter(d -> !d.isVacaciones())
                .map(DiaCalendarioDto::getJornada)
                .filter(Objects::nonNull)
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

        double horasJustificadas = diaRepository.findAllBySemanaAndNotFinSemanaOrderById(semana).stream()
                .map(d -> jornadaEmpleadoRepository.findByDiaAndEmpleado(d, empleado))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(ausenciaJustificadaRepository::findAllByJornadaEmpleado)
                .flatMap(List::stream)
                .map(AusenciaJustificada::getHoras)
                .reduce((double) 0, Double::sum);


        return ((diasNoFestivos - diasLibres) * 8) - horasJustificadas;
    }
}
