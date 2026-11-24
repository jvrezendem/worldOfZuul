
/**
 * Representa o jogador
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Jogador extends ItemMapa{
    private Posicao posicaoDestino;

    
    public Jogador(Posicao localizacao) {
        super(localizacao);
        posicaoDestino = null;
    }

   
    public Posicao getPosicaoDestino() {
        return posicaoDestino;
    }


    public void setPosicaoDestino(Posicao localizacaoDestino) {
        this.posicaoDestino = localizacaoDestino;
    }
    
    public void executarAcao(Posicao proximaLocalizacao) {
        Posicao destino = getPosicaoDestino();
        if(destino != null){
            setPosicaoOrigem(proximaLocalizacao);
        }
    } 
}
