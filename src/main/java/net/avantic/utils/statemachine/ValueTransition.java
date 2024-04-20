package net.avantic.utils.statemachine;

public class ValueTransition<T> extends Transition {

    private final Object entrada;

    public ValueTransition(State inicio, Object entrada, State fin) {
        super(inicio, fin);
        this.entrada = entrada;
    }

    public Object getEntrada() {
        return entrada;
    }
}
