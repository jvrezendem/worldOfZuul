

/**
 * Elemento que ocupa uma posição do mapa.
 *
 * @author Paulo Sergio Mendes Taciano
 * @author João Vitor Rezende Marciano
 */
public abstract class ItemMapa{

    private Posicao posicaoOrigem;

    /**
     * Cria um item do mapa.
     *
     * @param posicao Posição ocupada pelo item.
     */
    public ItemMapa(Posicao posicao){
        posicaoOrigem = posicao;
    }

    /**
     * Informa a posição do item.
     *
     * @return A posição atual.
     */
    public Posicao getPosicaoOrigem() {
        return posicaoOrigem;
    }

    /**
     * Altera a posição do item.
     *
     * @param localizacaoAtual Nova posição.
     */
    public void setPosicaoOrigem(Posicao localizacaoAtual) {
        this.posicaoOrigem = localizacaoAtual;
    }

    /**
     * Informa se o item impede a passagem.
     *
     * @return true se o jogador não pode atravessá-lo.
     */
    public boolean bloqueiaPassagem(){
        return false;
    }
}
