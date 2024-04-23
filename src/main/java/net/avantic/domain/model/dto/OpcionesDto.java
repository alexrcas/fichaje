package net.avantic.domain.model.dto;

import net.avantic.domain.model.EnumTipoFichaje;

import java.util.Arrays;
import java.util.List;

public class OpcionesDto {

    private final List<EnumTipoFichaje> tiposFichaje = Arrays.asList(EnumTipoFichaje.values());
    private final EnumTipoFichaje tipoFichajeSugerido;

    public OpcionesDto(EnumTipoFichaje tipoFichajeSugerido) {
        this.tipoFichajeSugerido = tipoFichajeSugerido;
    }

    public List<EnumTipoFichaje> getTiposFichaje() {
        return tiposFichaje;
    }

    public EnumTipoFichaje getTipoFichajeSugerido() {
        return tipoFichajeSugerido;
    }
}
