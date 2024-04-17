package net.avantic.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import net.avantic.utils.FichajeVisitor;

import java.io.Serializable;

@Entity
@PrimaryKeyJoinColumn(name = "idFichaje")
public class SalidaJornada extends Fichaje implements Serializable {

    protected SalidaJornada() {
    }

    @Override
    public void accept(FichajeVisitor visitor) {
        visitor.visit(this);
    }

    public SalidaJornada(Empleado empleado, JornadaEmpleado jornadaEmpleado) {
        super(empleado, jornadaEmpleado);
    }
}
