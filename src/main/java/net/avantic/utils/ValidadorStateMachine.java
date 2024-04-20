package net.avantic.utils;

import net.avantic.domain.model.*;
import net.avantic.domain.model.dto.ResultadoValidacionJornadaDto;
import net.avantic.utils.statemachine.*;


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

    StateMachine stateMachine = new StateMachineByClassBuilder()
            .addTransitions(
                    new ClassTransition(Estado.ESPERANDO_ENTRADA_JORNADA, EntradaJornada.class, Estado.ESPERANDO_SALIDA),
                    new ClassTransition(Estado.ESPERANDO_SALIDA, SalidaDesayuno.class, Estado.ESPERANDO_ENTRADA_DESAYUNO),
                    new ClassTransition(Estado.ESPERANDO_SALIDA, SalidaJornada.class, Estado.FIN_JORNADA),
                    new ClassTransition(Estado.ESPERANDO_ENTRADA_DESAYUNO, EntradaDesayuno.class, Estado.ESPERANDO_SALIDA_JORNADA_O_SALIDA_COMIDA),
                    new ClassTransition(Estado.ESPERANDO_SALIDA_JORNADA_O_SALIDA_COMIDA, SalidaJornada.class, Estado.FIN_JORNADA),
                    new ClassTransition(Estado.ESPERANDO_SALIDA_JORNADA_O_SALIDA_COMIDA, SalidaComida.class, Estado.ESPERANDO_ENTRADA_COMIDA),
                    new ClassTransition(Estado.ESPERANDO_SALIDA, SalidaComida.class, Estado.ESPERANDO_ENTRADA_COMIDA),
                    new ClassTransition(Estado.ESPERANDO_ENTRADA_COMIDA, EntradaComida.class, Estado.ESPERANDO_SALIDA_JORNADA),
                    new ClassTransition(Estado.ESPERANDO_SALIDA_JORNADA, SalidaJornada.class, Estado.FIN_JORNADA)
            )
            .setInitialState(Estado.ESPERANDO_ENTRADA_JORNADA)
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
