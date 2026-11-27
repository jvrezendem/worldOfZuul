import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 * Painel que lista as dicas encontradas pelo jogador.
 *
 * @author Paulo Sergio Mendes Taciano
 * @author João Vitor Rezende Marciano
 */
public class PainelDicas extends JPanel {
    private static final int COLUNAS = 20;

    private JTextArea areaDicas;

    /**
     * Cria o painel sem nenhuma dica.
     */
    public PainelDicas() {
        areaDicas = new JTextArea(0, COLUNAS);
        areaDicas.setEditable(false);
        areaDicas.setFocusable(false);
        areaDicas.setLineWrap(true);
        areaDicas.setWrapStyleWord(true);

        setLayout(new BorderLayout());
        add(new JLabel("Dicas Encontradas", SwingConstants.CENTER), BorderLayout.NORTH);
        add(areaDicas, BorderLayout.CENTER);
    }

    /**
     * Acrescenta uma dica à lista mostrada.
     *
     * @param dica Texto da dica.
     */
    public void adicionarDica(String dica) {
        areaDicas.append(dica + "\n\n");
    }
}