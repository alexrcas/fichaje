package net.avantic.domain.dao;

import net.avantic.domain.model.Fichaje;
import net.avantic.domain.model.JornadaEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FichajeRepository extends JpaRepository<Fichaje, Long> {

    List<Fichaje> findAllByJornadaEmpleado(JornadaEmpleado jornadaEmpleado);
}
