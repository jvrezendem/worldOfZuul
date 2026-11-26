

public abstract class ItemMapa{

    private Posicao posicaoOrigem;

    public ItemMapa(Posicao posicao){
        posicaoOrigem = posicao;
    }

    public Posicao getPosicaoOrigem() {
        return posicaoOrigem;
    }

    public void setPosicaoOrigem(Posicao localizacaoAtual) {
        this.posicaoOrigem = localizacaoAtual;
    }

    public boolean bloqueiaPassagem(){
        return false;
    }
}
