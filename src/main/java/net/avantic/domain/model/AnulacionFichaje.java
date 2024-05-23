package net.avantic.domain.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(indexes = {@Index(columnList = "idSolicitudAnulacion")})
public class AnulacionFichaje {

    private Long id;
    private LocalDateTime created;
    private SolicitudAnulacion solicitudAnulacion;

    protected AnulacionFichaje() {
        this.created = LocalDateTime.now();
    }

    public AnulacionFichaje(SolicitudAnulacion solicitudAnulacion) {
        this.created = LocalDateTime.now();
        this.solicitudAnulacion = solicitudAnulacion;
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
    @JoinColumn(name = "idSolicitudAnulacion", nullable = false)
    public SolicitudAnulacion getSolicitudAnulacion() {
        return solicitudAnulacion;
    }

    public void setSolicitudAnulacion(SolicitudAnulacion solicitudAnulacion) {
        this.solicitudAnulacion = solicitudAnulacion;
    }
}
