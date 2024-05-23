package net.avantic.domain.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(indexes = {@Index(columnList = "idFichaje")})
public class SolicitudAnulacion {

    private Long id;

    private LocalDateTime created;

    private Fichaje fichaje;

    public SolicitudAnulacion() {
        this.created = LocalDateTime.now();
    }

    public SolicitudAnulacion(Fichaje fichaje) {
        this.created = LocalDateTime.now();
        this.fichaje = fichaje;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idFichaje", nullable = false)
    public Fichaje getFichaje() {
        return fichaje;
    }

    public void setFichaje(Fichaje fichaje) {
        this.fichaje = fichaje;
    }
}
