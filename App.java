import javax.swing.JOptionPane;

import exception.ExcecaoJogo;

public class App {
    public static void main(String[] args) {
        try {
            Jogo jogo = new Jogo();
            jogo.jogar();
        } catch (ExcecaoJogo e) {
            JOptionPane.showMessageDialog(null, "Falha no jogo: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Configuração inválida: " + e.getMessage());
        }
    }
}