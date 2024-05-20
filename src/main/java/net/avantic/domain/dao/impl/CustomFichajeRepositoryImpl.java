package net.avantic.domain.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import net.avantic.domain.dao.CustomFichajeRepository;
import net.avantic.domain.dao.CustomJornadaEmpleadoRepository;
import net.avantic.domain.dao.FichajeRepository;
import net.avantic.domain.model.Empleado;
import net.avantic.domain.model.Fichaje;
import net.avantic.domain.model.JornadaEmpleado;
import net.avantic.domain.model.Role;
import net.avantic.domain.service.SecurityUtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomFichajeRepositoryImpl implements CustomFichajeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final SecurityUtilsService securityUtilsService;

    @Autowired
    public CustomFichajeRepositoryImpl(SecurityUtilsService securityUtilsService) {
        this.securityUtilsService = securityUtilsService;
    }


    @Override
    public Fichaje get(Long id) {
        Empleado empleadoAutenticado = securityUtilsService.getAuthenticatedUser();

        Fichaje fichaje = entityManager.find(Fichaje.class, id);

        if (fichaje.getJornadaEmpleado().getEmpleado().getEmail().equals(empleadoAutenticado.getEmail())) {
            return fichaje;
        }

        if (authenticatedUserIsAdmin()) {
            return fichaje;
        }

        throw new RuntimeException("El usuario no tiene permiso para ver el fichaje con id " + fichaje.getId());
    }

    private boolean authenticatedUserIsAdmin() {
        return securityUtilsService.listAuthenticatedUserRoles().stream()
                .map(Role::getName)
                .anyMatch("ROLE_ADMIN"::equals);
    }
}
