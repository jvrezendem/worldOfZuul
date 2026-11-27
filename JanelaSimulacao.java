import java.awt.*;
import javax.swing.*;

/**
 * Fornece a visualizacao da simulacao
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class JanelaSimulacao extends JFrame{
    private Mapa mapa;
    private VisaoMapa visaoMapa;
    private CarregadorImagem carregadorImagem;
    private PainelStatus painelStatus;          
    private PainelDicas painelDicas;            
    private PainelTerminal painelTerminal;
    
    /**
     * Monta a janela do jogo com o status à esquerda, o mapa no centro, as dicas à
     * direita e o terminal embaixo.
     *
     * @param mapa Mapa a ser desenhado.
     * @param receptor Objeto que recebe os comandos digitados.
     */
    public JanelaSimulacao(Mapa mapa, IReceptorComando receptor){
        this.mapa = mapa;
        visaoMapa = new VisaoMapa(mapa.getLargura(),mapa.getAltura());
        painelStatus = new PainelStatus();
        painelDicas = new PainelDicas();
        painelTerminal = new PainelTerminal(receptor);
        carregadorImagem = new CarregadorImagem();

        getContentPane().add(painelStatus, BorderLayout.WEST);
        getContentPane().add(visaoMapa, BorderLayout.CENTER);
        getContentPane().add(painelDicas, BorderLayout.EAST);
        getContentPane().add(painelTerminal, BorderLayout.SOUTH);
        setTitle("World of Zuul");
        setSize(1000,750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    /**
     * Escreve uma linha no terminal.
     *
     * @param texto Texto a ser mostrado.
     */
    public void escrever(String texto){
        painelTerminal.escrever(texto);
    }

    /**
     * Impede a digitação de novos comandos.
     */
    public void bloquearEntrada(){
        painelTerminal.bloquearEntrada();
    }

    /**
     * Atualiza o número de tentativas restantes.
     *
     * @param tentativas Tentativas restantes.
     */
    public void atualizarTentativas(int tentativas){
        painelStatus.atualizarTentativas(tentativas);
    }

    /**
     * Atualiza a durabilidade da chave mestra.
     *
     * @param durabilidade Durabilidade atual.
     */
    public void atualizarDurabilidade(int durabilidade){
        painelStatus.atualizarDurabilidade(durabilidade);
    }

    /**
     * Mostra uma dica no painel de dicas.
     *
     * @param dica Texto da dica.
     */
    public void adicionarDica(String dica){
        painelDicas.adicionarDica(dica);
    }
    
    /**
     * Mostra o estado atual do mapa.
     */
    public void executarAcao(){
        visaoMapa.preparePaint();
        for(int i = 0; i < mapa.getLargura(); i++){
            for(int j = 0; j < mapa.getAltura(); j++){
                for(ItemMapa item: mapa.getItensNaPosicao(i, j)){
                    visaoMapa.desenharImagem(i, j, carregadorImagem.carregarImagem(item));
                }
            }
        }
        visaoMapa.repaint();
    }

    /**
     * Fornece uma visualizacao grafica do mapa. Esta eh 
     * uma classe interna que define os componentes da GUI.
     * Ela contém alguns detalhes mais avancados sobre GUI 
     * que voce pode ignorar para realizacao do seu trabalho.
     */    
    private class VisaoMapa extends JPanel{

        private final int VIEW_SCALING_FACTOR = 6;
        private int larguraMapa, alturaMapa;
        private int xScale, yScale;
        private Dimension tamanho;
        private Graphics g;
        private Image imagemMapa;

        /**
         * Cria um novo componente VisaoMapa.
         */
        public VisaoMapa(int largura, int altura)
        {
            larguraMapa = largura;
            alturaMapa = altura;
            setBackground(Color.white);
            tamanho = new Dimension(0, 0);
        }

        /**
         * Informa para o gerenciador GUI o tamanho.
         */
        public Dimension getPreferredSize()
        {
            return new Dimension(larguraMapa * VIEW_SCALING_FACTOR,
                                 alturaMapa * VIEW_SCALING_FACTOR);
        }
        
        /**
         * Prepara para um novo ciclo de exibicao. Uma vez que o componente
         * pode ser redimensionado, calcula o "fator de escala" novamente.
         */
        public void preparePaint()
        {
            if(!tamanho.equals(getSize())) {  // se o tamanho mudou...
                tamanho = getSize();
                imagemMapa = visaoMapa.createImage(tamanho.width, tamanho.height);
                g = imagemMapa.getGraphics();

                xScale = tamanho.width / larguraMapa;
                if(xScale < 1) {
                    xScale = VIEW_SCALING_FACTOR;
                }
                yScale = tamanho.height / alturaMapa;
                if(yScale < 1) {
                    yScale = VIEW_SCALING_FACTOR;
                }
            }
            g.setColor(Color.white);
            g.fillRect(0, 0, tamanho.width, tamanho.height);
            g.setColor(Color.gray);
            for(int i = 0, x = 0; x < tamanho.width; i++, x = i * xScale) {
                g.drawLine(x, 0, x, tamanho.height - 1);
            }
            for(int i = 0, y = 0; y < tamanho.height; i++, y = i * yScale) {
                g.drawLine(0, y, tamanho.width - 1, y);
            }
        }
        
        /**
         * Desenha a imagem para um determinado item.
         */
        public void desenharImagem(int x, int y, Image image)
        {
            g.drawImage(image, x * xScale + 1, y * yScale + 1,
                        xScale - 1, yScale - 1, this);
        }

        /**
         * O componente VisaoMapa precisa ser reexibido. Copia a
         * imagem interna para a tela.
         */
        public void paintComponent(Graphics g)
        {
            if(imagemMapa != null) {
                g.drawImage(imagemMapa, 0, 0, null);
            }
        }
    }
    
}
