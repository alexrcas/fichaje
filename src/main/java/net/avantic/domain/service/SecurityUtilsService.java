package net.avantic.domain.service;

import net.avantic.domain.model.Empleado;
import net.avantic.domain.model.Role;

import java.util.List;

public interface SecurityUtilsService {

    Empleado getAuthenticatedUser();

    List<Role> listAuthenticatedUserRoles();
}
