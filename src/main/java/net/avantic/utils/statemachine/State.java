package net.avantic.utils.statemachine;

interface State<T extends Enum<T>> {
    boolean isFinalState();
}
