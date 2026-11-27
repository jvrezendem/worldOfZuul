
import java.util.Random;

/**
 * Reúne os sorteios do jogo.
 *
 * @author Paulo Sergio Mendes Taciano
 * @author João Vitor Rezende Marciano
 */
public abstract class GeradorAleatorios {
    private static Random rand = new Random();

    /**
     * Sorteia a durabilidade da chave mestra.
     *
     * @return Um número de 1 a 10.
     */
    public static int durabilidadeChave() {
        return rand.nextInt(1, 11);
    }

    /**
     * Sorteia o estado de uma porta.
     *
     * @return true se a porta deve funcionar.
     */
    public static boolean statusPorta() {
        return rand.nextBoolean();
    }

    /**
     * Sorteia um índice de uma coleção.
     *
     * @param tamanho Quantidade de elementos, maior que zero.
     * @return Um número de 0 até tamanho - 1.
     */
    public static int indiceAleatorio(int tamanho) {
        return rand.nextInt(tamanho);
    }

    /**
     * Sorteia a quantidade de tentativas de abertura de portas.
     *
     * @return Um número de 10 a 30.
     */
    public static int contadorPorta() {
        return rand.nextInt(10, 31);
    }
}
