package br.com.gympass.service;

import br.com.gympass.modelo.PilotoDTO;

import java.util.List;

public class CalculaTempos {

    public List<PilotoDTO> calcular(List<PilotoDTO> pilotoDTOList, Calculo calculo) {

        return calculo.calcular(pilotoDTOList);
    }
}
