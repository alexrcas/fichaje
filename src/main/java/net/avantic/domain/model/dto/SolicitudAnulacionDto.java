package net.avantic.domain.model.dto;

import java.time.LocalDateTime;

public class SolicitudAnulacionDto {

    private final LocalDateTime created;
    private final String usuario;
    private final FichajeDto fichaje;

    public SolicitudAnulacionDto(LocalDateTime created, String usuario, FichajeDto fichaje) {
        this.created = created;
        this.usuario = usuario;
        this.fichaje = fichaje;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public String getUsuario() {
        return usuario;
    }

    public FichajeDto getFichaje() {
        return fichaje;
    }
}
