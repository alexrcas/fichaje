package net.avantic.utils;

import net.avantic.domain.dao.EmpleadoRepository;
import net.avantic.domain.model.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthenticationSuccessEventListener {

    private final EmpleadoRepository empleadoRepository;

    @Autowired
    public AuthenticationSuccessEventListener(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }


    @EventListener
    public void authenticationSuccess(AuthenticationSuccessEvent event) {
        String username =  event.getAuthentication().getName();
        Optional<Empleado> empleado = empleadoRepository.findByEmail(username);
        if (empleado.isEmpty()) {
            return;
        }

        empleado.get().setFailedAttemps(0);
        empleadoRepository.save(empleado.get());
    }
}
