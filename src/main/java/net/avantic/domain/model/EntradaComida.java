package net.avantic.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import net.avantic.utils.FichajeVisitor;

import java.io.Serializable;

@Entity
@PrimaryKeyJoinColumn(name = "idFichaje")
public class EntradaComida extends Fichaje implements Serializable {

    protected EntradaComida() {
    }

    @Override
    public void accept(FichajeVisitor visitor) {
        visitor.visit(this);
    }

    public EntradaComida(JornadaEmpleado jornadaEmpleado) {
        super(jornadaEmpleado);
    }
}
