/**
 * Representa uma localização no mapa
 * 
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Posicao {
    private int x;
    private int y;

    /**
     * Cria uma localização no mapa
     * 
     * @param x Coordenada x: deve ser maior ou igual a 0.
     * @param y Coordenada y: deve ser maior ou igual a 0.
     */
    public Posicao(int x, int y) {

        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("x ou y menor que 0");
        }

        this.x = x;
        this.y = y;
    }

    /**
     * Informa a coordenada horizontal.
     *
     * @return A coordenada x.
     */
    public int getX() {
        return x;
    }

    /**
     * Informa a coordenada vertical.
     *
     * @return A coordenada y.
     */
    public int getY() {
        return y;
    }

    /**
     * Verificacao de igualdade de conteudo de objetos do tipo Posicao.
     * 
     * @return true: se a localizacao é igual.
     *         false: caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof Posicao)) {
            return false;
        } else {
            Posicao outro = (Posicao) obj;
            return x == outro.x && y == outro.y;
        }
    }

    @Override
    public int hashCode() {
        int num = 17;
        return x * num + y * num;
    }

    /**
     * @return A representacao da localizacao.
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}
