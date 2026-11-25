

public class Porta {

    private String status;
    private Ambiente destino;

    public Porta(Ambiente destino){
        status = gerarStatus();
        this.destino = destino;
    }

    public Ambiente getDestino() {
        return destino;
    }

    public String getStatus() {
        return status;
    }

    private String gerarStatus(){
        boolean statusBool = GeradorAleatorios.statusPorta();
        if(statusBool) return "funcionando corretamente";
        return "emperrada";
    }

    public boolean podePassarPorta(){
        return getStatus().equals("funcionando corretamente");
    }
    
}
