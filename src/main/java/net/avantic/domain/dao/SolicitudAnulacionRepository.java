package net.avantic.domain.dao;

import net.avantic.domain.model.Fichaje;
import net.avantic.domain.model.SolicitudAnulacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SolicitudAnulacionRepository extends JpaRepository<SolicitudAnulacion, Long> {

    List<SolicitudAnulacion> findAllByFichaje(Fichaje fichaje);

    @Query("From SolicitudAnulacion sa where sa not in (select af.solicitudAnulacion from AnulacionFichaje af)")
    List<SolicitudAnulacion> findAllByPendienteAnular();
}
