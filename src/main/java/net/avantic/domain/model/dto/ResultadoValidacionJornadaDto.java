package net.avantic.domain.model.dto;

public class ResultadoValidacionJornadaDto {

    private final boolean valid;
    private final String mensaje;
    private final Long idEstado;

    public ResultadoValidacionJornadaDto(boolean valid, String mensaje, Long idEstado) {
        this.valid = valid;
        this.mensaje = mensaje;
        this.idEstado = idEstado;
    }

    public boolean isValid() {
        return valid;
    }

    public String getMensaje() {
        return mensaje;
    }

    public Long getIdEstado() {
        return idEstado;
    }
}
