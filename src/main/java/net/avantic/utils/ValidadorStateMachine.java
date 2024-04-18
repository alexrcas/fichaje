package net.avantic.utils;

import io.micrometer.common.util.StringUtils;
import net.avantic.domain.model.*;
import net.avantic.domain.model.dto.ResultadoValidacionJornadaDto;


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

        public String transicionesEsperadas() {
            if (this.equals(ESPERANDO_ENTRADA_JORNADA)) {
                return "Entrada de jornada";
            }

            if (this.equals(ESPERANDO_SALIDA_JORNADA)) {
                return "Salida de jornada";
            }

            if (this.equals(ESPERANDO_ENTRADA_DESAYUNO)) {
                return "Entrada de desayuno";
            }

            if (this.equals(ESPERANDO_SALIDA_JORNADA_O_SALIDA_COMIDA)) {
                return "Salida de jornada o Salida a comida";
            }

            if (this.equals(ESPERANDO_ENTRADA_COMIDA)) {
                return "Entrada de comida";
            }

            if (this.equals(ESPERANDO_SALIDA)) {
                return "Salida a desayuno o Salida a comida o Salida de jornada";
            }

            if (this.equals(ERROR)) {
                return "Nada";
            }

            return "";
        }
    }

    private Estado estadoActual;
    private String errorCause;
    private Long idFichajeError;

    public ValidadorStateMachine() {
        this.estadoActual = Estado.ESPERANDO_ENTRADA_JORNADA;
    }

    public Estado getEstadoActual() {
        return estadoActual;
    }

    public String getErrorCause() {
        return errorCause;
    }

    public ResultadoValidacionJornadaDto getResultadoValidacion() {
        if (estadoActual.isAceptacion) {
            return new ResultadoValidacionJornadaDto(true, "", -1L);
        }

        if (StringUtils.isBlank(errorCause)) {
            String mensaje = "Se esperaba encontrar: " + estadoActual.transicionesEsperadas();
            return new ResultadoValidacionJornadaDto(false, mensaje, idFichajeError == null ? -1L : idFichajeError);
        }

        String mensaje = "Se esperaba: " + estadoActual.transicionesEsperadas() + " - Se encontró: " + errorCause;
        return new ResultadoValidacionJornadaDto(false, mensaje, idFichajeError == null ? -1L : idFichajeError);
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
            idFichajeError = entradaJornada.getId();
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
            idFichajeError = salidaJornada.getId();
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
            idFichajeError = entradaDesayuno.getId();
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
            idFichajeError = salidaDesayuno.getId();
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
            idFichajeError = entradaComida.getId();
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
            idFichajeError = salidaComida.getId();
        }
    }
}
