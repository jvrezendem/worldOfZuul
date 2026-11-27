package exception;

/**
 * Indica uma tentativa de explodir sem ter a carga explosiva.
 *
 * @author Paulo Sergio Mendes Taciano
 * @author João Vitor Rezende Marciano
 */
public class SemCargaExplosivaException extends ExcecaoJogo {
    /**
     * Cria a exceção.
     */
    public SemCargaExplosivaException() {
        super("Sem carga explosiva!");
    }
}
