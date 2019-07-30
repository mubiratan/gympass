package br.com.gympass;

import br.com.gympass.modelo.LinhaDTO;
import br.com.gympass.modelo.PilotoDTO;
import br.com.gympass.service.CalculadorTempos;
import br.com.gympass.service.MelhorVoltaCorrida;
import br.com.gympass.service.SomaTempos;
import br.com.gympass.service.TempoAposVencedor;
import br.com.gympass.util.Arquivo;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GympassPrincipal {

    @Test
    public void resultadoCorridaTest() throws IOException {

        // Lê arquivo de log 1
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

        CalculadorTempos calculaTempos = new CalculadorTempos();

        // Soma os tempos de cada piloto
        List<PilotoDTO> pilotoTempos = calculaTempos.calcular(pilotoDTOList, new SomaTempos());
        Assert.assertNotNull(pilotoTempos);

        // Retorna melhor volta da corrida 
        List<PilotoDTO>  melhorVoltaCorrida = calculaTempos.calcular(pilotoDTOList, new MelhorVoltaCorrida());
        Assert.assertNotNull(melhorVoltaCorrida);

        // Calcula tempo dos pilostos após vencedor
        List<PilotoDTO> pilotoFinal = calculaTempos.calcular(pilotoTempos, new TempoAposVencedor());
        Assert.assertNotNull(pilotoFinal);

        // Grava arquivo final
        arquivo.gravaArquivo(pilotoFinal, melhorVoltaCorrida);
    }
}
