package net.avantic.utils.statemachine;

import java.util.List;
import java.util.Optional;

public class StateMachine {

    List<? extends Transition> transiciones;
    boolean referenceTransitions;
    State estadoActual;

    public StateMachine(StateMachineBuilder builder) {
        this.transiciones = builder.getTransitions();
        this.estadoActual = builder.getInitialState();
        this.referenceTransitions = builder.isReferenceTransitions();
    }

    public boolean transitar(Object entrada) {
        if (referenceTransitions) {
            return transitarByReference(entrada);
        }
        return transitarByValue(entrada);
    }

    private boolean transitarByReference(Object entrada) {

        Optional<ReferenceTransition> transicion = transiciones.stream()
                .map(t -> (ReferenceTransition) t)
                .filter(t -> t.getInicio().equals(this.estadoActual))
                .filter(t -> t.getEntrada().equals(entrada.getClass()))
                .findAny();

        if (transicion.isEmpty()) {
            return false;
        }

        estadoActual = transicion.get().getFin();
        return true;
    }

    private boolean transitarByValue(Object entrada) {

        Optional<ValueTransition> transicion = transiciones.stream()
                .map(t -> (ValueTransition) t)
                .filter(t -> t.getInicio().equals(this.estadoActual))
                .filter(t -> t.getEntrada().equals(entrada))
                .findAny();

        if (transicion.isEmpty()) {
            return false;
        }

        estadoActual = transicion.get().getFin();
        return true;
    }

    public State getEstadoActual() {
        return estadoActual;
    }
}
