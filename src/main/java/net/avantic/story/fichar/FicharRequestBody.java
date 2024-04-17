package net.avantic.story.fichar;

import net.avantic.domain.model.EnumTipoFichaje;

public class FicharRequestBody {

    private Long idEmpleado;
    private EnumTipoFichaje tipoFichaje;

    public FicharRequestBody() {
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
}
