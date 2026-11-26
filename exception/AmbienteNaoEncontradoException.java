package exception;

public class AmbienteNaoEncontradoException extends ExcecaoJogo {
    public AmbienteNaoEncontradoException(String nome) {
        super("Ambiente não encontrado: " + nome);
    }
}
