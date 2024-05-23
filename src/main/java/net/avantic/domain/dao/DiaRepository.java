package net.avantic.domain.dao;

import net.avantic.domain.model.Dia;
import net.avantic.domain.model.Semana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DiaRepository extends JpaRepository<Dia, Long> {

    Optional<Dia> findByFecha(LocalDate fecha);

    @Query("From Dia d WHERE d.finSemana = false and d.festivo = true and d.fecha >= :fechaInicio and fecha <= :fechaFin order by id asc")
    List<Dia> findAllByFechaBetweenThanEqualAndNotFinSemanaAndFestivoOrderByIdAsc(LocalDate fechaInicio, LocalDate fechaFin);

    @Query("From Dia d WHERE d.semana = :semana AND d.finSemana = false order by id asc")
    List<Dia> findAllBySemanaAndNotFinSemanaOrderById(Semana semana);
}
