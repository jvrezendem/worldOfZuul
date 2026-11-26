public class ChaveMestra implements Item {

    private int durabilidade;

    public ChaveMestra() {
        durabilidade = GeradorAleatorios.durabilidadeChave();
    }

    @Override
    public String getDescricao() {
        return "Chave Mestra";
    }

    public int getDurabilidade() {
        return durabilidade;
    }

    public void usarChave() {
        durabilidade--;
    }

}
