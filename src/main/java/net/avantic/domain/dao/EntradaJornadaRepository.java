package net.avantic.domain.dao;

import net.avantic.domain.model.EntradaJornada;
import net.avantic.domain.model.JornadaEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EntradaJornadaRepository extends JpaRepository<EntradaJornada, Long> {

    List<EntradaJornada> findAllByJornadaEmpleado(JornadaEmpleado jornadaEmpleado);
}
