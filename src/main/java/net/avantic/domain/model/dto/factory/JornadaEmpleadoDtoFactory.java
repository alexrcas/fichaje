package net.avantic.domain.model.dto.factory;

import net.avantic.domain.dao.AusenciaJustificadaRepository;
import net.avantic.domain.model.*;
import net.avantic.domain.model.dto.ComputoDto;
import net.avantic.domain.model.dto.JornadaDto;
import net.avantic.domain.service.ValidarJornadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JornadaEmpleadoDtoFactory {

    private final ComputoDtoFactory computoDtoFactory;
    private final AusenciaJustificadaRepository ausenciaJustificadaRepository;

    @Autowired
    public JornadaEmpleadoDtoFactory(ComputoDtoFactory computoDtoFactory,
                                     AusenciaJustificadaRepository ausenciaJustificadaRepository) {
        this.computoDtoFactory = computoDtoFactory;
        this.ausenciaJustificadaRepository = ausenciaJustificadaRepository;
    }

    public JornadaDto newDto(JornadaEmpleado jornadaEmpleado) {

        //validarJornadaService.validar(jornadaEmpleado);
        //todo arodriguez: comprobar que efectivamente se puede eliminar
        boolean ausenciaJustificada = ausenciaJustificadaRepository.findAllByJornadaEmpleado(jornadaEmpleado).size() > 0;

        if (!jornadaEmpleado.isValidada()) {
            return new JornadaDto(jornadaEmpleado.getId(), "E", jornadaEmpleado.getDia().getFecha(), ausenciaJustificada);
        }

        ComputoDto computoDto = computoDtoFactory.newDto(jornadaEmpleado);
        return new JornadaDto(jornadaEmpleado.getId(), String.valueOf(computoDto.getTiempoTotalJornada()), jornadaEmpleado.getDia().getFecha(), ausenciaJustificada);
    }
}
