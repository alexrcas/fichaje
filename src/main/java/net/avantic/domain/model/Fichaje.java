package net.avantic.domain.model;

import jakarta.persistence.*;
import net.avantic.utils.FichajeVisitor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(indexes = @Index(columnList = "idJornadaEmpleado"))
public abstract class Fichaje implements Serializable {

    private Long id;

    private JornadaEmpleado jornadaEmpleado;
    private LocalDateTime created;

    @Transient
    public abstract void accept(FichajeVisitor visitor);

    protected Fichaje() {
    }

    public Fichaje(JornadaEmpleado jornadaEmpleado) {
        this.jornadaEmpleado = jornadaEmpleado;
        this.created = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idJornadaEmpleado")
    public JornadaEmpleado getJornadaEmpleado() {
        return jornadaEmpleado;
    }

    public void setJornadaEmpleado(JornadaEmpleado jornadaEmpleado) {
        this.jornadaEmpleado = jornadaEmpleado;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
