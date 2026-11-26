package exception;

public class ImagemNaoEncontradaException extends RuntimeException {

    public ImagemNaoEncontradaException(String caminho) {
        super("Imagem não encontrada em " + caminho);
    }
}
