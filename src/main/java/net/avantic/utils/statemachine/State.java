package net.avantic.utils.statemachine;

public interface State<T extends Enum<T>> {
    boolean isFinalState();
}
