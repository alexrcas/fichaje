package net.avantic.utils.statemachine;

import java.util.List;

public class StateMachineBuilder {

    List<Transition> transitions;
    State initialState;
    boolean referenceTransitions = false;

    public StateMachineBuilder(StateMachineByReferenceBuilder builder) {
        this.transitions = builder.getTransitions();
        this.initialState = builder.getInitialState();
        this.referenceTransitions = true;
    }

    public StateMachineBuilder(StateMachineByValueBuilder builder) {
        this.transitions = builder.getTransitions();
        this.initialState = builder.getInitialState();
        this.referenceTransitions = false;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public State getInitialState() {
        return initialState;
    }

    public boolean isReferenceTransitions() {
        return referenceTransitions;
    }

    public StateMachineBuilder setInitialState(State initialState) {
        this.initialState = initialState;
        return this;
    }


    public StateMachine build() {
        return new StateMachine(this);
    }
}
