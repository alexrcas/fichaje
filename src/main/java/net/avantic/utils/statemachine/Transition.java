package net.avantic.utils.statemachine;

public class Transition {
    private final State inicio;
    private final Class<?> entrada;
    private final State fin;

    Transition(State inicio, Class<?> entrada, State fin) {
        this.inicio = inicio;
        this.entrada = entrada;
        this.fin = fin;
    }

    public State getInicio() {
        return inicio;
    }

    public Class<?> getEntrada() {
        return entrada;
    }

    public State getFin() {
        return fin;
    }
}
