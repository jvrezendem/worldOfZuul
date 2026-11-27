import java.util.Collections;
import java.util.List;

import exception.AmbienteNaoEncontradoException;

public class Casa {

    private List<Ambiente> ambientes;

    public Casa(List<Ambiente> ambientes) {
        this.ambientes = ambientes;
        definirLocalItens();
    }

    public List<Ambiente> getAmbientes(){
        return Collections.unmodifiableList(ambientes);
    }

    private void definirLocalItens() {
        if (!existeTesouro()) {
            definirLocalTesouro();
        }

        criarDica();
        criarDica();

        definirLocalChave();
    }

    private void definirLocalTesouro() {
        int indiceAmbTesouro = GeradorAleatorios.indiceAleatorio(ambientes.size());
        ambientes.get(indiceAmbTesouro).adcionarItem(new Tesouro());
    }

    private void criarDica() {
        existeTesouro();
        int indiceAmbDica = GeradorAleatorios.indiceAleatorio(ambientes.size());
        int indiceConteudoDica = indiceAmbSemTesouro();

        ambientes.get(indiceAmbDica).adcionarItem(new Dica(ambientes.get(indiceConteudoDica)));
    }

    private void definirLocalChave() {
        existeTesouro();
        int indiceSemTesouro = indiceAmbSemTesouro();

        ambientes.get(indiceSemTesouro).adcionarItem(new ChaveMestra());

    }

    private boolean existeTesouro() {
        for (Ambiente amb : ambientes) {
            if (amb.getTemTesouro())
                return true;
        }

        return false;
    }

    private int indiceAmbSemTesouro() {
        int indiceSemTesouro;

        do {
            indiceSemTesouro = GeradorAleatorios.indiceAleatorio(ambientes.size());
        } while (ambientes.get(indiceSemTesouro).getTemTesouro());

        return indiceSemTesouro;
    }

    public void redefinirPortas() {
        for (Ambiente ambiente : ambientes) {
            ambiente.redefinirPortas();
        }
    }

    public Ambiente getAmbienteByNome(String nome) {
        for (Ambiente ambiente : ambientes) {
            if (ambiente.getNome().equals(nome)) {
                return ambiente;
            }
        }

        throw new AmbienteNaoEncontradoException(nome);
    }

}
