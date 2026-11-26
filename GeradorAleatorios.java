
import java.util.Random;

public abstract class GeradorAleatorios {
    private static Random rand = new Random();

    public static int durabilidadeChave() {
        return rand.nextInt(1, 11);
    }

    public static boolean statusPorta() {
        return rand.nextBoolean();
    }

    public static int indiceAleatorio(int tamanho) {
        return rand.nextInt(tamanho);
    }

    public static int contadorPorta() {
        return rand.nextInt(10, 31);
    }
}
