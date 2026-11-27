package exception;

/**
 * Indica que foi procurado um ambiente que não existe.
 *
 * @author Paulo Sergio Mendes Taciano
 * @author João Vitor Rezende Marciano
 */
public class AmbienteNaoEncontradoException extends ExcecaoJogo {
    /**
     * Cria a exceção.
     *
     * @param nome Nome do ambiente procurado.
     */
    public AmbienteNaoEncontradoException(String nome) {
        super("Ambiente não encontrado: " + nome);
    }
}
