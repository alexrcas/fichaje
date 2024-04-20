package net.avantic.domain.model.dto;

import java.time.Duration;
import java.time.LocalDateTime;

public class ComputoDto {

    private LocalDateTime inicioJornada;
    private LocalDateTime finJornada;

    private LocalDateTime inicioDesayuno;
    private LocalDateTime finDesayuno;

    private LocalDateTime inicioComida;
    private LocalDateTime finComida;


    public LocalDateTime getInicioJornada() {
        return inicioJornada;
    }

    public void setInicioJornada(LocalDateTime inicioJornada) {
        this.inicioJornada = inicioJornada;
    }

    public LocalDateTime getFinJornada() {
        return finJornada;
    }

    public void setFinJornada(LocalDateTime finJornada) {
        this.finJornada = finJornada;
    }

    public double getTiempoJornada() {
        if (inicioJornada == null || finJornada == null) {
            return 0;
        }
        return calcularTiempoTranscurrido(inicioJornada, finJornada);
    }

    public LocalDateTime getInicioDesayuno() {
        return inicioDesayuno;
    }

    public void setInicioDesayuno(LocalDateTime inicioDesayuno) {
        this.inicioDesayuno = inicioDesayuno;
    }

    public LocalDateTime getFinDesayuno() {
        return finDesayuno;
    }

    public void setFinDesayuno(LocalDateTime finDesayuno) {
        this.finDesayuno = finDesayuno;
    }

    public double getTiempoDesayuno() {
        if (inicioDesayuno == null || finDesayuno == null) {
            return 0;
        }
        return calcularTiempoTranscurrido(inicioDesayuno, finDesayuno);
    }

    public LocalDateTime getInicioComida() {
        return inicioComida;
    }

    public void setInicioComida(LocalDateTime inicioComida) {
        this.inicioComida = inicioComida;
    }

    public LocalDateTime getFinComida() {
        return finComida;
    }

    public void setFinComida(LocalDateTime finComida) {
        this.finComida = finComida;
    }

    public double getTiempoComida() {
        if (inicioComida == null || finComida == null) {
            return 0;
        }
        return calcularTiempoTranscurrido(inicioComida, finComida);
    }


    public double getTiempoTotalJornada() {
        return getTiempoJornada() - (getTiempoDesayuno() + getTiempoComida());
    }

    public static double calcularTiempoTranscurrido(LocalDateTime inicio, LocalDateTime fin) {
        Duration duracion = Duration.between(inicio, fin);
        double horasTranscurridas = duracion.toHours();
        long minutosExtras = duracion.minusHours((long) horasTranscurridas).toMinutes();
        double fraccionHora = minutosExtras / 60.0;
        return horasTranscurridas + fraccionHora;
    }
}
