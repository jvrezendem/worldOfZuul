import java.util.ArrayList;
import java.util.List;

/**
 * Essa eh a classe principal da aplicacao "World of Zull".
 * "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.
 * Usuarios podem caminhar em um cenario. E eh tudo! Ele realmente
 * precisa ser estendido para fazer algo interessante!
 * 
 * Para jogar esse jogo, crie uma instancia dessa classe e chame o metodo
 * "jogar".
 * 
 * Essa classe principal cria e inicializa todas as outras: ela cria os
 * ambientes, cria o analisador e comeca o jogo. Ela tambeme avalia e
 * executa os comandos que o analisador retorna.
 * 
 * @author Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
 */

public class Jogo implements IReceptorComando {
    private Analisador analisador;
    private Ambiente ambienteAtual;
    private Casa casa;
    private Simulacao simulacao;
    private JogadorLogico jogador;
    private int contadorPortas;
    private JanelaSimulacao janela;
    private ArrayList<Ambiente> ambientesVisitados;

    /**
     * Cria o jogo e incializa seu mapa interno.
     */
    public Jogo() {
        LeitorArquivoJogo leitor = new LeitorArquivoJogo("mapa.txt");
        casa = new Casa(leitor.getAmbientes());
        new Gabarito("gabarito.txt").gravar(casa.getAmbientes());
        analisador = new Analisador();
        ambienteAtual = casa.getAmbienteByNome("Sala de TV");
        Mapa mapa = new Mapa(leitor.getLargura(), leitor.getAltura(), leitor.getItensMapa());
        jogador = new JogadorLogico();
        contadorPortas = GeradorAleatorios.contadorPorta();
        ambientesVisitados = new ArrayList<>();
        janela = new JanelaSimulacao(mapa, this);
        simulacao = new Simulacao(mapa, janela, ambienteAtual.getPosicaoReferencia());
    }

    /**
     * Mostra a situacao inicial do jogo. Os comandos chegam pela interface.
     */
    public void jogar() {
        janela.atualizarTentativas(contadorPortas);
        imprimirBoasVindas();
        imprimirLocAtual();
    }

    @Override
    public void receberComando(String linha) {
        Comando comando = analisador.interpretar(linha);

        if (processarComando(comando)) {
            escrever("Obrigado por jogar. Ate mais!");
            janela.bloquearEntrada();
        }
    }

    private void escrever(String texto) {
        janela.escrever(texto);
    }

    private void exibirDescobertas() {
        if (!ambientesVisitados.contains(ambienteAtual)) {
            ambientesVisitados.add(ambienteAtual);

            for (Item item : ambienteAtual.getItens()) {
                item.exibirNaTela(janela);
            }
        }
    }

    private void imprimirLocAtual() {
        exibirDescobertas();

        escrever("Voce esta em " + ambienteAtual.getNome());
        escrever(ambienteAtual.getDescricao());

        escrever("Saidas: " + ambienteAtual.getSaidas());
        escrever("");

        imprimirItensAmbienteAtual();

        if (jogador.temChave()) {
            escrever("Durabilidade da chave:" + jogador.getChaveMestra().getDurabilidade());
        }

        escrever("Nº de tentativas restantes: " + contadorPortas);
        escrever("");
    }

    /**
     * Imprime a mensagem de abertura para o jogador.
     */
    private void imprimirBoasVindas() {
        escrever("");
        escrever("Bem-vindo ao World of Zuul!");
        escrever("World of Zuul eh um novo jogo de aventura, incrivelmente chato.");
        escrever("Digite 'ajuda' se voce precisar de ajuda.");
        escrever("");

    }

    /**
     * Dado um comando, processa-o (ou seja, executa-o)
     * 
     * @param comando O Comando a ser processado.
     * @return true se o comando finaliza o jogo.
     */
    private boolean processarComando(Comando comando) {
        boolean querSair = false;

        if (comando.ehDesconhecido()) {
            escrever("Eu nao entendi o que voce disse...");
            return false;
        }

        String palavraDeComando = comando.getPalavraDeComando();
        if (palavraDeComando.equals("ajuda")) {
            imprimirAjuda();
        } else if (palavraDeComando.equals("ir")) {
            querSair = irParaAmbiente(comando);
        } else if (palavraDeComando.equals("sair")) {
            querSair = sair(comando);
        } else if (palavraDeComando.equals("observar")) {
            observar(comando);
        } else if (palavraDeComando.equals("pegar")) {
            receberItensAmbiente();
        } else if (palavraDeComando.equals("mochila")) {
            imprimirItensMochila();
        } else if (palavraDeComando.equals("explodir")) {
            querSair = usarCargaExplosiva();
        }

        return querSair;
    }

