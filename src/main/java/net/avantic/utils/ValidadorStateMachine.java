package net.avantic.utils;

import net.avantic.domain.model.*;
import net.avantic.utils.FichajeVisitor;


import java.util.List;


public class ValidadorStateMachine {

    public enum Estado {
        ESPERANDO_ENTRADA_JORNADA(false),
        ESPERANDO_SALIDA(false),
        ESPERANDO_ENTRADA_DESAYUNO(false),
        ESPERANDO_ENTRADA_COMIDA(false),
        ESPERANDO_SALIDA_JORNADA_O_SALIDA_COMIDA(false),
        ESPERANDO_SALIDA_JORNADA(false),
        FIN_JORNADA(true),
        ERROR(false);

        private boolean isAceptacion;

        Estado(boolean isAceptacion) {
            this.isAceptacion = isAceptacion;
        }

        public boolean isAceptacion() {
            return isAceptacion;
        }

        public List<String> transicionesEsperadas() {
            if (this.equals(ESPERANDO_ENTRADA_JORNADA)) {
                return List.of("Entrada de jornada");
            }

            if (this.equals(ESPERANDO_SALIDA_JORNADA)) {
                return List.of("Salida de jornada");
            }

            if (this.equals(ESPERANDO_ENTRADA_DESAYUNO)) {
                return List.of("Entrada de desayuno");
            }

            if (this.equals(ESPERANDO_SALIDA_JORNADA_O_SALIDA_COMIDA)) {
                return List.of("Salida de jornada", "Salida a comida");
            }

            if (this.equals(ESPERANDO_ENTRADA_COMIDA)) {
                return List.of("Entrada de comida");
            }

            if (this.equals(ESPERANDO_SALIDA)) {
                return List.of("Salida a desayuno", "Salida a comida", "Salida de jornada");
            }

            if (this.equals(ERROR)) {
                return List.of("Nada");
            }

            return List.of();
        }
    }

    private Estado estadoActual;
    private String errorCause;

    public ValidadorStateMachine() {
        this.estadoActual = Estado.ESPERANDO_ENTRADA_JORNADA;
    }

    public Estado getEstadoActual() {
        return estadoActual;
    }

    public String getErrorCause() {
        return errorCause;
    }

    public boolean transitar(Fichaje fichaje) {
        TransitarVisitor visitor = new TransitarVisitor(fichaje);
        return visitor.isSuccess();
    }

    class TransitarVisitor implements FichajeVisitor {

        private boolean success = true;

        public TransitarVisitor(Fichaje fichaje) {
            fichaje.accept(this);
        }

        public boolean isSuccess() {
            return success;
        }


        @Override
        public void visit(EntradaJornada entradaJornada) {
            if (estadoActual.equals(Estado.FIN_JORNADA)) {
                estadoActual = Estado.ERROR;
            }

            if (estadoActual.equals(Estado.ESPERANDO_ENTRADA_JORNADA)) {
                estadoActual = Estado.ESPERANDO_SALIDA;
                return;
            }

            success = false;
            errorCause = "Entrada de Jornada";
        }

        @Override
        public void visit(SalidaJornada salidaJornada) {
            if (estadoActual.equals(Estado.FIN_JORNADA)) {
                estadoActual = Estado.ERROR;
            }

            if (estadoActual.equals(Estado.ESPERANDO_SALIDA_JORNADA_O_SALIDA_COMIDA) || estadoActual.equals(Estado.ESPERANDO_SALIDA) || estadoActual.equals(Estado.ESPERANDO_SALIDA_JORNADA)) {
                estadoActual = Estado.FIN_JORNADA;
                return;
            }
            success = false;
            errorCause = "Salida de Jornada";
        }

        @Override
        public void visit(EntradaDesayuno entradaDesayuno) {
            if (estadoActual.equals(Estado.FIN_JORNADA)) {
                estadoActual = Estado.ERROR;
            }

            if (estadoActual.equals(Estado.ESPERANDO_ENTRADA_DESAYUNO)) {
                estadoActual = Estado.ESPERANDO_SALIDA_JORNADA_O_SALIDA_COMIDA;
                return;
            }
            success = false;
            errorCause = "Entrada de Desayuno";
        }

        @Override
        public void visit(SalidaDesayuno salidaDesayuno) {
            if (estadoActual.equals(Estado.FIN_JORNADA)) {
                estadoActual = Estado.ERROR;
            }

            if (estadoActual.equals(Estado.ESPERANDO_SALIDA)) {
                estadoActual = Estado.ESPERANDO_ENTRADA_DESAYUNO;
                return;
            }
            success = false;
            errorCause = "Salida de Desayuno";
        }

        @Override
        public void visit(EntradaComida entradaComida) {
            if (estadoActual.equals(Estado.FIN_JORNADA)) {
                estadoActual = Estado.ERROR;
            }

            if (estadoActual.equals(Estado.ESPERANDO_ENTRADA_COMIDA)) {
                estadoActual = Estado.ESPERANDO_SALIDA_JORNADA;
                return;
            }
            success = false;
            errorCause = "Entrada de comida";
        }

        @Override
        public void visit(SalidaComida salidaComida) {
            if (estadoActual.equals(Estado.FIN_JORNADA)) {
                estadoActual = Estado.ERROR;
            }

            if (estadoActual.equals(Estado.ESPERANDO_SALIDA_JORNADA_O_SALIDA_COMIDA) || estadoActual.equals(Estado.ESPERANDO_SALIDA)) {
                estadoActual = Estado.ESPERANDO_ENTRADA_COMIDA;
                return;
            }
            success = false;
            errorCause = "Salida de comida";
        }
    }
}
