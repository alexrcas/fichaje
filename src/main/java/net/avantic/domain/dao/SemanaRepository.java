package net.avantic.domain.dao;

import net.avantic.domain.model.Semana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SemanaRepository extends JpaRepository<Semana, Long> {

    @Query("From Semana s WHERE s in (select d.semana from Dia d WHERE d.fecha >= :fechaInicio and d.fecha <= :fechaFin) order by s.id asc")
    List<Semana> findAllByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin);
}
