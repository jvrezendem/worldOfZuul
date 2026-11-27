package exception;

/**
 * Indica uma falha na leitura ou na gravação de um arquivo do jogo.
 *
 * @author Paulo Sergio Mendes Taciano
 * @author João Vitor Rezende Marciano
 */
public class ErroArquivoException extends ExcecaoJogo {
    /**
     * Cria a exceção.
     *
     * @param mensagem Descrição do problema.
     */
    public ErroArquivoException(String mensagem){
        super(mensagem);
    }
}
