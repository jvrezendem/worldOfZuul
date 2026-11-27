import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class PainelDicas extends JPanel {
    private static final int COLUNAS = 20;

    private JTextArea areaDicas;

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

    public void adicionarDica(String dica) {
        areaDicas.append(dica + "\n\n");
    }
}