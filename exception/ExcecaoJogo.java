package exception;

/**
 * Classe base das exceções do jogo.
 *
 * @author Paulo Sergio Mendes Taciano
 * @author João Vitor Rezende Marciano
 */
public class ExcecaoJogo extends RuntimeException {
    /**
     * Cria uma exceção do jogo.
     *
     * @param mensagem Descrição do problema.
     */
    public ExcecaoJogo(String mensagem) {
        super(mensagem);
    }
}
