package net.avantic.domain.dao;

import net.avantic.domain.model.Dia;
import net.avantic.domain.model.DiaLibre;
import net.avantic.domain.model.Empleado;
import net.avantic.domain.model.Vacaciones;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import java.util.Optional;

public interface DiaLibreRepository extends JpaRepository<DiaLibre, Long> {

    List<DiaLibre> findAllByVacacionesOrderByVacaciones_fechaInicioAsc(Vacaciones vacaciones);

    Optional<DiaLibre> findByDiaAndEmpleado(Dia dia, Empleado empleado);
}