    // Implementacoes dos comandos do usuario

    /**
     * Printe informacoes de ajuda.
     * Aqui nos imprimimos algo bobo e enigmatico e a lista de
     * palavras de comando
     */
    private void imprimirAjuda() {
        escrever("Voce esta perdido. Voce esta sozinho. Voce caminha");
        escrever("pela casa assombrada.");
        escrever("");
        escrever("Suas palavras de comando sao:");
        escrever(analisador.getComandos());
    }

    /**
     * Tenta ir em uma direcao. Se existe uma saida entra no
     * novo ambiente, caso contrario imprime mensagem de erro.
     */
    private boolean irParaAmbiente(Comando comando) {
        if (!comando.temSegundaPalavra()) {
            // se nao ha segunda palavra, nao sabemos pra onde ir...
            escrever("Ir pra onde?");
            return false;
        }

        String nomeAmbiente = comando.getSegundaPalavra();

        // Tenta sair do ambiente atual
        Ambiente proximoAmbiente = ambienteAtual.getAmbienteSaida(nomeAmbiente);

        if (proximoAmbiente == null) {
            escrever("Nao ha passagem!");
            return false;
        }

        if (jogador.temChave() && jogador.getChaveMestra().getDurabilidade() > 0) {
            jogador.usarChave();
            janela.atualizarDurabilidade(jogador.getChaveMestra().getDurabilidade());

            escrever("Durabilidade da chave diminuiu!");

            simulacao.executarSimulacao(ambienteAtual.getPosicaoReferencia(), proximoAmbiente.getPosicaoReferencia());
            ambienteAtual = proximoAmbiente;
            imprimirLocAtual();
        } else {

            contadorPortas--;
            janela.atualizarTentativas(contadorPortas);

            if (ambienteAtual.tentarSair(nomeAmbiente)) {
                simulacao.executarSimulacao(ambienteAtual.getPosicaoReferencia(),
                        proximoAmbiente.getPosicaoReferencia());
                ambienteAtual = proximoAmbiente;
                imprimirLocAtual();

            } else {
                escrever("A porta esta emperrada.");
            }

        }

        casa.redefinirPortas();

        if (contadorPortas <= 0) {
            perdeuJogo("VOCE NAO PODE MAIS ABRIR PORTAS E PERDEU O JOGO. TENTE NOVAMENTE");
            return true;
        }

        return false;
    }

    private boolean usarCargaExplosiva() {
        try {
            jogador.usarCargaExplosiva();
            escrever("!BOOOOM!");

            if (ambienteAtual.getTemTesouro()) {
                ganhouJogo();
            } else {
                perdeuJogo("VOCE EXPLODIU O LUGAR ERRRADO E PERDEU O JOGO. TENTE NOVAMENTE");
            }

            return true;

        } catch (Exception e) {
            escrever(e.getMessage());
            return false;
        }
    }

    private void ganhouJogo() {
        escrever("====================================================");
        escrever("PARABENS, VOCE ACHOU O TESOURO! OBRIGADO POR JOGAR");
        escrever("====================================================");
    }

    private void perdeuJogo(String motivo) {
        escrever("===============================================================");
        escrever(motivo);
        escrever("===============================================================");
    }

    private void receberItensAmbiente() {
        List<Item> itemsRecbidos = ambienteAtual.pegarItensAmbiente();
        for (Item item : itemsRecbidos) {
            jogador.pegouItem(item);
            escrever("Pegou item: " + item.getDescricao());
        }
    }

    private void imprimirItensMochila() {
        escrever(jogador.getItensMochilaDescricao());
    }

    /**
     * "Sair" foi digitado. Verifica o resto do comando pra ver
     * se nos queremos realmente sair do jogo.
     * 
     * @return true, se este comando sai do jogo, false, caso contrario
     */
    private boolean sair(Comando comando) {
        if (comando.temSegundaPalavra()) {
            escrever("Sair o que?");
            return false;
        } else {
            return true; // sinaliza que nos queremos sair
        }
    }

    private void observar(Comando comando) {
        if (comando.temSegundaPalavra()) {
            escrever("Observar o que?");
        } else {
            imprimirLocAtual();
        }
    }

    private void imprimirItensAmbienteAtual() {
        String itens = "Itens: ";
        for (Item i : ambienteAtual.getItens()) {
            if (!(i instanceof Tesouro)) {
                itens = itens + i.getDescricao() + " ";
            }
        }

        escrever(itens);
    }
}