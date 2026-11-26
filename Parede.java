/**
 * Representa os veiculos da simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Parede extends ItemMapa{

    public Parede(Posicao posicao) {
        super(posicao);
    }

    @Override
    public boolean bloqueiaPassagem() {
        return true;
    }

    

}
