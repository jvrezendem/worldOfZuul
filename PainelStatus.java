import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Painel que mostra as tentativas restantes e a durabilidade da chave mestra.
 *
 * @author Paulo Sergio Mendes Taciano
 * @author João Vitor Rezende Marciano
 */
public class PainelStatus extends JPanel {
    private JLabel valorTentativas;
    private JLabel valorDurabilidade;

    /**
     * Cria o painel com os valores ainda não informados.
     */
    public PainelStatus() {
        valorTentativas = new JLabel("-", SwingConstants.CENTER);
        valorDurabilidade = new JLabel("-", SwingConstants.CENTER);

        setLayout(new GridLayout(4, 1));
        add(new JLabel("Nº de Tentativas Restantes", SwingConstants.CENTER));
        add(valorTentativas);
        add(new JLabel("Durabilidade da Chave Mestra", SwingConstants.CENTER));
        add(valorDurabilidade);
    }

    /**
     * Atualiza o número de tentativas restantes.
     *
     * @param tentativas Tentativas restantes.
     */
    public void atualizarTentativas(int tentativas) {
        valorTentativas.setText(String.valueOf(tentativas));
    }

    /**
     * Atualiza a durabilidade da chave mestra.
     *
     * @param durabilidade Durabilidade atual.
     */
    public void atualizarDurabilidade(int durabilidade) {
        valorDurabilidade.setText(String.valueOf(durabilidade));
    }
}