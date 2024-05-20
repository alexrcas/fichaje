package net.avantic.story.web.solicitaranulacion;

import net.avantic.domain.model.EnumTipoFichaje;

import java.time.LocalDateTime;

public class SolicitarAnulacionCommand {

    Long idFichaje;

    public SolicitarAnulacionCommand(Long idFichaje) {
        this.idFichaje = idFichaje;
    }

    public Long getIdFichaje() {
        return idFichaje;
    }

    public void setIdFichaje(Long idFichaje) {
        this.idFichaje = idFichaje;
    }
}
