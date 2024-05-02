package net.avantic.domain.dao;

import net.avantic.domain.model.Empleado;
import net.avantic.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findAllByEmpleado(Empleado empleado);
}
