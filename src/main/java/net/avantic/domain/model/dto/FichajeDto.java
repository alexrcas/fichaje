package net.avantic.domain.model.dto;

import net.avantic.domain.model.EntradaJornada;
import net.avantic.domain.model.EnumTipoFichaje;
import net.avantic.domain.model.Fichaje;
import net.avantic.domain.model.SalidaJornada;
import net.avantic.utils.FichajeVisitor;

import java.time.LocalDateTime;

public class FichajeDto {

    private final LocalDateTime created;
    private final EnumTipoFichaje tipoFichaje;

    public FichajeDto(LocalDateTime created, EnumTipoFichaje tipoFichaje) {
        this.created = created;
        this.tipoFichaje = tipoFichaje;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public EnumTipoFichaje getTipoFichaje() {
        return tipoFichaje;
    }


}
