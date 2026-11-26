
import java.util.ArrayList;
import java.util.List;

public class PosicaoMatriz {

    private List<ItemMapa> itensNaPosicao;
    
    public PosicaoMatriz(){
        itensNaPosicao = new ArrayList<>();
    }

    public List<ItemMapa> getItensNaPosicao() {
        return itensNaPosicao;
    }

    public boolean temParede() {
        for(ItemMapa item: itensNaPosicao){
            if(item.bloqueiaPassagem()){
                return true;
            }
        }
        return false;
    }

    
}
