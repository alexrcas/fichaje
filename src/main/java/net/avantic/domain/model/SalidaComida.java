package net.avantic.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import net.avantic.utils.FichajeVisitor;

import java.io.Serializable;

@Entity
@PrimaryKeyJoinColumn(name = "idFichaje")
public class SalidaComida extends Fichaje implements Serializable {

    protected SalidaComida() {
    }

    @Override
    public void accept(FichajeVisitor visitor) {
        visitor.visit(this);
    }

    public SalidaComida(JornadaEmpleado jornadaEmpleado) {
        super(jornadaEmpleado);
    }
}
