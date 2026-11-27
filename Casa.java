import java.util.Collections;
import java.util.List;

import exception.AmbienteNaoEncontradoException;

/**
 * A casa do jogo, composta pelos seus ambientes. Distribui os itens entre eles
 * no início da partida.
 *
 * @author Paulo Sergio Mendes Taciano
 * @author João Vitor Rezende Marciano
 */
public class Casa {

    /** Quantidade de dicas distribuídas pela casa. */
    private final int NUMERO_DICAS = 2;

    private List<Ambiente> ambientes;

    /**
     * Cria a casa e sorteia onde ficam o tesouro, as dicas e a chave mestra.
     *
     * @param ambientes Ambientes que formam a casa.
     */
    public Casa(List<Ambiente> ambientes) {
        this.ambientes = ambientes;
        definirLocalItens();
    }

    /**
     * Informa os ambientes da casa.
     *
     * @return Uma lista que não pode ser alterada.
     */
    public List<Ambiente> getAmbientes() {
        return Collections.unmodifiableList(ambientes);
    }

    /**
     * Distribui os itens, colocando o tesouro antes dos demais.
     */
    private void definirLocalItens() {
        if (!existeTesouro()) {
            definirLocalTesouro();
        }

        for (int i = 0; i < NUMERO_DICAS; i++)
            criarDica();

        definirLocalChave();
    }

    /**
     * Coloca o tesouro em um ambiente sorteado.
     */
    private void definirLocalTesouro() {
        int indiceAmbTesouro = GeradorAleatorios.indiceAleatorio(ambientes.size());
        ambientes.get(indiceAmbTesouro).adcionarItem(new Tesouro());
    }

    /**
     * Coloca em um ambiente sorteado uma dica sobre um ambiente sem o tesouro.
     */
    private void criarDica() {
        existeTesouro();
        int indiceAmbDica = GeradorAleatorios.indiceAleatorio(ambientes.size());
        int indiceConteudoDica = indiceAmbSemTesouro();

        ambientes.get(indiceAmbDica).adcionarItem(new Dica(ambientes.get(indiceConteudoDica)));
    }

    /**
     * Coloca a chave mestra em um ambiente sorteado sem o tesouro.
     */
    private void definirLocalChave() {
        existeTesouro();
        int indiceSemTesouro = indiceAmbSemTesouro();

        ambientes.get(indiceSemTesouro).adcionarItem(new ChaveMestra());

    }

    /**
     * Informa se o tesouro já foi colocado em algum ambiente.
     *
     * @return true se algum ambiente tem o tesouro.
     */
    private boolean existeTesouro() {
        for (Ambiente amb : ambientes) {
            if (amb.getTemTesouro())
                return true;
        }

        return false;
    }

    /**
     * Sorteia um ambiente que não tenha o tesouro.
     *
     * @return A posição do ambiente sorteado na lista.
     */
    private int indiceAmbSemTesouro() {
        int indiceSemTesouro;

        do {
            indiceSemTesouro = GeradorAleatorios.indiceAleatorio(ambientes.size());
        } while (ambientes.get(indiceSemTesouro).getTemTesouro());

        return indiceSemTesouro;
    }

    /**
     * Sorteia novamente o estado de todas as portas da casa.
     */
    public void redefinirPortas() {
        for (Ambiente ambiente : ambientes) {
            ambiente.redefinirPortas();
        }
    }

    /**
     * Procura um ambiente pelo nome.
     *
     * @param nome Nome do ambiente.
     * @return O ambiente encontrado.
     * @throws exception.AmbienteNaoEncontradoException Se não houver ambiente com
     *                                                  esse nome.
     */
    public Ambiente getAmbienteByNome(String nome) {
        for (Ambiente ambiente : ambientes) {
            if (ambiente.getNome().equals(nome)) {
                return ambiente;
            }
        }

        throw new AmbienteNaoEncontradoException(nome);
    }

}
