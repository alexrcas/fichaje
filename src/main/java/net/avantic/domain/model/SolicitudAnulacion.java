package net.avantic.domain.model;

import jakarta.persistence.*;

@Entity
@Table(indexes = {@Index(columnList = "idFichaje")})
public class SolicitudAnulacion {

    private Long id;

    private Fichaje fichaje;

    public SolicitudAnulacion() {
    }

    public SolicitudAnulacion(Fichaje fichaje) {
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idFichaje", nullable = false)
    public Fichaje getFichaje() {
        return fichaje;
    }

    public void setFichaje(Fichaje fichaje) {
        this.fichaje = fichaje;
    }
}
