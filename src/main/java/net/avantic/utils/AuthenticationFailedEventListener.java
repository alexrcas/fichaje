package net.avantic.utils;

import net.avantic.domain.dao.EmpleadoRepository;
import net.avantic.domain.model.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthenticationFailedEventListener {

    private final EmpleadoRepository empleadoRepository;
    private final long LOGIN_ATTEMPS_LIMIT;

    @Autowired
    public AuthenticationFailedEventListener(EmpleadoRepository empleadoRepository, @Value("${login.attemps.limit}") Long login_attemps_limit) {
        this.empleadoRepository = empleadoRepository;
        LOGIN_ATTEMPS_LIMIT = login_attemps_limit;
    }

    @EventListener
    public void authenticationFailed(AuthenticationFailureBadCredentialsEvent event) {

        String username = event.getAuthentication().getName();
        Optional<Empleado> empleadoOpt = empleadoRepository.findByEmail(username);
        if (empleadoOpt.isEmpty()) {
            return;
        }

        empleadoOpt.get().setFailedAttemps(empleadoOpt.get().getFailedAttemps() + 1);
        if (empleadoOpt.get().getFailedAttemps() >= LOGIN_ATTEMPS_LIMIT) {
            empleadoOpt.get().setAccountLocked(true);
        }

        empleadoRepository.save(empleadoOpt.get());
    }
}
