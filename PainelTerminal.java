import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Terminal de texto do jogo, com a área de mensagens e o campo de comandos.
 *
 * @author Paulo Sergio Mendes Taciano
 * @author João Vitor Rezende Marciano
 */
public class PainelTerminal extends JPanel implements ActionListener {
    private static final int LINHAS = 12;

    private JTextArea saida;
    private JTextField entrada;
    private IReceptorComando receptor;

    /**
     * Monta o terminal.
     *
     * @param receptor Objeto que recebe os comandos digitados.
     */
    public PainelTerminal(IReceptorComando receptor) {
        this.receptor = receptor;

        saida = new JTextArea(LINHAS, 0);
        saida.setEditable(false);
        saida.setFocusable(false);

        entrada = new JTextField();
        entrada.addActionListener(this);

        JPanel linhaComando = new JPanel();
        linhaComando.setLayout(new BorderLayout());
        linhaComando.add(new JLabel("> "), BorderLayout.WEST);
        linhaComando.add(entrada, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(new JScrollPane(saida), BorderLayout.CENTER);
        add(linhaComando, BorderLayout.SOUTH);
    }

    /**
     * Chamado quando o jogador pressiona Enter. Limpa o campo e envia a linha, se
     * ela não estiver vazia.
     *
     * @param e Evento gerado pelo campo de texto.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String linha = entrada.getText();
        entrada.setText("");

        if (!linha.isEmpty()) {
            escrever("> " + linha);
            receptor.receberComando(linha);
        }
    }

    /**
     * Acrescenta uma linha às mensagens e rola a área até o final.
     *
     * @param texto Texto a ser mostrado.
     */
    public void escrever(String texto) {
        saida.append(texto + "\n");
        saida.setCaretPosition(saida.getDocument().getLength());
    }

    /**
     * Desativa o campo de comandos.
     */
    public void bloquearEntrada() {
        entrada.setEnabled(false);
    }
}