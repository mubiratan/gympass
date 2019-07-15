package br.com.gympass.service;

import br.com.gympass.modelo.PilotoDTO;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TempoAposVencedor implements Calculo {

    @Override
    public List<PilotoDTO> calcular(List<PilotoDTO> pilotos) {

        //Ordena os pilotos do menor tempo para o maior
        Collections.sort(pilotos);

        LocalTime tempoVencedor = LocalTime.of(0,0,0,0);
        boolean isVencedor = true;

        PilotoDTO pilotoDTO = new PilotoDTO();
        List<PilotoDTO> pilotoList = new ArrayList<>();

        for(PilotoDTO p : pilotos) {
            if(isVencedor && p.getQuantidadeVoltas() == 4) {
                p.setTempoAposVencedor(tempoVencedor);
                tempoVencedor = p.getTempoTotal();
                isVencedor = false;
            } else {
                p.setTempoAposVencedor(p.getTempoTotal().
                        minusMinutes(tempoVencedor.getMinute()).
                        minusSeconds(tempoVencedor.getSecond()).
                        minusNanos(tempoVencedor.getNano()));
            }
            pilotoList.add(p);
        }

        return pilotoList;
    }
}
