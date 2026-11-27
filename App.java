import javax.swing.JOptionPane;

import exception.ExcecaoJogo;

/**
 * Ponto de entrada do jogo World of Zuul.
 *
 * @author Paulo Sergio Mendes Taciano
 * @author João Vitor Rezende Marciano
 */
public class App {
    /**
     * Cria e inicia uma partida, mostrando uma caixa de mensagem se ela não puder
     * ser montada.
     *
     * @param args Argumentos de linha de comando, não utilizados.
     */
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