package net.avantic.domain.model.dto;

import net.avantic.domain.model.EnumTipoFichaje;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FichajeDto {

    private final Long id;
    private final LocalDateTime created;
    private final boolean extemporaneo;
    private final LocalDateTime horaFichaje;
    private final EnumTipoFichaje tipoFichaje;
    private final boolean pendienteAnulacion;
    private final boolean anulado;

    public FichajeDto(Long id, LocalDateTime created, boolean extemporaneo,
                      LocalDateTime horaFichaje, EnumTipoFichaje tipoFichaje,
                      boolean pendienteAnulacion, boolean anulado) {
        this.id = id;
        this.created = created;
        this.extemporaneo = extemporaneo;
        this.horaFichaje = horaFichaje;
        this.tipoFichaje = tipoFichaje;
        this.pendienteAnulacion = pendienteAnulacion;
        this.anulado = anulado;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public boolean isExtemporaneo() {
        return extemporaneo;
    }

    public LocalDateTime getHoraFichaje() {
        return horaFichaje;
    }

    public String getHoraFichajeHHmm() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return dateTimeFormatter.format(horaFichaje);
    }

    public String getCreatedHHmm() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return dateTimeFormatter.format(created);
    }

    public EnumTipoFichaje getTipoFichaje() {
        return tipoFichaje;
    }

    public boolean isPendienteAnulacion() {
        return pendienteAnulacion;
    }

    public boolean isAnulado() {
        return anulado;
    }
}
