package net.avantic.domain.model.dto.factory;

import net.avantic.domain.model.dto.JornadaDto;
import net.avantic.domain.model.dto.SemanaJornadaDto;
import net.avantic.domain.service.FechaService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SemanaJornadaDtoFactory {

    public static final int WEEK_SIZE = 5;

    public static List<SemanaJornadaDto> agruparLista(List<JornadaDto> lista) {
        LocalDate today = FechaService.findPrimerLunes(LocalDate.now());

        List<SemanaJornadaDto> semanaJornadaDtoList = new ArrayList<>();
        for (int i = 0; i < lista.size(); i += WEEK_SIZE) {

            List<JornadaDto> jornadasSemana = lista.subList(i, Math.min(i + WEEK_SIZE, lista.size()));

            boolean isSemanaActual = jornadasSemana.stream()
                    .map(JornadaDto::getFecha)
                    .anyMatch(today::isEqual);

            double tiempoSemana = jornadasSemana.stream()
                    .map(JornadaDto::getHoras)
                    .filter(s -> !s.isBlank() && !s.equals("E"))
                    .map(Double::parseDouble)
                    .reduce((double) 0, Double::sum);

            semanaJornadaDtoList.add(new SemanaJornadaDto(jornadasSemana, isSemanaActual, tiempoSemana));
        }
        return semanaJornadaDtoList;
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


}
