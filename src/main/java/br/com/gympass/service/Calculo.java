package br.com.gympass.service;

import br.com.gympass.modelo.PilotoDTO;

import java.util.List;

public interface Calculo {

    public List<PilotoDTO> calcular(List<PilotoDTO> pilotoDTO);
}
