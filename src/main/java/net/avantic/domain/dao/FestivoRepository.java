package net.avantic.domain.dao;

import net.avantic.domain.model.Dia;
import net.avantic.domain.model.Festivo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FestivoRepository extends JpaRepository<Festivo, Long> {

    Optional<Festivo> findByDia(Dia dia);

}
