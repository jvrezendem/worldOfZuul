
/**
 * Porta que leva a um ambiente. Ela pode estar funcionando corretamente ou
 * emperrada, e esse estado é sorteado.
 *
 * @author Paulo Sergio Mendes Taciano
 * @author João Vitor Rezende Marciano
 */
public class Porta {

    private String status;
    private Ambiente destino;

    /**
     * Cria uma porta com estado sorteado.
     *
     * @param destino Ambiente para onde a porta leva.
     * @throws IllegalArgumentException Se o destino for nulo.
     */
    public Porta(Ambiente destino) {

        if (destino == null) {
            throw new IllegalArgumentException("O ambiente de destino da porta nao pode ser nulo");
        }

        status = gerarStatus();
        this.destino = destino;
    }

    /**
     * Informa para onde a porta leva.
     *
     * @return O ambiente de destino.
     */
    public Ambiente getDestino() {
        return destino;
    }

    /**
     * Informa o estado da porta.
     *
     * @return O texto que descreve o estado atual.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sorteia um estado para a porta.
     *
     * @return O texto do estado sorteado.
     */
    private String gerarStatus() {
        boolean statusBool = GeradorAleatorios.statusPorta();
        if (statusBool)
            return "funcionando corretamente";
        return "emperrada";
    }

    /**
     * Informa se a porta permite a passagem.
     *
     * @return true se estiver funcionando corretamente.
     */
    private boolean podePassarPorta() {
        return getStatus().equals("funcionando corretamente");
    }

    /**
     * Sorteia novamente o estado da porta.
     */
    public void setStatus() {
        this.status = gerarStatus();
    }

    /**
     * Tenta abrir a porta e, em seguida, sorteia um novo estado para ela.
     *
     * @return true se a porta abriu.
     */
    public boolean tentarAbrir() {
        boolean abriu = podePassarPorta();
        setStatus();
        return abriu;
    }

}
