package net.avantic.domain.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Empleado implements Serializable {

    private Long id;
    private String email;
    private String nombre;
    private String apellidos;
    private String password;
    private int failedAttemps;
    private boolean isAccountLocked;

    public Empleado() {
    }

    public Empleado(String email, String nombre, String apellidos, String password, boolean isAccountLocked) {
        this.email = email;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.password = password;
        this.isAccountLocked = isAccountLocked;
        this.failedAttemps = 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false, length = 64)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(nullable = false, length = 128)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(nullable = false, length = 255)
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(nullable = false)
    public boolean isAccountLocked() {
        return isAccountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        isAccountLocked = accountLocked;
    }

    @Column(nullable = false)
    public int getFailedAttemps() {
        return failedAttemps;
    }

    public void setFailedAttemps(int failedAttemps) {
        this.failedAttemps = failedAttemps;
    }
}
