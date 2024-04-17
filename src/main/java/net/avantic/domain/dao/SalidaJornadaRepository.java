package net.avantic.domain.dao;

import net.avantic.domain.model.JornadaEmpleado;
import net.avantic.domain.model.SalidaJornada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SalidaJornadaRepository extends JpaRepository<SalidaJornada, Long> {

    List<SalidaJornada> findAllByJornadaEmpleado(JornadaEmpleado jornadaEmpleado);
}
