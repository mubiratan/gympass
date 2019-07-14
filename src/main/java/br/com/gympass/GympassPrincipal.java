package br.com.gympass;

import br.com.gympass.service.CalculaTempos;
import br.com.gympass.modelo.LinhaDTO;
import br.com.gympass.modelo.MelhorVoltaDTO;
import br.com.gympass.modelo.PilotoDTO;
import br.com.gympass.util.Arquivo;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

public class GympassPrincipal {

    @Test
    public void resultadoCorridaTest() throws IOException {
        // Lê arquivo de log
        Arquivo arquivo = new Arquivo();

        List<LinhaDTO> linhas =  arquivo.abreArquivo();
        Assert.assertNotNull(linhas);

        // Para cada linha do LOG, monta objeto Piloto
        List<PilotoDTO> pilotoDTOList = new ArrayList<>();
        for(LinhaDTO l : linhas) {
            PilotoDTO pilotoDTO = new PilotoDTO(l);
            pilotoDTOList.add(pilotoDTO);
        }
        Assert.assertNotNull(pilotoDTOList);

        // Cria lista sem pilotos duplicados para realizar a soma de tempos
        Set<String> pilotos = new HashSet<>();
        for(PilotoDTO p : pilotoDTOList) {
            pilotos.add(p.getCodigoPiloto());
        }
        Assert.assertNotNull(pilotos);

        // Soma os tempos de cada piloto
        CalculaTempos pilotoTempos = new CalculaTempos();
        List<PilotoDTO> pilotoTempo = pilotoTempos.somaTempos(pilotos, pilotoDTOList);

        // Retorna melhor volta da corrida
        MelhorVoltaDTO melhorVoltaDTO = pilotoTempos.retornaMelhorVoltaCorrida(pilotoDTOList);
        Assert.assertNotNull(melhorVoltaDTO);

        //Ordena os pilotos do menor tempo para o maior
        Collections.sort(pilotoTempo);

        // Calcula tempo dos pilostos após vencedor
        List<PilotoDTO> pilotoFinal = pilotoTempos.calculaTempoAposVencedor(pilotoTempo);
        Assert.assertNotNull(pilotoFinal);

        // Grava arquivo final
        arquivo.gravaArquivo(pilotoFinal, melhorVoltaDTO);
    }
}