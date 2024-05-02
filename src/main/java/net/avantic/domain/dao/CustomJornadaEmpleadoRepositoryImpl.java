package net.avantic.domain.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import net.avantic.domain.model.Empleado;
import net.avantic.domain.model.JornadaEmpleado;
import net.avantic.domain.model.Role;
import net.avantic.domain.service.SecurityUtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomJornadaEmpleadoRepositoryImpl implements CustomJornadaEmpleadoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final SecurityUtilsService securityUtilsService;

    @Autowired
    public CustomJornadaEmpleadoRepositoryImpl(SecurityUtilsService securityUtilsService) {
        this.securityUtilsService = securityUtilsService;
    }


    @Override
    public JornadaEmpleado get(Long id) {
        Empleado empleadoAutenticado = securityUtilsService.getAuthenticatedUser();

        JornadaEmpleado jornadaEmpleado = entityManager.find(JornadaEmpleado.class, id);

        if (jornadaEmpleado.getEmpleado().getEmail().equals(empleadoAutenticado.getEmail())) {
            return jornadaEmpleado;
        }

        if (authenticatedUserIsAdmin()) {
            return jornadaEmpleado;
        }

        throw new RuntimeException("El usuario no tiene permiso para ver la jornada con id " + jornadaEmpleado.getId());
    }

    private boolean authenticatedUserIsAdmin() {
        return securityUtilsService.listAuthenticatedUserRoles().stream()
                .map(Role::getName)
                .anyMatch("ROLE_ADMIN"::equals);
    }
}
