package net.avantic.utils.statemachine;

public class ReferenceTransition extends Transition {

    private final Class<?> entrada;

    public ReferenceTransition(State inicio, Class<?> entrada, State fin) {
        super(inicio, fin);
        this.entrada = entrada;
    }

    public Class<?> getEntrada() {
        return entrada;
    }
}
