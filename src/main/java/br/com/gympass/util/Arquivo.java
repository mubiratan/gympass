package br.com.gympass.util;

import br.com.gympass.modelo.LinhaDTO;
import br.com.gympass.modelo.MelhorVoltaDTO;
import br.com.gympass.modelo.PilotoDTO;

import java.io.*;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Arquivo {

    String caminhoArquivo = "/home/marcio/estudos/gympass/";
    //String caminhoArquivo = "C:\\estudos\\gympass\\";

    // Carrega arquivo de log independente
    // da quantidade de espaços entre as informações
    public List<LinhaDTO> abreArquivo() throws IOException {
        File file = new File(caminhoArquivo + "log");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        List<LinhaDTO> linhas = new ArrayList();

        String line = null;
        int i;

        // Sempre pula a primeira linha
        bufferedReader.readLine();

        while((line = bufferedReader.readLine()) != null) {
            i = 0;
            line = line.trim();

            StringBuilder strLinha = new StringBuilder();
            while(i < line.length()) {
                if(line.charAt(i) != ' ' && line.charAt(i) != '-') {
                    strLinha.append(line.charAt(i));
                } else {
                    if(strLinha.charAt(strLinha.length()-1) != ';')
                        strLinha.append(";");
                }
                i++;
            }

            // Split da linha pelo caracter ";"
            String[] linhaSplit = strLinha.toString().split(";");

            LinhaDTO linha = new LinhaDTO();
            linha.setHora(linhaSplit[0]);
            linha.setCodigoPiloto(linhaSplit[1]);
            linha.setNome(linhaSplit[2]);
            linha.setNumeroVolta(linhaSplit[3]);
            linha.setTempoVolta(linhaSplit[4]);
            linha.setVelocidadeMedia(linhaSplit[5]);
            linhas.add(linha);
        }
        return  linhas;
    }

    // Grava arquivo final
    public void gravaArquivo(List<PilotoDTO> pilotos, MelhorVoltaDTO melhorVoltaDTO) {
        String espaco = " ";
        final int qtdVoltas = 4;

        DecimalFormat df = new DecimalFormat("###,#00.000");
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;

        try {
            FileWriter arq = new FileWriter(caminhoArquivo + "resultado_final.txt");
            PrintWriter gravarArq = new PrintWriter(arq);

            gravarArq.write("Posição Chegada        Código Piloto       " +
                                "Nome Piloto     Voltas Completadas       " +
                                "Tempo Total de Prova       Melhor Volta       " +
                                "Velocidade Média       Tempo Após Vencedor");
            gravarArq.println("");

            // Inicia contador de posição de chegada
            int posicaoChegada = 1;

            for(PilotoDTO p : pilotos) {
                // Grava apenas pilotos quie completaram as 4 voltas
                if (p.getQuantidadeVoltas() == qtdVoltas) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(espaco.repeat(8)).append(posicaoChegada);
                    sb.append(espaco.repeat(21)).append(p.getCodigoPiloto());
                    sb.append(espaco.repeat(10)).append(p.getNome());

                    sb.append(espaco.repeat(25 - p.getNome().length())).
                            append(p.getQuantidadeVoltas());

                    sb.append(espaco.repeat(15)).
                            append(p.getTempoTotal().format(formatter).
                                    subSequence(3, p.getTempoTotal().toString().length() - 1));

                    sb.append(espaco.repeat(31 - p.getTempoTotal().toString().length())).
                            append(p.getMelhorVolta().format(formatter).
                                    subSequence(3, p.getMelhorVolta().toString().length() - 1));

                    sb.append(espaco.repeat(10)).append(df.format(p.getVelocidadeMedia()));

                    sb.append(espaco.repeat(15)).append(p.getTempoAposVencedor().format(formatter).
                            subSequence(3, p.getTempoAposVencedor().toString().length() - 1));

                    gravarArq.write(sb.toString());
                    gravarArq.println("");
                    posicaoChegada++;
                }
            }
                // Melhor Volta da Corrida
                gravarArq.println("");
                gravarArq.write("Melhor volta da corrida: " + melhorVoltaDTO.getNomePiloto() + " : "
                        + melhorVoltaDTO.getMelhorVoltaCorrida().format(formatter)
                        .subSequence(3, melhorVoltaDTO.getMelhorVoltaCorrida().toString().length() - 1));


                arq.flush();
                arq.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
