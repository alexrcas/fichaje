package net.avantic.domain.service.impl;

import net.avantic.domain.dao.*;
import net.avantic.domain.model.*;
import net.avantic.domain.model.dto.FichajeOrdenJornadaSpecification;
import net.avantic.domain.model.dto.ResultadoValidacionJornadaDto;
import net.avantic.domain.service.FichajeService;
import net.avantic.utils.ValidadorStateMachine;
import net.avantic.domain.service.ValidarJornadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidarJornadaServiceImpl implements ValidarJornadaService {

    private final FichajeService fichajeService;
    private final JornadaEmpleadoRepository jornadaEmpleadoRepository;

    @Autowired
    public ValidarJornadaServiceImpl(FichajeService fichajeService,
                                     JornadaEmpleadoRepository jornadaEmpleadoRepository) {
        this.fichajeService = fichajeService;
        this.jornadaEmpleadoRepository = jornadaEmpleadoRepository;
    }


    @Override
    public ResultadoValidacionJornadaDto validar(JornadaEmpleado jornadaEmpleado) {

        if (jornadaEmpleado.isValidada()) {
            return new ResultadoValidacionJornadaDto(true, "", -1L);
        }

        List<Fichaje> fichajes = fichajeService.listFichajesOrdenJornada(jornadaEmpleado).stream()
                .map(FichajeOrdenJornadaSpecification::getFichaje)
                .toList();


        ValidadorStateMachine validadorStateMachine = new ValidadorStateMachine();

        for (Fichaje fichaje : fichajes) {
            boolean success = validadorStateMachine.transitar(fichaje);
            if (!success) {
                break;
            }
        }


        if (!validadorStateMachine.isInEstadoAceptacion()) {
            return validadorStateMachine.getResultadoValidacion();
        }

        jornadaEmpleado.setValidada(true);
        jornadaEmpleadoRepository.save(jornadaEmpleado);

        return validadorStateMachine.getResultadoValidacion();
    }


}
