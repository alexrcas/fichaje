package net.avantic.domain.service;

import net.avantic.domain.model.Empleado;
import net.avantic.domain.model.EnumTipoFichaje;
import net.avantic.domain.model.Jornada;
import net.avantic.domain.model.JornadaEmpleado;

public interface FichajeService {

    void fichar(Empleado empleado, Jornada jornada, EnumTipoFichaje tipoFichaje);

}
