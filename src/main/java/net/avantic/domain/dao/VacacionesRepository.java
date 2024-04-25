package net.avantic.domain.dao;

import net.avantic.domain.model.Vacaciones;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VacacionesRepository extends JpaRepository<Vacaciones, Long> {

}
