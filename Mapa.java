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


    public PosicaoMatriz[][] getMatriz() {
        return matriz;
    }

    public List<ItemMapa> getItensNaPosicao(int x, int y) {
        if (!estaDentro(x, y)) {
            throw new IndexOutOfBoundsException("A posição está fora do mapa");
        }

        return matriz[x][y].getItensNaPosicao();
    }

    public void adicionarItem(ItemMapa i) {
        getItensNaPosicao(i.getPosicaoOrigem().getX(), i.getPosicaoOrigem().getY()).add(i);
    }

    public void removerItem(ItemMapa i) {
        getItensNaPosicao(i.getPosicaoOrigem().getX(), i.getPosicaoOrigem().getY()).remove(i);
    }

    public void atualizarMapa(ItemMapa i) {
        removerItem(i);
        adicionarItem(i);
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }

    public boolean podePassar(int x, int y) {

        if (!estaDentro(x, y)) {
            throw new RuntimeException("Posição inválida");
        }

        return estaDentro(x, y) && !matriz[x][y].temParede();
    }

    public boolean estaDentro(int x, int y) {
        return x >= 0 && x < largura
                && y >= 0 && y < altura;
    }

    public List<Posicao> encontrarCaminho(
            Posicao origem,
            Posicao destino) {

        HeuristicaAEstrela heuristica = new HeuristicaAEstrela(this);

        return heuristica.encontrarCaminho(
                origem,
                destino);
    }

}
