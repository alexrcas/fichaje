package net.avantic.domain.model;

import jakarta.persistence.*;

@Entity
@Table(indexes = {@Index(columnList = "idSolicitudAnulacion")})
public class AnulacionFichaje {

    private Long id;
    private SolicitudAnulacion solicitudAnulacion;

    protected AnulacionFichaje() {}

    public AnulacionFichaje(SolicitudAnulacion solicitudAnulacion) {
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSolicitudAnulacion", nullable = false)
    public SolicitudAnulacion getSolicitudAnulacion() {
        return solicitudAnulacion;
    }

    public void setSolicitudAnulacion(SolicitudAnulacion solicitudAnulacion) {
        this.solicitudAnulacion = solicitudAnulacion;
    }
}
