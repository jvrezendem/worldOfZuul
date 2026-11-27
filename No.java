/**
 * Nó da busca A*, com uma posição, o nó anterior e os custos do caminho.
 */
public class No {

    private Posicao posicao;
    private No pai;

    private int custoG;
    private int custoH;

    /**
     * Cria um nó da busca.
     *
     * @param posicao Posição representada.
     * @param pai Nó anterior, ou null na origem.
     * @param custoG Custo da origem até esta posição.
     * @param custoH Estimativa do custo até o destino.
     */
    public No(Posicao posicao, No pai, int custoG, int custoH) {
        this.posicao = posicao;
        this.pai = pai;
        this.custoG = custoG;
        this.custoH = custoH;
    }

    /**
     * Informa a posição do nó.
     *
     * @return A posição.
     */
    public Posicao getPosicao() {
        return posicao;
    }

    /**
     * Informa o nó anterior.
     *
     * @return O nó pai, ou null na origem.
     */
    public No getPai() {
        return pai;
    }

    /**
     * Informa o custo já percorrido.
     *
     * @return O custo da origem até aqui.
     */
    public int getCustoG() {
        return custoG;
    }

    /**
     * Informa a estimativa restante.
     *
     * @return O custo estimado até o destino.
     */
    public int getCustoH() {
        return custoH;
    }

    /**
     * Informa o custo total estimado.
     *
     * @return A soma dos custos G e H.
     */
    public int getCustoF() {
        return custoG + custoH;
    }
}
