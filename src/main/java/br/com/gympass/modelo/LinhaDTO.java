package br.com.gympass.modelo;

public class LinhaDTO {

    private String hora;
    private String nome;
    private String numeroVolta;
    private String tempoVolta;
    private String velocidadeMedia;
    private String codigoPiloto;


    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroVolta() {
        return numeroVolta;
    }

    public void setNumeroVolta(String numeroVolta) {
        this.numeroVolta = numeroVolta;
    }

    public String getTempoVolta() {
        return tempoVolta;
    }

    public void setTempoVolta(String tempoVolta) {
        this.tempoVolta = tempoVolta;
    }

    public String getVelocidadeMedia() {
        return velocidadeMedia;
    }

    public void setVelocidadeMedia(String velocidadeMedia) {
        this.velocidadeMedia = velocidadeMedia;
    }

    public String getCodigoPiloto() {
        return codigoPiloto;
    }

    public void setCodigoPiloto(String codigoPiloto) {
        this.codigoPiloto = codigoPiloto;
    }
}
