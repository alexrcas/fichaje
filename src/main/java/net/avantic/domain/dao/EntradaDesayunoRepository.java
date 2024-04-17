package net.avantic.domain.dao;

import net.avantic.domain.model.EntradaDesayuno;
import net.avantic.domain.model.EntradaJornada;
import net.avantic.domain.model.JornadaEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntradaDesayunoRepository extends JpaRepository<EntradaDesayuno, Long> {

    List<EntradaDesayuno> findAllByJornadaEmpleado(JornadaEmpleado jornadaEmpleado);
}
