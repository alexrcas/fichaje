package net.avantic.domain.dao;

import net.avantic.domain.model.Dia;
import net.avantic.domain.model.Empleado;
import net.avantic.domain.model.JornadaEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JornadaEmpleadoRepository extends JpaRepository<JornadaEmpleado, Long> {

    Optional<JornadaEmpleado> findByDiaAndEmpleado(Dia dia, Empleado empleado);

    List<JornadaEmpleado> findAllByEmpleado(Empleado empleado);
}
