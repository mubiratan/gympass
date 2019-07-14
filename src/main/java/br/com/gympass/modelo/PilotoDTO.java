package br.com.gympass.modelo;

import java.time.LocalTime;

public class PilotoDTO  implements Comparable<PilotoDTO> {

    private LocalTime hora;
    private String nome;
    private String numeroVolta;
    private LocalTime tempoVolta;
    private double velocidadeMedia;
    private String codigoPiloto;
    private LocalTime tempoTotal;
    private int quantidadeVoltas;
    private LocalTime melhorVolta;
    private Double velocidadeMediaCorrida;
    private LocalTime tempoAposVencedor;

    public PilotoDTO(){}

    // Construtor
    public PilotoDTO(LinhaDTO linha) {
        this.hora = LocalTime.of(
                Integer.parseInt(linha.getHora().substring(0,2)),
                Integer.parseInt(linha.getHora().substring(3,5)),
                Integer.parseInt(linha.getHora().substring(6,8)),
                Integer.parseInt(linha.getHora().substring(9,12)));

        this.tempoVolta =  LocalTime.of(00,
                Integer.parseInt(linha.getTempoVolta().substring(0,1)),
                Integer.parseInt(linha.getTempoVolta().substring(2,4)),
                Integer.parseInt(linha.getTempoVolta().substring(5,8)));

        this.nome = linha.getNome();
        this.numeroVolta = linha.getNumeroVolta();
        this.velocidadeMedia = Double.parseDouble(linha.getVelocidadeMedia().replace(",","."));
        this.codigoPiloto = linha.getCodigoPiloto();
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNumeroVolta(String numeroVolta) {
        this.numeroVolta = numeroVolta;
    }

    public void setTempoVolta(LocalTime tempoVolta) {
        this.tempoVolta = tempoVolta;
    }

    public void setVelocidadeMedia(double velocidadeMedia) {
        this.velocidadeMedia = velocidadeMedia;
    }

    public void setCodigoPiloto(String codigoPiloto) {
        this.codigoPiloto = codigoPiloto;
    }

    public LocalTime getHora() {
        return hora;
    }

    public String getNome() {
        return nome;
    }

    public String getNumeroVolta() {
        return numeroVolta;
    }

    public LocalTime getMelhorVolta() { return melhorVolta; }

    public void setMelhorVolta(LocalTime melhorVolta) { this.melhorVolta = melhorVolta; }

    public Double getVelocidadeMediaCorrida() { return velocidadeMediaCorrida; }

    public void setVelocidadeMediaCorrida(Double velocidadeMediaCorrida) { this.velocidadeMediaCorrida = velocidadeMediaCorrida; }

    public LocalTime getTempoVolta() { return tempoVolta; }

    public double getVelocidadeMedia() {
        return velocidadeMedia;
    }

    public String getCodigoPiloto() {
        return codigoPiloto;
    }

    public LocalTime getTempoAposVencedor() { return tempoAposVencedor; }

    public void setTempoAposVencedor(LocalTime tempoAposVencedor) { this.tempoAposVencedor = tempoAposVencedor; }

    public LocalTime getTempoTotal() {
        return tempoTotal;
    }

    public void setTempoTotal(LocalTime tempoTotal) {
        this.tempoTotal = tempoTotal;
    }

    public int getQuantidadeVoltas() { return quantidadeVoltas; }

    public void setQuantidadeVoltas(int quantidadeVoltas) { this.quantidadeVoltas = quantidadeVoltas; }



    @Override
    public int compareTo(PilotoDTO o) {
        if (this.tempoTotal.isAfter(o.getTempoTotal()))
            return 1;
        return -1;
    }


}
