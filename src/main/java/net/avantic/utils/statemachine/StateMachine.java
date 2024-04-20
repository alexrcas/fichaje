package net.avantic.utils.statemachine;

import java.util.List;
import java.util.Optional;

public class StateMachine {

    List<Transition> transiciones;
    State estadoActual;

    public StateMachine(StateMachineBuilder builder) {
        this.transiciones = builder.getTransitions();
        this.estadoActual = builder.getInitialState();
    }

    boolean transitar(Object entrada) {
        Optional<Transition> transicion = transiciones.stream()
                .filter(t -> t.getInicio().equals(this.estadoActual))
                .filter(t -> t.getEntrada().equals(entrada.getClass()))
                .findAny();

        if (transicion.isEmpty()) {
            return false;
        }

        estadoActual = transicion.get().getFin();
        return true;
    }
}
