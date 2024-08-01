package net.avantic.domain.service;

import net.avantic.domain.model.Dia;
import net.avantic.domain.model.Empleado;
import net.avantic.domain.model.JornadaEmpleado;

public interface NotificacionService {

    void generarNotificacionJornadaErronea(JornadaEmpleado jornadaEmpleado);

    void generarNotificacionDiaSinFichar(Dia dia, Empleado empleado);
}
