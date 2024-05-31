package net.avantic.job.validarjornada;

import net.avantic.domain.dao.JornadaEmpleadoRepository;
import net.avantic.domain.service.ValidarJornadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ValidarJornadaJob {

    private final ValidarJornadaService validarJornadaService;
    private final JornadaEmpleadoRepository jornadaEmpleadoRepository;

    @Autowired
    public ValidarJornadaJob(ValidarJornadaService validarJornadaService,
                             JornadaEmpleadoRepository jornadaEmpleadoRepository) {
        this.validarJornadaService = validarJornadaService;
        this.jornadaEmpleadoRepository = jornadaEmpleadoRepository;
    }

    @Scheduled(fixedDelay = 3000)
    public void validar() {
        jornadaEmpleadoRepository.findAllByNotValid().stream()
                .forEach(validarJornadaService::validar);
    }
}
