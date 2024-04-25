package net.avantic.domain.model.dto.factory;

import net.avantic.domain.dao.DiaRepository;
import net.avantic.domain.model.Semana;
import net.avantic.domain.model.dto.JornadaDto;
import net.avantic.domain.service.FechaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class SemanaJornadaDtoFactory {

    private final DiaRepository diaRepository;

    @Autowired
    public SemanaJornadaDtoFactory(DiaRepository diaRepository) {
        this.diaRepository = diaRepository;
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
        int diasNoFestivos = diaRepository.findAllBySemanaAndFinSemanaOrderById(semana, false).size();
        return diasNoFestivos;
    }
}
