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
import java.util.List;

public class Ambiente 
{
    private String nome;
    private String descricao;
    private HashMap<String, Porta> saidas;
    private List<Item> items;
    private boolean temTesouro;

    /**
     * Cria um ambiente com a "descricao" passada. Inicialmente, ele
     * nao tem saidas. "descricao" eh algo como "uma cozinha" ou
     * "
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "um jardim aberto".
     * @param descricao A descricao do ambiente.
     */
    public Ambiente(String nome, String descricao) 
    {
        this.nome = nome;
        this.descricao = descricao;
        saidas = new HashMap<>();
        items = new ArrayList<>();
        temTesouro = false;
    }

    /**
     * Define as saidas do ambiente. Cada direcao ou leva a um
     * outro ambiente ou eh null (nenhuma saida para la).
     * @param norte A saida norte.
     * @param leste A saida leste.
     * @param sul A saida sul.
     * @param oeste A saida oeste.
     */
    public void ajustarSaidas(String nomeAmbiente, Porta saida) 
    {
        if(nomeAmbiente != null && saida != null){
            saidas.put(nomeAmbiente, saida);
        }

    }

    private Porta getPorta(String nomeDestino){
        Porta porta = saidas.get(nomeDestino);
        if(porta != null){
            return porta;
        }

        throw new RuntimeException("Porta nao encontrada");
    }

    public Ambiente getAmbienteSaida(String nomeAmbiente){
        Porta porta = getPorta(nomeAmbiente);
        return  porta.getDestino();
    }

    public boolean podeSair(String nomeAmbiente){
        Porta porta = getPorta(nomeAmbiente);
        return porta.podePassarPorta();
    }

    public String verificarStatusPorta(String nomeAmbiente){
        Porta porta = getPorta(nomeAmbiente);
        return porta.getStatus();
    }

    /**
     * @return A descricao do ambiente.
     */
    public String getDescricao()
    {
        return descricao;
    }

    public String getSaidas(){
        String conjuntoSaidas = "";
        for(String saida: saidas.keySet()){
            if(saidas.get(saida) != null){
                conjuntoSaidas = conjuntoSaidas + saida + ", ";
            }
        }

        return conjuntoSaidas;
    }

    public String getItens(){
        String conjuntoItens = "";
        for(Item item: items){
            conjuntoItens += (item.getDescricao() + " ");
        }

        return conjuntoItens;
    }

    public void adcionarItem(Item item){
        if(item instanceof Tesouro) temTesouro = true;
        items.add(item);
    }

    public List<Item> getItems(){
        return Collections.unmodifiableList(items);
    }

    public String getNome() {
        return nome;
    }

    public boolean getTemTesouro(){
        return temTesouro;
    }
}
