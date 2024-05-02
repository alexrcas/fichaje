package net.avantic.domain.model.dto;

public class EmpleadoDto {

    private final Long id;
    private final String email;
    private final String nombre;
    private final String apellidos;

    public EmpleadoDto(Long id, String email, String nombre, String apellidos) {
        this.id = id;
        this.email = email;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }
}
