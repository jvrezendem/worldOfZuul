import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Timer;

/**
 * Responsavel pela simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Simulacao implements ActionListener {
    private static final int INTERVALO = 100;

    private Jogador jogador;
    private JanelaSimulacao janelaSimulacao;
    private Mapa mapa;
    private Timer temporizador;
    private List<Posicao> caminho;
    private int passoAtual;

    /**
     * Coloca o jogador no mapa e prepara o temporizador da animação.
     *
     * @param mapa Mapa onde o jogador se desloca.
     * @param janelaSimulacao Janela onde o mapa é desenhado.
     * @param inicioJogador Posição inicial do jogador.
     */
    public Simulacao(Mapa mapa, JanelaSimulacao janelaSimulacao, Posicao inicioJogador){
        this.mapa = mapa;
        this.janelaSimulacao = janelaSimulacao;
        jogador = new Jogador(inicioJogador);//Cria jogador
        temporizador = new Timer(INTERVALO, this);

        if (inicioJogador != null) {
            mapa.adicionarItem(jogador);
        }
        janelaSimulacao.executarAcao(); // desenha o mapa inicial
    }

    /**
     * Inicia o deslocamento do jogador entre duas posições. O método retorna logo e
     * a animação continua um passo de cada vez.
     *
     * @param origem Posição inicial.
     * @param destino Posição final.
     */
    public void executarSimulacao(Posicao origem, Posicao destino){
        temporizador.stop();

        if (jogador.getPosicaoOrigem() != null) {
            mapa.removerItem(jogador);
        }

        jogador.setPosicaoOrigem(origem);//Define a posicao origem do jogador
        jogador.setPosicaoDestino(destino);//Define a posicao destino do jogador
        mapa.adicionarItem(jogador);//Inicializando o mapa com o jogador
        janelaSimulacao.executarAcao();
        caminho = mapa.encontrarCaminho(jogador.getPosicaoOrigem(), jogador.getPosicaoDestino());
        passoAtual = 0;

        if (!caminho.isEmpty()) {
            temporizador.start();
        } else {
            janelaSimulacao.escrever("Não foi possível encontrar um caminho para o destino.");
        }
    }

    /**
     * Chamado pelo temporizador. Executa o próximo passo e para ao chegar ao
     * destino.
     *
     * @param e Evento gerado pelo temporizador.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        executarUmPasso(caminho.get(passoAtual));
        passoAtual++;

        if (passoAtual == caminho.size()) {
            temporizador.stop();
        }
    }

    /**
     * Move o jogador para uma posição e redesenha o mapa.
     *
     * @param proximaLocalizacao Nova posição do jogador.
     */
    private void executarUmPasso(Posicao proximaLocalizacao) {
        mapa.removerItem(jogador);
        jogador.executarAcao(proximaLocalizacao);
        mapa.adicionarItem(jogador);
        janelaSimulacao.executarAcao();
    }

}