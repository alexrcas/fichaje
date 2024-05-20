package net.avantic.domain.dao;

import net.avantic.domain.model.AnulacionFichaje;
import net.avantic.domain.model.SolicitudAnulacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnulacionFichajeRepository extends JpaRepository<AnulacionFichaje, Long> {

    List<AnulacionFichaje> findAllBySolicitudAnulacion(SolicitudAnulacion solicitudAnulacion);
}
