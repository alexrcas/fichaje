package net.avantic.domain.dao;

import net.avantic.domain.model.DiaLibre;
import net.avantic.domain.model.Vacaciones;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaLibreRepository extends JpaRepository<DiaLibre, Long> {

    List<DiaLibre> findAllByVacacionesOrderByDia_fechaDesc(Vacaciones vacaciones);
}
