package net.avantic.domain.model.dto;

public class AusenciaJustificadaDto {

    private final double horas;
    private final String motivo;

    public AusenciaJustificadaDto(double horas, String motivo) {
        this.horas = horas;
        this.motivo = motivo;
    }

    public double getHoras() {
        return horas;
    }

    public String getMotivo() {
        return motivo;
    }
}
