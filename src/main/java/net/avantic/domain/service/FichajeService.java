package net.avantic.domain.service;

import net.avantic.domain.model.Empleado;
import net.avantic.domain.model.EnumTipoFichaje;
import net.avantic.domain.model.Dia;

public interface FichajeService {

    void fichar(Empleado empleado, Dia dia, EnumTipoFichaje tipoFichaje);

}
