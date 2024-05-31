package net.avantic.domain.dao;

import net.avantic.domain.model.Dia;
import net.avantic.domain.model.Empleado;
import net.avantic.domain.model.JornadaEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JornadaEmpleadoRepository extends JpaRepository<JornadaEmpleado, Long>, CustomJornadaEmpleadoRepository {

    Optional<JornadaEmpleado> findByDiaAndEmpleado(Dia dia, Empleado empleado);

    List<JornadaEmpleado> findAllByEmpleado(Empleado empleado);

    @Query("From JornadaEmpleado je where je.validada = false")
    List<JornadaEmpleado> findAllByNotValid();
}
