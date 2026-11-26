import exception.ExcecaoJogo;

public class App {
    public static void main(String[] args) {
        try {
            Jogo jogo = new Jogo();
            jogo.jogar();
        } catch (ExcecaoJogo e) {
            System.err.println("Falha no jogo: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Configuração inválida: " + e.getMessage());
        }
    }
}