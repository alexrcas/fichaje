package net.avantic.domain.service.impl;

import net.avantic.domain.dao.DiaRepository;
import net.avantic.domain.dao.JornadaEmpleadoRepository;
import net.avantic.domain.model.Empleado;
import net.avantic.domain.service.ValidadorDiaEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidadorDiaEmpleadoServiceImpl implements ValidadorDiaEmpleadoService {

    private final JornadaEmpleadoRepository jornadaEmpleadoRepository;
    private final DiaRepository diaRepository;

    @Autowired
    public ValidadorDiaEmpleadoServiceImpl(JornadaEmpleadoRepository jornadaEmpleadoRepository,
                                           DiaRepository diaRepository) {
        this.jornadaEmpleadoRepository = jornadaEmpleadoRepository;
        this.diaRepository = diaRepository;
    }


    @Override
    public void validar(Empleado empleado) {

        diaRepository.findAllByFinSemanaOrderByIdAsc(false).stream()
                .forEach(dia -> {
                    //todo arodriguez: contemplar vacaciones
                    jornadaEmpleadoRepository.findByDiaAndEmpleado(dia, empleado)
                            .orElseThrow(() -> new RuntimeException("El empleado no ha registrado el dia"));
                });
    }
}
