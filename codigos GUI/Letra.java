
public class Letra extends ItemMapa{
    private char c;

    public Letra(Posicao posicao, char letra) {
        super(posicao);
        c = letra;
    }

    public char getC() {
        return c;
    }
}
