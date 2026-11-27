/**
 * Chave que abre qualquer porta, mesmo emperrada, um número limitado de vezes.
 *
 * @author Paulo Sergio Mendes Taciano
 * @author João Vitor Rezende Marciano
 */
public class ChaveMestra implements Item {

    private int durabilidade;

    /**
     * Cria a chave com durabilidade sorteada.
     */
    public ChaveMestra() {
        durabilidade = GeradorAleatorios.durabilidadeChave();
    }

    /**
     * Descreve a chave.
     *
     * @return O nome da chave.
     */
    @Override
    public String getDescricao() {
        return "Chave Mestra";
    }

    /**
     * Mostra a durabilidade da chave na janela.
     *
     * @param janela Janela onde a durabilidade é exibida.
     */
    @Override
    public void exibirNaTela(JanelaSimulacao janela) {
        janela.atualizarDurabilidade(durabilidade);
    }

    /**
     * Informa quantas portas a chave ainda pode abrir.
     *
     * @return A durabilidade atual.
     */
    public int getDurabilidade() {
        return durabilidade;
    }

    /**
     * Diminui em uma unidade a durabilidade da chave.
     */
    public void usarChave() {
        durabilidade--;
    }

}
