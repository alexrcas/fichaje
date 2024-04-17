package net.avantic.domain.model.dto;

import net.avantic.domain.model.JornadaEmpleado;

public class JornadaDto {

    private final Long id;
    private final String horas;

    public JornadaDto(Long id, String horas) {
        this.id = id;
        this.horas = horas;
    }

    protected JornadaDto() {
        this.id = -1L;
        this.horas = "N/A";
    }

    public Long getId() {
        return id;
    }

    public String getHoras() {
        return horas;
    }

    public static JornadaDto emptyDto() {
        return new JornadaDto();
    }
}
