package net.avantic.story.ficharextemporaneo;

import net.avantic.domain.model.EnumTipoFichaje;

import java.time.LocalDateTime;

public class FicharExtemporaneoRequestBody {

    private Long idEmpleado;
    private EnumTipoFichaje tipoFichaje;
    private LocalDateTime hora;

    public FicharExtemporaneoRequestBody() {
    }

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public EnumTipoFichaje getTipoFichaje() {
        return tipoFichaje;
    }

    public void setTipoFichaje(EnumTipoFichaje tipoFichaje) {
        this.tipoFichaje = tipoFichaje;
    }

    public LocalDateTime getHora() {
        return hora;
    }

    public void setHora(LocalDateTime hora) {
        this.hora = hora;
    }
}
