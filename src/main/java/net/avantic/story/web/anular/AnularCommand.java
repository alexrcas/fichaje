package net.avantic.story.web.anular;

public class AnularCommand {

    Long idFichaje;

    public AnularCommand(Long idFichaje) {
        this.idFichaje = idFichaje;
    }

    public Long getIdFichaje() {
        return idFichaje;
    }

    public void setIdFichaje(Long idFichaje) {
        this.idFichaje = idFichaje;
    }
}
