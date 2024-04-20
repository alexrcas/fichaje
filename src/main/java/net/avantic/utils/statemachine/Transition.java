package net.avantic.utils.statemachine;

public abstract class Transition {

    private final State inicio;
    private final State fin;

    public Transition(State inicio, State fin) {
        this.inicio = inicio;
        this.fin = fin;
    }

    public State getInicio() {
        return inicio;
    }

    public State getFin() {
        return fin;
    }
}
