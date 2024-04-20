package net.avantic.utils.statemachine;

public class ClassTransition extends Transition {

    private final Class<?> entrada;

    public ClassTransition(State inicio, Class<?> entrada, State fin) {
        super(inicio, fin);
        this.entrada = entrada;
    }

    public Class<?> getEntrada() {
        return entrada;
    }
}
