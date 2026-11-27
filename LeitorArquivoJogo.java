import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import exception.AmbienteNaoEncontradoException;
import exception.ErroArquivoException;

public class LeitorArquivoJogo {
    private int largura;
    private int altura;
    private ArrayList<ItemMapa> itensMapa;
    private HashMap<String, Ambiente> ambientes;

    public LeitorArquivoJogo(String nomeArquivo) {
        itensMapa = new ArrayList<>();
        ambientes = new HashMap<>();
        ler(nomeArquivo);
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }

    public List<ItemMapa> getItensMapa() {
        return new ArrayList<>(itensMapa);
    }

    public List<Ambiente> getAmbientes() {
        return new ArrayList<>(ambientes.values());
    }

    private void ler(String nomeArquivo) {
        try (BufferedReader arq = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha = arq.readLine();

            while (linha != null) {
                interpretarLinha(linha);
                linha = arq.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new ErroArquivoException("Impossivel abrir o arquivo " + nomeArquivo);
        } catch (IOException e) {
            throw new ErroArquivoException("Problema na leitura do arquivo " + nomeArquivo);
        } catch (IllegalArgumentException e) {
            throw new ErroArquivoException("Dado invalido no arquivo " + nomeArquivo + ": " + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            throw new ErroArquivoException("Linha incompleta no arquivo " + nomeArquivo);
        }
    }

    private void interpretarLinha(String linha) {
        if (linha.isEmpty()) {
            return;
        }

        String[] campos = linha.split(";");
        String tipo = campos[0];

        if (tipo.equals("TAMANHO")) {
            largura = Integer.parseInt(campos[1]);
            altura = Integer.parseInt(campos[2]);
        } else if (tipo.equals("PAREDE")) {
            lerParedes(campos[1]);
        } else if (tipo.equals("LETREIRO")) {
            lerLetreiro(campos[1], campos[2]);
        } else if (tipo.equals("AMBIENTE")) {
            Ambiente ambiente = new Ambiente(campos[1], campos[2], lerPosicao(campos[3]));
            ambientes.put(ambiente.getNome(), ambiente);
        } else if (tipo.equals("PORTA")) {
            Ambiente destino = buscarAmbiente(campos[2]);
            buscarAmbiente(campos[1]).ajustarSaidas(destino.getNome(), new Porta(destino));
        } else {
            throw new IllegalArgumentException("Tipo de linha desconhecido: " + tipo);
        }
    }

    private void lerParedes(String posicoes) {
        for (String texto : posicoes.split(" ")) {
            itensMapa.add(new Parede(lerPosicao(texto)));
        }
    }

    private void lerLetreiro(String letras, String posicoes) {
        String[] textos = posicoes.split(" ");

        if (textos.length != letras.length()) {
            throw new IllegalArgumentException("O letreiro " + letras + " precisa de uma posicao por letra");
        }

        for (int i = 0; i < textos.length; i++) {
            itensMapa.add(new Letra(lerPosicao(textos[i]), letras.charAt(i)));
        }
    }

    private Posicao lerPosicao(String texto) {
        String[] coordenadas = texto.split(",");
        int x = Integer.parseInt(coordenadas[0]);
        int y = Integer.parseInt(coordenadas[1]);

        if (x >= largura || y >= altura) {
            throw new IllegalArgumentException("Posicao fora do mapa: " + texto);
        }

        return new Posicao(x, y);
    }

    private Ambiente buscarAmbiente(String nome) {
        Ambiente ambiente = ambientes.get(nome);

        if (ambiente == null) {
            throw new AmbienteNaoEncontradoException(nome);
        }

        return ambiente;
    }
}