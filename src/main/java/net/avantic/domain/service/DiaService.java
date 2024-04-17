package net.avantic.domain.service;

import net.avantic.domain.model.Dia;

import java.time.LocalDate;

public interface DiaService {

    Dia getByFecha(LocalDate fecha);
}
