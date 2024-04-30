package net.avantic.domain.model.dto;

public class FestivoDto {

    private final Long id;
    private final DiaDto dia;
    private final String motivo;

    public FestivoDto(Long id, DiaDto dia, String motivo) {
        this.id = id;
        this.dia = dia;
        this.motivo = motivo;
    }

    public Long getId() {
        return id;
    }

    public DiaDto getDia() {
        return dia;
    }

    public String getMotivo() {
        return motivo;
    }
}
