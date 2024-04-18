package net.avantic.domain.model.dto;

import net.avantic.domain.model.Fichaje;

import java.time.LocalDateTime;

public class FichajeOrdenJornadaSpecification {

    private final Fichaje fichaje;
    private final boolean extemporaneo;
    private final LocalDateTime horaFichaje;

    public FichajeOrdenJornadaSpecification(Fichaje fichaje, boolean extemporaneo, LocalDateTime horaFichaje) {
        this.fichaje = fichaje;
        this.extemporaneo = extemporaneo;
        this.horaFichaje = horaFichaje;
    }

    public Fichaje getFichaje() {
        return fichaje;
    }

    public boolean isExtemporaneo() {
        return extemporaneo;
    }

    public LocalDateTime getHoraFichaje() {
        return horaFichaje;
    }
}
