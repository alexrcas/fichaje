package net.avantic.domain.model;

import jakarta.persistence.*;

@Entity
@Table(indexes = @Index(columnList = "idDia"))
public class Festivo {

    private Long id;

    private Dia dia;
    private String motivo;

    public Festivo() {
    }

    public Festivo(Dia dia, String motivo) {
        this.dia = dia;
        this.motivo = motivo;
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
    @JoinColumn(name = "idDia")
    public Dia getDia() {
        return dia;
    }

    public void setDia(Dia dia) {
        this.dia = dia;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
