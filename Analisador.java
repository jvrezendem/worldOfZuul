import java.util.Scanner;

/**
 * Esta classe eh parte da aplicacao "World of Zuul".
 * "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.
 * 
 * Esse analisador recebe uma linha digitada na interface e tenta
 * interpreta-la como um comando "Adventure", formado por uma palavra de
 * comando seguida de um complemento. Ele retorna o comando como um objeto
 * da classe Comando.
 *
 * O analisador tem um conjunto de palavras de comando conhecidas. Ele compara
 * a entrada do usuario com os comandos conhecidos, e se a entrada nao eh um
 * dos comandos conhecidos, ele retorna um objeto comando que eh marcado como
 * um comando desconhecido.
 * 
 * @author Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
 */
public class Analisador {
    private PalavrasComando palavrasDeComando; // guarda todas as palavras de comando validas
    
    /**
     * Cria um analisador de comandos.
     */
    public Analisador() {
        palavrasDeComando = new PalavrasComando();
    }

    /**
     * @param linha A linha digitada pelo usuario
     * @return O comando correspondente a linha
     */
    public Comando interpretar(String linha) {
        String palavra1 = null;
        String palavra2 = null;

        // Tenta encontrar ate duas palavras na linha
        Scanner tokenizer = new Scanner(linha);
        if (tokenizer.hasNext()) {
            palavra1 = tokenizer.next(); // pega a primeira palavra
            if (tokenizer.hasNext()) {
                palavra2 = tokenizer.nextLine().trim(); // pega a segunda palavra
            }
        }

        tokenizer.close();

        // Agora verifica se esta palavra eh conhecida. Se for, cria um
        // com ela. Se nao, cria um comando "null" (para comando desconhecido)
        if (palavrasDeComando.ehComando(palavra1)) {
            return new Comando(palavra1, palavra2);
        } else {
            return new Comando(null, palavra2);
        }

    }

    /**
     * Informa os comandos conhecidos.
     *
     * @return As palavras de comando.
     */
    public String getComandos() {
        return palavrasDeComando.getComandos();
    }
}
