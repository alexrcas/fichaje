package net.avantic.domain.dao;

import net.avantic.domain.model.Extemporaneo;
import net.avantic.domain.model.Fichaje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExtemporaneoRepository extends JpaRepository<Extemporaneo, Long> {

    Optional<Extemporaneo> findByFichaje(Fichaje fichaje);
}
