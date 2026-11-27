import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Classe Ambiente - um ambiente em um jogo adventure.
 *
 * Esta classe eh parte da aplicacao "World of Zuul".
 * "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.
 *
 * Um "Ambiente" representa uma localizacao no cenario do jogo. Ele eh
 * conectado aos outros ambientes atraves de saidas. Cada saida eh uma
 * porta identificada pelo nome do ambiente de destino.
 *
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
 */
public class Ambiente {
    private String nome;
    private String descricao;
    private HashMap<String, Porta> saidas;
    private List<Item> items;
    private boolean temTesouro;
    private Posicao posicaoReferencia;

    /**
     * Cria um ambiente sem saidas e sem itens.
     *
     * @param nome Nome do ambiente.
     * @param descricao Descricao mostrada ao jogador.
     * @param ref Posicao do ambiente no mapa.
     */
    public Ambiente(String nome, String descricao, Posicao ref) {
        this.nome = Objects.requireNonNull(nome, "O nome do ambiente não pode ser nulo.");
        this.descricao = Objects.requireNonNull(descricao, "A descrição do ambiente não pode ser nula.");
        this.posicaoReferencia = Objects.requireNonNull(ref, "A posição de referência não pode ser nula.");

        if (this.nome.isBlank()) {
            throw new IllegalArgumentException("O nome do ambiente não pode ser vazio.");
        }
        if (this.descricao.isBlank()) {
            throw new IllegalArgumentException("A descrição do ambiente não pode ser vazia.");
        }

        saidas = new HashMap<>();
        items = new ArrayList<>();
        temTesouro = false;
    }

    /**
     * Define as saidas do ambiente. Cada direcao ou leva a um
     * outro ambiente ou eh null (nenhuma saida para la).
     * 
     * @param nomeAmbiente Nome do ambiente de destino.
     * @param saida Porta que leva a esse ambiente.
     */
    public void ajustarSaidas(String nomeAmbiente, Porta saida) {
        if (nomeAmbiente != null && saida != null) {
            saidas.put(nomeAmbiente, saida);
        }

    }

    /**
     * Retira do ambiente os itens que o jogador pode pegar. O tesouro permanece.
     *
     * @return Os itens retirados.
     */
    public List<Item> pegarItensAmbiente() {

        if (getItens() != null) {
            List<Item> listaItensAmbiente = new ArrayList<>();

            Iterator<Item> it = items.iterator();

            while (it.hasNext()) {

                Item item = it.next();

                if (!(item instanceof Tesouro)) {
                    listaItensAmbiente.add(item);
                    it.remove();
                }
            }
            return listaItensAmbiente;
        }

        throw new RuntimeException("Ambiente não possui itens");
    }

    /**
     * Procura a porta que leva a um ambiente, sem diferenciar maiúsculas de
     * minúsculas.
     *
     * @param nomeDestino Nome do ambiente de destino.
     * @return A porta, ou null se não houver.
     */
    public Porta getPorta(String nomeDestino) {
        for (String saida : saidas.keySet()) {
            if (saida.toLowerCase().equals(nomeDestino.toLowerCase())) {
                return saidas.get(saida);
            }
        }
        return null;
    }

    /**
     * Informa para onde leva uma saída.
     *
     * @param nomeAmbiente Nome do destino desejado.
     * @return O ambiente de destino, ou null se não houver saída para ele.
     */
    public Ambiente getAmbienteSaida(String nomeAmbiente) {
        Porta porta = getPorta(nomeAmbiente);
        if (porta == null) {
            return null;
        }
        Ambiente ambiente = porta.getDestino();
        if (ambiente != null) {
            return ambiente;
        }
        return null;
    }

    /**
     * Sorteia novamente o estado de todas as portas deste ambiente.
     */
    public void redefinirPortas(){
        for(Porta porta: saidas.values()){
            porta.setStatus();
        }
    }

    /**
     * Tenta abrir a porta que leva a um ambiente.
     *
     * @param nomeAmbDestino Nome do ambiente de destino.
     * @return true se a porta existe e abriu.
     */
    public boolean tentarSair(String nomeAmbDestino) {
        Porta porta = getPorta(nomeAmbDestino);
        return porta != null && porta.tentarAbrir();

    }

    /**
     * @return A descricao do ambiente.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Informa as saídas do ambiente.
     *
     * @return Os nomes das saídas separados por vírgula.
     */
    public String getSaidas() {
        String conjuntoSaidas = "";
        for (String saida : saidas.keySet()) {
            if (saidas.get(saida) != null) {
                if(!conjuntoSaidas.isEmpty()) {
                    conjuntoSaidas = conjuntoSaidas + ", ";
                }
                conjuntoSaidas = conjuntoSaidas + saida;
            }
        }

        return conjuntoSaidas;
    }

    /**
     * Descreve os itens do ambiente.
     *
     * @return As descrições separadas por espaço.
     */
    public String getItensDescricao() {
        String conjuntoItens = "";
        for (Item item : items) {
            conjuntoItens += (item.getDescricao() + " ");
        }

        return conjuntoItens;
    }

    /**
     * Coloca um item no ambiente.
     *
     * @param item Item a ser colocado.
     */
    public void adcionarItem(Item item) {
        if (item instanceof Tesouro)
            temTesouro = true;
        items.add(item);
    }

    /**
     * Informa os itens do ambiente.
     *
     * @return Uma lista que não pode ser alterada.
     */
    public List<Item> getItens() {
        return Collections.unmodifiableList(items);
    }

    /**
     * Informa o nome do ambiente.
     *
     * @return O nome.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Informa se o tesouro está neste ambiente.
     *
     * @return true se o tesouro está aqui.
     */
    public boolean getTemTesouro() {
        return temTesouro;
    }

    /**
     * Informa a posição do ambiente no mapa.
     *
     * @return A posição.
     */
    public Posicao getPosicaoReferencia() {
        return posicaoReferencia;
    }

}
