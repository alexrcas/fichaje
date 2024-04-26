package net.avantic.domain.service;

import java.time.DayOfWeek;
import java.time.LocalDate;

public interface FechaService {

    LocalDate getStartOfYear();

    static LocalDate findPrimerLunes(LocalDate fecha) {
        DayOfWeek diaSemana = fecha.getDayOfWeek();
        int diasRetroceder = diaSemana.getValue() - DayOfWeek.MONDAY.getValue();
        return fecha.minusDays(diasRetroceder);
    }

    LocalDate getEndOfYear();
}
