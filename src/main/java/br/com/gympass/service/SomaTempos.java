package br.com.gympass.service;

import br.com.gympass.modelo.PilotoDTO;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SomaTempos implements Calculo {

    @Override
    public List<PilotoDTO> calcular(List<PilotoDTO> pilotoDTOList) {

        LocalTime tempoTotal = LocalTime.of(0,0,0,0);
        LocalTime melhorVoltaPiloto = LocalTime.of(23,0,0,0);

        // Cria lista sem pilotos duplicados para realizar a soma de tempos
        Set<String> pilotos = new HashSet<>();
        for(PilotoDTO p : pilotoDTOList) {
            pilotos.add(p.getCodigoPiloto());
        }

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
}
