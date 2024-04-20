package net.avantic.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import net.avantic.utils.FichajeVisitor;

import java.io.Serializable;

@Entity
@PrimaryKeyJoinColumn(name = "idFichaje")
public class EntradaJornada extends Fichaje implements Serializable {

    protected EntradaJornada() {
    }

    @Override
    public void accept(FichajeVisitor visitor) {
        visitor.visit(this);
    }

    public EntradaJornada(JornadaEmpleado jornadaEmpleado) {
        super(jornadaEmpleado);
    }
}
