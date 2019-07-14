package br.com.gympass.service;

import br.com.gympass.modelo.MelhorVoltaDTO;
import br.com.gympass.modelo.PilotoDTO;

import java.time.LocalTime;
import java.util.*;

public class CalculaTempos {

    public List<PilotoDTO> somaTempos(Collection<String> pilotos, List<PilotoDTO> pilotoDTOList) {

        LocalTime tempoTotal = LocalTime.of(0,0,0,0);
        LocalTime melhorVoltaPiloto = LocalTime.of(23,0,0,0);

        // Soma os tempos por piloto
        List<PilotoDTO> pilotoTempo = new ArrayList<>();
        for (String p : pilotos) {
            int qtdVoltas = 1;
            double mediaVolta = 0.0;

            tempoTotal = LocalTime.of(0, 0, 0, 0);
            melhorVoltaPiloto = LocalTime.of(23, 0, 0, 0);

            PilotoDTO pilotoTempoSoma = new PilotoDTO();
            // Para cada piloto, soma as voltas
            for (PilotoDTO p1 : pilotoDTOList) {

                // Soma tempos do piloto
                if (p.equalsIgnoreCase(p1.getCodigoPiloto())) {
                    tempoTotal = tempoTotal.plusMinutes(p1.getTempoVolta().getMinute()).
                            plusSeconds(p1.getTempoVolta().getSecond()).
                            plusNanos(p1.getTempoVolta().getNano());

                    pilotoTempoSoma.setCodigoPiloto(p);
                    pilotoTempoSoma.setNome(p1.getNome());
                    pilotoTempoSoma.setQuantidadeVoltas(qtdVoltas++);

                    // Verifica se é a melhor volta do piloto
                    if (p1.getTempoVolta().isBefore(melhorVoltaPiloto)) {
                        pilotoTempoSoma.setMelhorVolta(p1.getTempoVolta());
                        melhorVoltaPiloto = p1.getTempoVolta();
                    }

                    // Calcula Média do Piloto
                    mediaVolta = mediaVolta + p1.getVelocidadeMedia();
                }
            }
            pilotoTempoSoma.setTempoTotal(tempoTotal);
            pilotoTempoSoma.setVelocidadeMedia(mediaVolta / pilotoTempoSoma.getQuantidadeVoltas());
            pilotoTempo.add(pilotoTempoSoma);
        }

        return pilotoTempo;
    }

    public MelhorVoltaDTO retornaMelhorVoltaCorrida(List<PilotoDTO> pilotoDTOList) {
        LocalTime melhorVoltaCorrida = LocalTime.of(23,0,0,0);
        MelhorVoltaDTO melhorVoltaDTO = new MelhorVoltaDTO();

        for(PilotoDTO p : pilotoDTOList) {
            if(p.getTempoVolta().isBefore(melhorVoltaCorrida)) {
                melhorVoltaDTO.setNomePiloto(p.getNome());
                melhorVoltaDTO.setMelhorVoltaCorrida(p.getTempoVolta());
                melhorVoltaCorrida = melhorVoltaDTO.getMelhorVoltaCorrida();
            }
        }

        return melhorVoltaDTO;
    }


    public List<PilotoDTO> calculaTempoAposVencedor(List<PilotoDTO> pilotoDTOList) {
        LocalTime tempoVencedor = LocalTime.of(0,0,0,0);
        boolean isVencedor = true;

        PilotoDTO pilotoDTO = new PilotoDTO();
        List<PilotoDTO> pilotoList = new ArrayList<>();

        for(PilotoDTO p : pilotoDTOList) {
            if(isVencedor) {
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
