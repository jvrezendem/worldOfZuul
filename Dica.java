public class Dica implements Item {

    private String conteudo;

    public Dica(Ambiente ambiente) {

        if (ambiente == null) {
            throw new IllegalArgumentException("O ambiente nao pode ser nulo");
        }

        conteudo = gerarConteudo(ambiente);
    }

    public String getConteudo() {
        return conteudo;
    }

    @Override
    public String getDescricao() {
        return "Dica: " + conteudo;
    }

    @Override
    public void exibirNaTela(JanelaSimulacao janela) {
        janela.adicionarDica(conteudo);
    }

    private String gerarConteudo(Ambiente ambiente) {
        return "O tesouro não está no(a) " + ambiente.getNome();
    }

}
