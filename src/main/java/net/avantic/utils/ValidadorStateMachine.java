package net.avantic.utils;

import net.avantic.domain.model.*;
import net.avantic.domain.model.dto.ResultadoValidacionJornadaDto;
import net.avantic.utils.statemachine.State;
import net.avantic.utils.statemachine.StateMachine;
import net.avantic.utils.statemachine.StateMachineBuilder;
import net.avantic.utils.statemachine.Transition;


public class ValidadorStateMachine {

    private String mensaje;
    private Long idError;

    public enum Estado implements State {

        ESPERANDO_ENTRADA_JORNADA,
        ESPERANDO_SALIDA,
        ESPERANDO_ENTRADA_DESAYUNO,
        ESPERANDO_ENTRADA_COMIDA,
        ESPERANDO_SALIDA_JORNADA_O_SALIDA_COMIDA,
        ESPERANDO_SALIDA_JORNADA,
        FIN_JORNADA,
        ERROR;

        @Override
        public boolean isFinalState() {
            return this.equals(FIN_JORNADA);
        }
    }

    StateMachine stateMachine = new StateMachineBuilder()
            .setInitialState(Estado.ESPERANDO_ENTRADA_JORNADA)
            .addTransitions(
                    new Transition(Estado.ESPERANDO_ENTRADA_JORNADA, EntradaJornada.class, Estado.ESPERANDO_SALIDA),
                    new Transition(Estado.ESPERANDO_SALIDA, SalidaDesayuno.class, Estado.ESPERANDO_ENTRADA_DESAYUNO),
                    new Transition(Estado.ESPERANDO_ENTRADA_DESAYUNO, EntradaDesayuno.class, Estado.ESPERANDO_SALIDA_JORNADA_O_SALIDA_COMIDA),
                    new Transition(Estado.ESPERANDO_SALIDA_JORNADA_O_SALIDA_COMIDA, SalidaJornada.class, Estado.FIN_JORNADA),
                    new Transition(Estado.ESPERANDO_SALIDA, SalidaComida.class, Estado.ESPERANDO_ENTRADA_COMIDA),
                    new Transition(Estado.ESPERANDO_ENTRADA_COMIDA, EntradaComida.class, Estado.ESPERANDO_SALIDA_JORNADA),
                    new Transition(Estado.ESPERANDO_SALIDA_JORNADA, SalidaJornada.class, Estado.FIN_JORNADA)
            )
            .build();

    public boolean transitar(Fichaje fichaje) {
        this.mensaje = "Se estaba en: " + stateMachine.getEstadoActual() + " - Se encontró: " + fichaje.getClass().getName();
        this.idError = fichaje.getId();

        return stateMachine.transitar(fichaje);
    }

    public boolean isInEstadoAceptacion() {
        return stateMachine.getEstadoActual().isFinalState();
    }

    public ResultadoValidacionJornadaDto getResultadoValidacion() {
        if (isInEstadoAceptacion()) {
            return new ResultadoValidacionJornadaDto(true, "", -1L);
        }
        return new ResultadoValidacionJornadaDto(false, mensaje, idError);
    }
}
