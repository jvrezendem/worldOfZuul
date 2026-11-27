
/**
 * Classe Ambiente - um ambiente em um jogo adventure.
 *
 * Esta classe eh parte da aplicacao "World of Zuul".
 * "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.  
 *
 * Um "Ambiente" representa uma localizacao no cenario do jogo. Ele eh
 * conectado aos outros ambientes atraves de saidas. As saidas sao
 * nomeadas como norte, sul, leste e oeste. Para cada direcao, o ambiente
 * guarda uma referencia para o ambiente vizinho, ou null se nao ha
 * saida naquela direcao.
 * 
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Ambiente {
    private String nome;
    private String descricao;
    private HashMap<String, Porta> saidas;
    private List<Item> items;
    private boolean temTesouro;
    private Posicao posicaoReferencia;

    /**
     * Cria um ambiente com a "descricao" passada. Inicialmente, ele
     * nao tem saidas. "descricao" eh algo como "uma cozinha" ou
     * "
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "um jardim aberto".
     * 
     * @param descricao A descricao do ambiente.
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
     * @param norte A saida norte.
     * @param leste A saida leste.
     * @param sul   A saida sul.
     * @param oeste A saida oeste.
     */
    public void ajustarSaidas(String nomeAmbiente, Porta saida) {
        if (nomeAmbiente != null && saida != null) {
            saidas.put(nomeAmbiente, saida);
        }

    }

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

    public Porta getPorta(String nomeDestino) {
        Porta porta = saidas.get(nomeDestino);
        if (porta != null) {
            return porta;
        }

        return null;
    }

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

    public void redefinirPortas(){
        for(Porta porta: saidas.values()){
            porta.setStatus();
        }
    }

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

    public String getSaidas() {
        String conjuntoSaidas = "";
        for (String saida : saidas.keySet()) {
            if (saidas.get(saida) != null) {
                conjuntoSaidas = conjuntoSaidas + saida + ", ";
            }
        }

        return conjuntoSaidas;
    }

    public String getItensDescricao() {
        String conjuntoItens = "";
        for (Item item : items) {
            conjuntoItens += (item.getDescricao() + " ");
        }

        return conjuntoItens;
    }

    public void adcionarItem(Item item) {
        if (item instanceof Tesouro)
            temTesouro = true;
        items.add(item);
    }

    public List<Item> getItens() {
        return Collections.unmodifiableList(items);
    }

    public String getNome() {
        return nome;
    }

    public boolean getTemTesouro() {
        return temTesouro;
    }

    public Posicao getPosicaoReferencia() {
        return posicaoReferencia;
    }

}
