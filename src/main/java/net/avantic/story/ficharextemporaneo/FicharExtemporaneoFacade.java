package net.avantic.story.ficharextemporaneo;

import net.avantic.domain.model.EnumTipoFichaje;

import java.time.LocalDateTime;

public interface FicharExtemporaneoFacade {

    void fichar(Long idEmpleado, EnumTipoFichaje tipoFichaje, LocalDateTime hora);
}
