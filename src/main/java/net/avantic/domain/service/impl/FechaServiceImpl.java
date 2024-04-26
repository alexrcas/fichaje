package net.avantic.domain.service.impl;

import net.avantic.domain.service.FechaService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Service
public class FechaServiceImpl implements FechaService {

    @Override
    public LocalDate getStartOfYear() {
        LocalDate today = LocalDate.now();
        return LocalDate.of(today.getYear(), 1, 1);
    }

    @Override
    public LocalDate getEndOfYear() {
        LocalDate today = LocalDate.now();
        return LocalDate.of(today.getYear(), 12, 31);
    }

}
