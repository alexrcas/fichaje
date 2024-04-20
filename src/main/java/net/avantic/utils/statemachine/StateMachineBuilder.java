package net.avantic.utils.statemachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StateMachineBuilder {

    List<Transition> transitions = new ArrayList<>();
    State initialState;

    public List<Transition> getTransitions() {
        return transitions;
    }

    public State getInitialState() {
        return initialState;
    }

    public StateMachineBuilder setInitialState(State initialState) {
        this.initialState = initialState;
        return this;
    }

    public StateMachineBuilder addTransitions(Transition... t) {
        transitions.addAll(Arrays.asList(t));
        return this;
    }

    public StateMachine build() {
        return new StateMachine(this);
    }
}
