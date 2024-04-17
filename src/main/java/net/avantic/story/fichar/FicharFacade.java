package net.avantic.story.fichar;

import net.avantic.domain.model.EnumTipoFichaje;

public interface FicharFacade {

    void fichar(Long idEmpleado, EnumTipoFichaje tipoFichaje);
}
