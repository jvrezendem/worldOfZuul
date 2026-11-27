import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PainelStatus extends JPanel {
    private JLabel valorTentativas;
    private JLabel valorDurabilidade;

    public PainelStatus() {
        valorTentativas = new JLabel("-", SwingConstants.CENTER);
        valorDurabilidade = new JLabel("-", SwingConstants.CENTER);

        setLayout(new GridLayout(4, 1));
        add(new JLabel("Nº de Tentativas Restantes", SwingConstants.CENTER));
        add(valorTentativas);
        add(new JLabel("Durabilidade da Chave Mestra", SwingConstants.CENTER));
        add(valorDurabilidade);
    }

    public void atualizarTentativas(int tentativas) {
        valorTentativas.setText(String.valueOf(tentativas));
    }

    public void atualizarDurabilidade(int durabilidade) {
        valorDurabilidade.setText(String.valueOf(durabilidade));
    }
}