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


    @Query("From Dia d WHERE d.finSemana = false")
    List<Dia> findAllByNotFinSemanaOrderByIdAsc();

    @Query("From Dia d WHERE d.finSemana = false and d.fecha >= :fecha order by id asc")
    List<Dia> findAllByFechaGreaterThanEqualAndFinSemanaOrderByIdAsc(LocalDate fecha);

    @Query("From Dia d WHERE d.semana = :semana AND d.finSemana = false order by id asc")
    List<Dia> findAllBySemanaAndNotFinSemanaOrderById(Semana semana);
}
