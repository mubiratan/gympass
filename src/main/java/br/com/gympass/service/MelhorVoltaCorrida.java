package br.com.gympass.service;

import br.com.gympass.modelo.PilotoDTO;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MelhorVoltaCorrida implements Calculo {

    @Override
    public List<PilotoDTO> calcular(List<PilotoDTO> pilotos) {

        LocalTime melhorVoltaCorrida = LocalTime.of(23,0,0,0);
        List<PilotoDTO> pilotoList = new ArrayList<>();
        PilotoDTO piloto = new PilotoDTO();

        for(PilotoDTO p : pilotos) {
            if(p.getTempoVolta().isBefore(melhorVoltaCorrida)) {
                piloto.setNome(p.getNome());
                piloto.setMelhorVoltaCorrida(p.getTempoVolta());
                melhorVoltaCorrida = piloto.getMelhorVoltaCorrida();
            }
        }
        pilotoList.add(piloto);

        return pilotoList;
    }

}
