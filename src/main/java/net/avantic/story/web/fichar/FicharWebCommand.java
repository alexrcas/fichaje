package net.avantic.story.web.fichar;

import net.avantic.domain.model.EnumTipoFichaje;

import java.time.LocalDateTime;

public class FicharWebCommand {

    private EnumTipoFichaje fichaje;
    private LocalDateTime fecha;

    public EnumTipoFichaje getFichaje() {
        return fichaje;
    }

    public void setFichaje(EnumTipoFichaje fichaje) {
        this.fichaje = fichaje;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
