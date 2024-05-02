package net.avantic.story.web.addfestivo;

import java.time.LocalDate;

public class AddFestivoCommand {

    private LocalDate fecha;
    private String motivo;

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
