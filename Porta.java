
public class Porta {

    private String status;
    private Ambiente destino;

    public Porta(Ambiente destino) {

        if (destino == null) {
            throw new IllegalArgumentException("O ambiente de destino da porta nao pode ser nulo");
        }

        status = gerarStatus();
        this.destino = destino;
    }

    public Ambiente getDestino() {
        return destino;
    }

    public String getStatus() {
        return status;
    }

    private String gerarStatus() {
        boolean statusBool = GeradorAleatorios.statusPorta();
        if (statusBool)
            return "funcionando corretamente";
        return "emperrada";
    }

    private boolean podePassarPorta() {
        return getStatus().equals("funcionando corretamente");
    }

    public void setStatus() {
        this.status = gerarStatus();
    }

    public boolean tentarAbrir() {
        boolean abriu = podePassarPorta();
        setStatus();
        return abriu;
    }

}
