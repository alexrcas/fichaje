package net.avantic.domain.model.dto;

import net.avantic.domain.model.EnumTipoFichaje;

public class SolicitudAnulacionDto {

    private final String usuario;
    private final Long id;
    private final EnumTipoFichaje tipoFichaje;

    public SolicitudAnulacionDto(String usuario, Long id, EnumTipoFichaje tipoFichaje) {
        this.usuario = usuario;
        this.id = id;
        this.tipoFichaje = tipoFichaje;
    }

    public String getUsuario() {
        return usuario;
    }

    public Long getId() {
        return id;
    }

    public EnumTipoFichaje getTipoFichaje() {
        return tipoFichaje;
    }
}
