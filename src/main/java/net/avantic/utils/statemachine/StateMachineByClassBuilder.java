package net.avantic.utils.statemachine;

import net.avantic.utils.ValidadorStateMachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StateMachineByClassBuilder {

    List<Transition> transitions = new ArrayList<Transition>();
    ValidadorStateMachine.Estado initialState;

    public StateMachineByClassBuilder addTransitions(ClassTransition... t) {
        transitions.addAll(Arrays.asList(t));
        return this;
    }

    public StateMachineBuilder setInitialState(ValidadorStateMachine.Estado initialState) {
        this.initialState = initialState;
        return new StateMachineBuilder(this);
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public ValidadorStateMachine.Estado getInitialState() {
        return initialState;
    }
}
