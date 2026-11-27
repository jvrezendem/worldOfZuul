
/**
 * Letra de um letreiro desenhado no mapa para identificar os ambientes.
 */
public class Letra extends ItemMapa{
    private char c;

    /**
     * Cria uma letra.
     *
     * @param posicao Posição da letra.
     * @param letra Caractere a ser desenhado.
     */
    public Letra(Posicao posicao, char letra) {
        super(posicao);
        c = letra;
    }

    /**
     * Informa o caractere.
     *
     * @return O caractere desta letra.
     */
    public char getC() {
        return c;
    }
}
