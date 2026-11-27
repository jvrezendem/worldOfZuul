/**
 * A carga explosiva que o jogador leva na mochila e só pode usar uma vez.
 *
 * @author Paulo Sergio Mendes Taciano
 * @author João Vitor Rezende Marciano
 */
public class CargaExplosiva implements Item{

    /**
     * Descreve a carga.
     *
     * @return O nome da carga.
     */
    @Override
    public String getDescricao() {
        return "Carga Explosiva";
    }

    /**
     * Não mostra nada, pois a carga já está com o jogador.
     *
     * @param janela Janela do jogo.
     */
    @Override
    public void exibirNaTela(JanelaSimulacao janela) {
    }
}
