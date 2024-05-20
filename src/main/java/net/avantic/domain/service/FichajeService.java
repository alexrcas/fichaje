package net.avantic.domain.service;

import net.avantic.domain.model.Empleado;
import net.avantic.domain.model.EnumTipoFichaje;
import net.avantic.domain.model.Dia;
import net.avantic.domain.model.JornadaEmpleado;
import net.avantic.domain.model.dto.FichajeOrdenJornadaSpecification;

import java.time.LocalDateTime;
import java.util.List;

public interface FichajeService {

    void fichar(Empleado empleado, Dia dia, EnumTipoFichaje tipoFichaje);

    void ficharExtemporaneo(Empleado empleado, Dia dia, EnumTipoFichaje tipoFichaje, LocalDateTime hora);

    List<FichajeOrdenJornadaSpecification> listFichajesOrdenJornadaNotAnulados(JornadaEmpleado jornadaEmpleado);

    List<FichajeOrdenJornadaSpecification> listFichajesOrdenJornada(JornadaEmpleado jornadaEmpleado);

    EnumTipoFichaje getFichajeSugerido(JornadaEmpleado jornadaEmpleado);
}
