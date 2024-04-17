package net.avantic.domain.service.impl;

import net.avantic.domain.dao.JornadaEmpleadoRepository;
import net.avantic.domain.model.Dia;
import net.avantic.domain.model.Empleado;
import net.avantic.domain.model.JornadaEmpleado;
import net.avantic.domain.service.JornadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JornadaServiceImpl implements JornadaService {

    private final JornadaEmpleadoRepository jornadaEmpleadoRepository;

    @Autowired
    public JornadaServiceImpl(JornadaEmpleadoRepository jornadaEmpleadoRepository) {
        this.jornadaEmpleadoRepository = jornadaEmpleadoRepository;
    }

    @Override
    public Optional<JornadaEmpleado> findByDiaAndEmpleado(Dia dia, Empleado empleado) {
        return jornadaEmpleadoRepository.findByDiaAndEmpleado(dia, empleado);
    }

    @Override
    public JornadaEmpleado createJornadaEmpleado(Dia dia, Empleado empleado) {
        JornadaEmpleado jornadaEmpleado = new JornadaEmpleado(empleado, dia);
        return jornadaEmpleadoRepository.save(jornadaEmpleado);
    }
}
