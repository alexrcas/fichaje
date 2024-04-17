package net.avantic.domain.dao;

import net.avantic.domain.model.EntradaDesayuno;
import net.avantic.domain.model.JornadaEmpleado;
import net.avantic.domain.model.SalidaDesayuno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalidaDesayunoRepository extends JpaRepository<SalidaDesayuno, Long> {

    List<SalidaDesayuno> findAllByJornadaEmpleado(JornadaEmpleado jornadaEmpleado);
}
