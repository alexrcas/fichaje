package net.avantic.domain.service.impl;

import net.avantic.domain.dao.EmpleadoRepository;
import net.avantic.domain.dao.RoleRepository;
import net.avantic.domain.model.Empleado;
import net.avantic.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Empleado> empleadoOpt = empleadoRepository.findByEmail(username);
        if (empleadoOpt.isEmpty()) {
            throw new UsernameNotFoundException("Not user found for username " + username);
        }

        if (empleadoOpt.get().isAccountLocked()) {
            throw new RuntimeException("Account is locked for user " + username);
        }

        return new UserDetailsImpl(empleadoOpt.get(), roleRepository);
    }
}
