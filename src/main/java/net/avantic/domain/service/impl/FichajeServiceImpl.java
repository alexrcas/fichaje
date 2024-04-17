package net.avantic.domain.service.impl;

import net.avantic.domain.dao.FichajeRepository;
import net.avantic.domain.dao.JornadaEmpleadoRepository;
import net.avantic.domain.model.*;
import net.avantic.domain.service.FichajeService;
import net.avantic.domain.service.JornadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FichajeServiceImpl implements FichajeService {

    private final JornadaService jornadaService;
    private final FichajeRepository fichajeRepository;
    private final JornadaEmpleadoRepository jornadaEmpleadoRepository;

    @Autowired
    public FichajeServiceImpl(JornadaService jornadaService,
                              FichajeRepository fichajeRepository,
                              JornadaEmpleadoRepository jornadaEmpleadoRepository) {
        this.jornadaService = jornadaService;
        this.fichajeRepository = fichajeRepository;
        this.jornadaEmpleadoRepository = jornadaEmpleadoRepository;
    }

    @Override
    public void fichar(Empleado empleado, Dia dia, EnumTipoFichaje tipoFichaje) {

        JornadaEmpleado jornadaEmpleado = jornadaService.findByDiaAndEmpleado(dia, empleado)
                .orElseGet(() -> jornadaService.createJornadaEmpleado(dia, empleado));

        Fichaje fichaje = createFichaje(empleado, jornadaEmpleado, tipoFichaje);

        jornadaEmpleado.setValidada(false);
        jornadaEmpleadoRepository.save(jornadaEmpleado);
        fichajeRepository.save(fichaje);
    }


    private Fichaje createFichaje(Empleado empleado, JornadaEmpleado jornadaEmpleado, EnumTipoFichaje tipoFichaje) {

        if (tipoFichaje.equals(EnumTipoFichaje.ENTRADA_JORNADA)) {
            return new EntradaJornada(empleado, jornadaEmpleado);
        }

        if (tipoFichaje.equals(EnumTipoFichaje.SALIDA_JORNADA)) {
            return new SalidaJornada(empleado, jornadaEmpleado);
        }

        if (tipoFichaje.equals(EnumTipoFichaje.ENTRADA_DESAYUNO)) {
            return new EntradaDesayuno(empleado, jornadaEmpleado);
        }

        if (tipoFichaje.equals(EnumTipoFichaje.SALIDA_DESAYUNO)) {
            return new SalidaDesayuno(empleado, jornadaEmpleado);
        }

        if (tipoFichaje.equals(EnumTipoFichaje.ENTRADA_COMIDA)) {
            return new EntradaComida(empleado, jornadaEmpleado);
        }

        if (tipoFichaje.equals(EnumTipoFichaje.SALIDA_COMIDA)) {
            return new SalidaComida(empleado, jornadaEmpleado);
        }

        throw new RuntimeException("No está soportado el EnumTipoFichaje [" + tipoFichaje + "]");
    }

}
