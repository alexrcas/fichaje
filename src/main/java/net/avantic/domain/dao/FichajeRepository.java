package net.avantic.domain.dao;

import net.avantic.domain.model.Fichaje;
import net.avantic.domain.model.JornadaEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FichajeRepository extends JpaRepository<Fichaje, Long>, CustomFichajeRepository {

    @Query("From Fichaje f WHERE f not in (Select a.solicitudAnulacion.fichaje From AnulacionFichaje a) and f.jornadaEmpleado = :jornadaEmpleado")
    List<Fichaje> findAllByJornadaEmpleadoNotAnulado(JornadaEmpleado jornadaEmpleado);

    List<Fichaje> findAllByJornadaEmpleado(JornadaEmpleado jornadaEmpleado);
}
