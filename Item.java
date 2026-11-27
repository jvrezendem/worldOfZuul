/**
 * Algo que pode estar em um ambiente da casa.
 *
 * @author Paulo Sergio Mendes Taciano
 * @author João Vitor Rezende Marciano
 */
public interface Item {

    /**
     * Descreve o item.
     *
     * @return A descrição do item.
     */
    String getDescricao();

    /**
     * Mostra na janela a informação que o item revela quando o jogador entra pela
     * primeira vez no ambiente.
     *
     * @param janela Janela onde a informação é exibida.
     */
    void exibirNaTela(JanelaSimulacao janela);
}
