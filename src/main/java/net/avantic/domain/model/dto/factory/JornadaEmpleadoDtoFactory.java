package net.avantic.domain.model.dto.factory;

import net.avantic.domain.model.*;
import net.avantic.domain.model.dto.ComputoDto;
import net.avantic.domain.model.dto.JornadaDto;
import net.avantic.domain.service.FichajeService;
import net.avantic.domain.service.ValidarJornadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class JornadaEmpleadoDtoFactory {

    private final FichajeService fichajeService;
    private final ValidarJornadaService validarJornadaService;
    private final ComputoDtoFactory computoDtoFactory;

    @Autowired
    public JornadaEmpleadoDtoFactory(FichajeService fichajeService,
                                     ValidarJornadaService validarJornadaService,
                                     ComputoDtoFactory computoDtoFactory) {
        this.fichajeService = fichajeService;
        this.validarJornadaService = validarJornadaService;
        this.computoDtoFactory = computoDtoFactory;
    }

    public JornadaDto newDto(JornadaEmpleado jornadaEmpleado) {

        validarJornadaService.validar(jornadaEmpleado);

        if (!jornadaEmpleado.isValidada()) {
            return new JornadaDto(jornadaEmpleado.getId(), "E");
        }

        ComputoDto computoDto = computoDtoFactory.newDto(jornadaEmpleado);
        return new JornadaDto(jornadaEmpleado.getId(), String.valueOf(computoDto.getTiempoTotalJornada()));
    }
}
