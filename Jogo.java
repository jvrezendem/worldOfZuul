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

public class Jogo {
    private Analisador analisador;
    private Ambiente ambienteAtual;
    private Casa casa;
    private Simulacao simulacao;
    private JogadorLogico jogador;
    private int contadorPortas;

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
        simulacao = new Simulacao(mapa, ambienteAtual.getPosicaoReferencia());
        jogador = new JogadorLogico();
        contadorPortas = GeradorAleatorios.contadorPorta();
    }

    /**
     * Rotina principal do jogo. Fica em loop ate terminar o jogo.
     */
    public void jogar() {
        imprimirBoasVindas();
        imprimirLocAtual();

        // Entra no loop de comando principal. Aqui nos repetidamente lemos
        // comandos e os executamos ate o jogo terminar.

        boolean terminado = false;
        while (!terminado) {
            Comando comando = analisador.pegarComando();
            terminado = processarComando(comando);
        }
        System.out.println("Obrigado por jogar. Ate mais!");
    }

    private void imprimirLocAtual() {

        System.out.println("Voce esta em " + ambienteAtual.getNome());
        System.out.println(ambienteAtual.getDescricao());

        System.out.print("Saidas: ");
        System.out.println(ambienteAtual.getSaidas());
        System.out.println();

        imprimirItensAmbienteAtual();

        if (jogador.temChave()) {
            System.out.println("Durabilidade da chave:" + jogador.getChaveMestra().getDurabilidade());
        }

        System.out.println("Nº de tentativas restantes: " + contadorPortas);
        System.out.println();
    }

    /**
     * Imprime a mensagem de abertura para o jogador.
     */
    private void imprimirBoasVindas() {
        System.out.println();
        System.out.println("Bem-vindo ao World of Zuul!");
        System.out.println("World of Zuul eh um novo jogo de aventura, incrivelmente chato.");
        System.out.println("Digite 'ajuda' se voce precisar de ajuda.");
        System.out.println();

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
            System.out.println("Eu nao entendi o que voce disse...");
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
        System.out.println("Voce esta perdido. Voce esta sozinho. Voce caminha");
        System.out.println("pela casa assombrada.");
        System.out.println();
        System.out.println("Suas palavras de comando sao:");
        System.out.println(analisador.getComandos());
    }

    /**
     * Tenta ir em uma direcao. Se existe uma saida entra no
     * novo ambiente, caso contrario imprime mensagem de erro.
     */
    private boolean irParaAmbiente(Comando comando) {
        if (!comando.temSegundaPalavra()) {
            // se nao ha segunda palavra, nao sabemos pra onde ir...
            System.out.println("Ir pra onde?");
            return false;
        }

        String nomeAmbiente = comando.getSegundaPalavra();

        // Tenta sair do ambiente atual
        Ambiente proximoAmbiente = ambienteAtual.getAmbienteSaida(nomeAmbiente);

        if (proximoAmbiente == null) {
            System.out.println("Nao ha passagem!");
            return false;
        }

        if (jogador.temChave() && jogador.getChaveMestra().getDurabilidade() > 0) {
            jogador.usarChave();

            System.out.println("Durabilidade da chave diminuiu!");

            simulacao.executarSimulacao(ambienteAtual.getPosicaoReferencia(), proximoAmbiente.getPosicaoReferencia());
            ambienteAtual = proximoAmbiente;
            imprimirLocAtual();
        } else {

            contadorPortas--;

            if (ambienteAtual.tentarSair(nomeAmbiente)) {
                simulacao.executarSimulacao(ambienteAtual.getPosicaoReferencia(),
                        proximoAmbiente.getPosicaoReferencia());
                ambienteAtual = proximoAmbiente;
                imprimirLocAtual();

            } else {
                System.out.println("A porta esta emperrada.");
            }

        }

        if (contadorPortas <= 0) {
            perdeuJogo("VOCE NAO PODE MAIS ABRIR PORTAS E PERDEU O JOGO. TENTE NOVAMENTE");
            return true;
        }

        return false;
    }

    private boolean usarCargaExplosiva() {
        try {
            jogador.usarCargaExplosiva();
            System.out.println("!BOOOOM!");

            if (ambienteAtual.getTemTesouro()) {
                ganhouJogo();
            } else {
                perdeuJogo("VOCE EXPLODIU O LUGAR ERRRADO E PERDEU O JOGO. TENTE NOVAMENTE");
            }

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private void ganhouJogo() {
        System.out.println("====================================================");
        System.out.println("PARABENS, VOCE ACHOU O TESOURO! OBRIGADO POR JOGAR");
        System.out.println("====================================================");
    }

    private void perdeuJogo(String motivo) {
        System.out.println("===============================================================");
        System.out.println(motivo);
        System.out.println("===============================================================");
    }

    private void receberItensAmbiente() {
        List<Item> itemsRecbidos = ambienteAtual.pegarItensAmbiente();
        for (Item item : itemsRecbidos) {
            jogador.pegouItem(item);
            System.out.println("Pegou item: " + item.getDescricao());
        }
    }

    private void imprimirItensMochila() {
        System.out.println(jogador.getItensMochilaDescricao());
    }

    /**
     * "Sair" foi digitado. Verifica o resto do comando pra ver
     * se nos queremos realmente sair do jogo.
     * 
     * @return true, se este comando sai do jogo, false, caso contrario
     */
    private boolean sair(Comando comando) {
        if (comando.temSegundaPalavra()) {
            System.out.println("Sair o que?");
            return false;
        } else {
            return true; // sinaliza que nos queremos sair
        }
    }

    private void observar(Comando comando) {
        if (comando.temSegundaPalavra()) {
            System.out.println("Observar o que?");
        } else {
            imprimirLocAtual();
        }
    }

    private void imprimirItensAmbienteAtual() {
        System.out.print("Itens: ");
        for (Item i : ambienteAtual.getItens()) {
            System.out.print(i.getDescricao() + " ");
        }

        System.out.println();
    }
}
