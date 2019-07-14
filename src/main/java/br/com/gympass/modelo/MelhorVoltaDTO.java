package br.com.gympass.modelo;

import java.time.LocalTime;

public class MelhorVoltaDTO {

    private String nomePiloto;
    private LocalTime melhorVoltaCorrida;

    public String getNomePiloto() {
        return nomePiloto;
    }

    public void setNomePiloto(String nomePiloto) {
        this.nomePiloto = nomePiloto;
    }

    public LocalTime getMelhorVoltaCorrida() {
        return melhorVoltaCorrida;
    }

    public void setMelhorVoltaCorrida(LocalTime melhorVoltaCorrida) {
        this.melhorVoltaCorrida = melhorVoltaCorrida;
    }
}
