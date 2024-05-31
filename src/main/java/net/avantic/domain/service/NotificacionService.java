package net.avantic.domain.service;

import net.avantic.domain.model.JornadaEmpleado;

public interface NotificacionService {

    void generarNotificacionJornadaErronea(JornadaEmpleado jornadaEmpleado);
}
