package exception;

/**
 * Indica que um arquivo de imagem do jogo não foi encontrado.
 *
 * @author Paulo Sergio Mendes Taciano
 * @author João Vitor Rezende Marciano
 */
public class ImagemNaoEncontradaException extends RuntimeException {

    /**
     * Cria a exceção.
     *
     * @param caminho Caminho da imagem procurada.
     */
    public ImagemNaoEncontradaException(String caminho) {
        super("Imagem não encontrada em " + caminho);
    }
}
