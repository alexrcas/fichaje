package net.avantic.domain.dao;

import net.avantic.domain.model.Vacaciones;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacacionesRepository extends JpaRepository<Vacaciones, Long> {
}