package net.avantic.domain.dao;

import net.avantic.domain.model.Dia;
import net.avantic.domain.model.Semana;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DiaRepository extends JpaRepository<Dia, Long> {

    Optional<Dia> findByFecha(LocalDate fecha);

    List<Dia> findAllByFestivoOrderByIdAsc(boolean festivo);

    List<Dia> findAllByFechaGreaterThanEqualAndFestivoOrderByIdAsc(LocalDate fecha, boolean festivo);

    List<Dia> findAllByOrderByIdAsc();

    List<Dia> findAllBySemanaOrderById(Semana semana);
}
