package net.avantic.domain.dao;

import net.avantic.domain.model.Vacaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface VacacionesRepository extends JpaRepository<Vacaciones, Long> {

    @Query("FROM Vacaciones v where v.fechaInicio >= :fechaInicio and v.fechaInicio <= :fechaFin")
    List<Vacaciones> findAllByFechaInicioBetweenOrderByFechaInicioAsc(LocalDate fechaInicio, LocalDate fechaFin);

}
