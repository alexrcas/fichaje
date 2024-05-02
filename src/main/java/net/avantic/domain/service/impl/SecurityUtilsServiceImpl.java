package net.avantic.domain.service.impl;

import net.avantic.domain.dao.EmpleadoRepository;
import net.avantic.domain.dao.RoleRepository;
import net.avantic.domain.model.Empleado;
import net.avantic.domain.model.Role;
import net.avantic.domain.service.SecurityUtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityUtilsServiceImpl implements SecurityUtilsService {

    private final EmpleadoRepository empleadoRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public SecurityUtilsServiceImpl(EmpleadoRepository empleadoRepository, RoleRepository roleRepository) {
        this.empleadoRepository = empleadoRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Empleado getAuthenticatedUser() {
        String name = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return empleadoRepository.findByEmail(name).get();
    }

    @Override
    public List<Role> listAuthenticatedUserRoles() {
        Empleado empleado = getAuthenticatedUser();
        return roleRepository.findAllByEmpleado(empleado);
    }
}
