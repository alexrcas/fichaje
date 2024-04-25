package net.avantic.domain.dao;

import net.avantic.domain.model.AusenciaJustificada;
import net.avantic.domain.model.JornadaEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AusenciaJustificadaRepository extends JpaRepository<AusenciaJustificada, Long> {

    List<AusenciaJustificada> findAllByJornadaEmpleado(JornadaEmpleado jornadaEmpleado);
}
