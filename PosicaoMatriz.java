
import java.util.ArrayList;
import java.util.List;

/**
 * Uma célula do mapa, que pode conter vários itens.
 *
 * @author Paulo Sergio Mendes Taciano
 * @author João Vitor Rezende Marciano
 */
public class PosicaoMatriz {

    private List<ItemMapa> itensNaPosicao;
    
    /**
     * Cria uma célula vazia.
     */
    public PosicaoMatriz(){
        itensNaPosicao = new ArrayList<>();
    }

    /**
     * Informa os itens da célula.
     *
     * @return A lista de itens.
     */
    public List<ItemMapa> getItensNaPosicao() {
        return itensNaPosicao;
    }

    /**
     * Informa se a célula está bloqueada.
     *
     * @return true se algum item impede a passagem.
     */
    public boolean temParede() {
        for(ItemMapa item: itensNaPosicao){
            if(item.bloqueiaPassagem()){
                return true;
            }
        }
        return false;
    }

    
}
