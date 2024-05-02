package net.avantic.utils;

import net.avantic.domain.dao.RoleRepository;
import net.avantic.domain.model.Empleado;
import net.avantic.domain.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private Empleado empleado;
    private RoleRepository roleRepository;

    public UserDetailsImpl(Empleado empleado, RoleRepository roleRepository) {
        this.empleado = empleado;
        this.roleRepository = roleRepository;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleRepository.findAllByEmpleado(empleado).stream()
                .map(Role::getName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return empleado.getPassword();
    }

    @Override
    public String getUsername() {
        return empleado.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
