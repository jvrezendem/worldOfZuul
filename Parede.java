/**
 * Representa uma parede do mapa.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Parede extends ItemMapa{

    /**
     * Cria uma parede.
     *
     * @param posicao Posição da parede.
     */
    public Parede(Posicao posicao) {
        super(posicao);
    }

    /**
     * Informa que a parede impede a passagem.
     *
     * @return Sempre true.
     */
    @Override
    public boolean bloqueiaPassagem() {
        return true;
    }

    

}
