
/**
 * Representa o jogador
 * 
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Jogador extends ItemMapa {
    private Posicao posicaoDestino;

    /**
     * Cria a figura do jogador.
     *
     * @param localizacao Posição inicial.
     */
    public Jogador(Posicao localizacao) {
        super(localizacao);
        posicaoDestino = null;
    }

    /**
     * Informa para onde o jogador está indo.
     *
     * @return A posição de destino, ou null.
     */
    public Posicao getPosicaoDestino() {
        return posicaoDestino;
    }

    /**
     * Define o destino do jogador.
     *
     * @param localizacaoDestino Posição de destino.
     */
    public void setPosicaoDestino(Posicao localizacaoDestino) {
        this.posicaoDestino = localizacaoDestino;
    }

    /**
     * Move o jogador para a próxima posição, se houver um destino.
     *
     * @param proximaLocalizacao Próxima posição.
     */
    public void executarAcao(Posicao proximaLocalizacao) {
        Posicao destino = getPosicaoDestino();
        if (destino != null) {
            setPosicaoOrigem(proximaLocalizacao);
        }
    }
}
