/**
 * Item que revela um ambiente onde o tesouro não está.
 *
 * @author Paulo Sergio Mendes Taciano
 * @author João Vitor Rezende Marciano
 */
public class Dica implements Item {

    private String conteudo;

    /**
     * Cria uma dica sobre um ambiente.
     *
     * @param ambiente Ambiente que não contém o tesouro.
     * @throws IllegalArgumentException Se o ambiente for nulo.
     */
    public Dica(Ambiente ambiente) {

        if (ambiente == null) {
            throw new IllegalArgumentException("O ambiente nao pode ser nulo");
        }

        conteudo = gerarConteudo(ambiente);
    }

    /**
     * Informa o texto da dica.
     *
     * @return O texto da dica.
     */
    public String getConteudo() {
        return conteudo;
    }

    /**
     * Descreve a dica.
     *
     * @return O texto da dica precedido da palavra Dica.
     */
    @Override
    public String getDescricao() {
        return "Dica: " + conteudo;
    }

    /**
     * Acrescenta o texto da dica ao painel de dicas.
     *
     * @param janela Janela onde a dica é exibida.
     */
    @Override
    public void exibirNaTela(JanelaSimulacao janela) {
        janela.adicionarDica(conteudo);
    }

    /**
     * Monta a frase da dica.
     *
     * @param ambiente Ambiente citado na dica.
     * @return A frase mostrada ao jogador.
     */
    private String gerarConteudo(Ambiente ambiente) {
        return "O tesouro não está no(a) " + ambiente.getNome();
    }

}
