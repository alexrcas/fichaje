package net.avantic.domain.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(indexes = @Index(columnList = "fichaje"))
public class Extemporaneo {

    private Long id;
    private Fichaje fichaje;
    private LocalDateTime hora;

    public Extemporaneo() {
    }

    public Extemporaneo(Fichaje fichaje, LocalDateTime hora) {
        this.fichaje = fichaje;
        this.hora = hora;
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
    @JoinColumn(name = "idFichaje")
    public Fichaje getFichaje() {
        return fichaje;
    }

    public void setFichaje(Fichaje fichaje) {
        this.fichaje = fichaje;
    }

    public LocalDateTime getHora() {
        return hora;
    }

    public void setHora(LocalDateTime hora) {
        this.hora = hora;
    }
}
