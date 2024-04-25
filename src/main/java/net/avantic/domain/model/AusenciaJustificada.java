package net.avantic.domain.model;

import jakarta.persistence.*;

@Entity
public class AusenciaJustificada {

    private Long id;
    private JornadaEmpleado jornadaEmpleado;
    private double horas;
    private String motivo;

    protected AusenciaJustificada() {
    }

    public AusenciaJustificada(JornadaEmpleado jornadaEmpleado, double horas, String motivo) {
        this.jornadaEmpleado = jornadaEmpleado;
        this.horas = horas;
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
    @JoinColumn(name = "idJornadaEmpleado")
    public JornadaEmpleado getJornadaEmpleado() {
        return jornadaEmpleado;
    }

    public void setJornadaEmpleado(JornadaEmpleado jornadaEmpleado) {
        this.jornadaEmpleado = jornadaEmpleado;
    }

    public double getHoras() {
        return horas;
    }

    public void setHoras(double horas) {
        this.horas = horas;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
