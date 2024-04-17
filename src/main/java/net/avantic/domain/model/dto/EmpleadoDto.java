package net.avantic.domain.model.dto;

public class EmpleadoDto {

    private final Long id;
    private final String email;

    public EmpleadoDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
