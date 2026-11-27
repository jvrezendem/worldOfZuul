import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import exception.AmbienteNaoEncontradoException;
import exception.ErroArquivoException;

/**
 * Lê o arquivo texto que descreve o mapa e os ambientes do jogo.
 *
 * Os campos de cada linha são separados por ponto e vírgula, e cada posição é
 * escrita como x,y. Os tipos de linha são TAMANHO (largura e altura), PAREDE
 * (posições separadas por espaço), LETREIRO (texto e uma posição por letra),
 * AMBIENTE (nome, descrição e posição) e PORTA (origem e destino). A linha
 * TAMANHO vem antes das posições, e os ambientes vêm antes das portas.
 *
 * @author Paulo Sergio Mendes Taciano
 * @author João Vitor Rezende Marciano
 */
public class LeitorArquivoJogo {
    private int largura;
    private int altura;
    private ArrayList<ItemMapa> itensMapa;
    private HashMap<String, Ambiente> ambientes;

    /**
     * Lê todo o conteúdo do arquivo.
     *
     * @param nomeArquivo Nome do arquivo do mapa.
     * @throws exception.ErroArquivoException Se o arquivo não puder ser lido ou
     *         tiver uma linha inválida.
     * @throws exception.AmbienteNaoEncontradoException Se uma porta citar um
     *         ambiente não definido.
     */
    public LeitorArquivoJogo(String nomeArquivo) {
        itensMapa = new ArrayList<>();
        ambientes = new HashMap<>();
        ler(nomeArquivo);
    }

    /**
     * Informa a largura do mapa.
     *
     * @return A largura, em posições.
     */
    public int getLargura() {
        return largura;
    }

    /**
     * Informa a altura do mapa.
     *
     * @return A altura, em posições.
     */
    public int getAltura() {
        return altura;
    }

    /**
     * Informa as paredes e letras lidas.
     *
     * @return Uma cópia da lista de itens do mapa.
     */
    public List<ItemMapa> getItensMapa() {
        return new ArrayList<>(itensMapa);
    }

    /**
     * Informa os ambientes lidos.
     *
     * @return Uma lista com os ambientes, sem ordem definida.
     */
    public List<Ambiente> getAmbientes() {
        return new ArrayList<>(ambientes.values());
    }

    /**
     * Percorre o arquivo linha por linha.
     *
     * @param nomeArquivo Nome do arquivo.
     */
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

    /**
     * Processa uma linha de acordo com o tipo indicado no primeiro campo.
     *
     * @param linha Linha lida do arquivo.
     * @throws IllegalArgumentException Se o conteúdo da linha for inválido.
     */
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

    /**
     * Cria uma parede em cada posição informada.
     *
     * @param posicoes Posições separadas por espaço.
     */
    private void lerParedes(String posicoes) {
        for (String texto : posicoes.split(" ")) {
            itensMapa.add(new Parede(lerPosicao(texto)));
        }
    }

    /**
     * Cria as letras de um letreiro.
     *
     * @param letras Texto do letreiro.
     * @param posicoes Uma posição para cada letra, separadas por espaço.
     * @throws IllegalArgumentException Se a quantidade de posições não for igual à
     *         de letras.
     */
    private void lerLetreiro(String letras, String posicoes) {
        String[] textos = posicoes.split(" ");

        if (textos.length != letras.length()) {
            throw new IllegalArgumentException("O letreiro " + letras + " precisa de uma posicao por letra");
        }

        for (int i = 0; i < textos.length; i++) {
            itensMapa.add(new Letra(lerPosicao(textos[i]), letras.charAt(i)));
        }
    }

    /**
     * Converte um texto no formato x,y em uma posição.
     *
     * @param texto Texto a ser convertido.
     * @return A posição correspondente.
     * @throws IllegalArgumentException Se a posição for inválida.
     */
    private Posicao lerPosicao(String texto) {
        String[] coordenadas = texto.split(",");
        int x = Integer.parseInt(coordenadas[0]);
        int y = Integer.parseInt(coordenadas[1]);

        if (x >= largura || y >= altura) {
            throw new IllegalArgumentException("Posicao fora do mapa: " + texto);
        }

        return new Posicao(x, y);
    }

    /**
     * Procura um ambiente já lido.
     *
     * @param nome Nome do ambiente.
     * @return O ambiente encontrado.
     * @throws exception.AmbienteNaoEncontradoException Se ele não foi definido.
     */
    private Ambiente buscarAmbiente(String nome) {
        Ambiente ambiente = ambientes.get(nome);

        if (ambiente == null) {
            throw new AmbienteNaoEncontradoException(nome);
        }

        return ambiente;
    }
}