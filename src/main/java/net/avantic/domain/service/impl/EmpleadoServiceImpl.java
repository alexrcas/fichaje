package net.avantic.domain.service.impl;

import net.avantic.domain.dao.EmpleadoRepository;
import net.avantic.domain.model.Empleado;
import net.avantic.domain.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    @Autowired
    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public Empleado getById(Long id) {
        return empleadoRepository.getById(id);
    }
}
