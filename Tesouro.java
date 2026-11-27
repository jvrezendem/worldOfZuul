/**
 * O tesouro enterrado em um dos ambientes da casa.
 *
 * @author Paulo Sergio Mendes Taciano
 * @author João Vitor Rezende Marciano
 */
public class Tesouro implements Item{

    /**
     * Descreve o tesouro.
     *
     * @return O nome do tesouro.
     */
    @Override
    public String getDescricao() {
        return "Tesouro";
    }

    /**
     * Não mostra nada, pois o tesouro está escondido.
     *
     * @param janela Janela do jogo.
     */
    @Override
    public void exibirNaTela(JanelaSimulacao janela) {
    }
}
