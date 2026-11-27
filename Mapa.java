import java.util.List;

/**
 * Representa um mapa com todos os itens que participam da simulacao
 * 
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Mapa {
    // matriz de itens
    private PosicaoMatriz[][] matriz;
    private int largura;
    private int altura;


    /**
     * Cria mapa para alocar itens da simulacao.
     * 
     * @param largura: largura da área de simulacao.
     * @param altura:  altura da área de simulação.
     */
    public Mapa(int largura, int altura, List<ItemMapa> itemMapas) {
        this.largura = largura;
        this.altura = altura;

        matriz = new PosicaoMatriz[largura][altura];

        for (int i = 0; i < largura; i++) {
            for (int j = 0; j < altura; j++) {
                matriz[i][j] = new PosicaoMatriz();
            }
        }

        for(ItemMapa item: itemMapas){
            adicionarItem(item);
        }
    }


    /**
     * Informa a matriz do mapa.
     *
     * @return A matriz de células.
     */
    public PosicaoMatriz[][] getMatriz() {
        return matriz;
    }

    /**
     * Informa os itens que ocupam uma posição.
     *
     * @param x Coordenada horizontal.
     * @param y Coordenada vertical.
     * @return A lista de itens da posição.
     * @throws IndexOutOfBoundsException Se a posição estiver fora do mapa.
     */
    public List<ItemMapa> getItensNaPosicao(int x, int y) {
        if (!estaDentro(x, y)) {
            throw new IndexOutOfBoundsException("A posição está fora do mapa");
        }

        return matriz[x][y].getItensNaPosicao();
    }

    /**
     * Coloca um item na posição em que ele se encontra.
     *
     * @param i Item a ser colocado.
     */
    public void adicionarItem(ItemMapa i) {
        getItensNaPosicao(i.getPosicaoOrigem().getX(), i.getPosicaoOrigem().getY()).add(i);
    }

    /**
     * Retira um item da posição em que ele se encontra.
     *
     * @param i Item a ser retirado.
     */
    public void removerItem(ItemMapa i) {
        getItensNaPosicao(i.getPosicaoOrigem().getX(), i.getPosicaoOrigem().getY()).remove(i);
    }

    /**
     * Retira um item e o coloca novamente em sua posição.
     *
     * @param i Item a ser atualizado.
     */
    public void atualizarMapa(ItemMapa i) {
        removerItem(i);
        adicionarItem(i);
    }

    /**
     * Informa a largura do mapa.
     *
     * @return A largura.
     */
    public int getLargura() {
        return largura;
    }

    /**
     * Informa a altura do mapa.
     *
     * @return A altura.
     */
    public int getAltura() {
        return altura;
    }

    /**
     * Informa se o jogador pode ocupar uma posição.
     *
     * @param x Coordenada horizontal.
     * @param y Coordenada vertical.
     * @return true se a posição não tem parede.
     * @throws RuntimeException Se a posição estiver fora do mapa.
     */
    public boolean podePassar(int x, int y) {

        if (!estaDentro(x, y)) {
            throw new RuntimeException("Posição inválida");
        }

        return estaDentro(x, y) && !matriz[x][y].temParede();
    }

    /**
     * Informa se uma posição pertence ao mapa.
     *
     * @param x Coordenada horizontal.
     * @param y Coordenada vertical.
     * @return true se a posição está dentro dos limites.
     */
    public boolean estaDentro(int x, int y) {
        return x >= 0 && x < largura
                && y >= 0 && y < altura;
    }

    /**
     * Calcula um caminho entre duas posições desviando das paredes.
     *
     * @param origem Posição inicial.
     * @param destino Posição final.
     * @return As posições do caminho, ou uma lista vazia se ele não existir.
     */
    public List<Posicao> encontrarCaminho(
            Posicao origem,
            Posicao destino) {

        HeuristicaAEstrela heuristica = new HeuristicaAEstrela(this);

        return heuristica.encontrarCaminho(
                origem,
                destino);
    }

}
