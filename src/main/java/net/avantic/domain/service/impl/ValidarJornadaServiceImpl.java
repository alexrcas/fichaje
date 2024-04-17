package net.avantic.domain.service.impl;

import net.avantic.domain.dao.*;
import net.avantic.domain.model.*;
import net.avantic.utils.ValidadorStateMachine;
import net.avantic.domain.service.ValidarJornadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ValidarJornadaServiceImpl implements ValidarJornadaService {

    private final FichajeRepository fichajeRepository;
    private final EntradaJornadaRepository entradaJornadaRepository;
    private final SalidaJornadaRepository salidaJornadaRepository;
    private final JornadaEmpleadoRepository jornadaEmpleadoRepository;
    private final EntradaDesayunoRepository entradaDesayunoRepository;
    private final SalidaDesayunoRepository salidaDesayunoRepository;

    @Autowired
    public ValidarJornadaServiceImpl(FichajeRepository fichajeRepository,
                                     EntradaJornadaRepository entradaJornadaRepository,
                                     SalidaJornadaRepository salidaJornadaRepository,
                                     JornadaEmpleadoRepository jornadaEmpleadoRepository,
                                     EntradaDesayunoRepository entradaDesayunoRepository,
                                     SalidaDesayunoRepository salidaDesayunoRepository) {
        this.fichajeRepository = fichajeRepository;
        this.entradaJornadaRepository = entradaJornadaRepository;
        this.salidaJornadaRepository = salidaJornadaRepository;
        this.jornadaEmpleadoRepository = jornadaEmpleadoRepository;
        this.entradaDesayunoRepository = entradaDesayunoRepository;
        this.salidaDesayunoRepository = salidaDesayunoRepository;
    }

    @Override
    public void validarTodas(Empleado empleado) {
        for (JornadaEmpleado jornadaEmpleado : jornadaEmpleadoRepository.findAllByEmpleado(empleado)) {
            validar(jornadaEmpleado);
        }
    }


    @Override
    public List<String> validar(JornadaEmpleado jornadaEmpleado) {

        List<String> validaciones = new ArrayList<>();

        //todo arodriguez: filtrar a nivel de consulta
        if (jornadaEmpleado.isValidada()) {
            return validaciones;
        }

        List<Fichaje> fichajes = fichajeRepository.findAllByJornadaEmpleadoOrderByCreatedAsc(jornadaEmpleado);

        ValidadorStateMachine validadorStateMachine = new ValidadorStateMachine();

        for (Fichaje fichaje : fichajes) {
            boolean success = validadorStateMachine.transitar(fichaje);
            if (!success) {
                break;
            }
        }

        if (!validadorStateMachine.getEstadoActual().isAceptacion()) {
            return List.of("Se esperaba: " + validadorStateMachine.getEstadoActual().transicionesEsperadas() + " - Se encontró: " + validadorStateMachine.getErrorCause());
        }

        jornadaEmpleado.setValidada(true);
        jornadaEmpleadoRepository.save(jornadaEmpleado);

        return validaciones;
    }


}
