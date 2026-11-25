
import java.util.ArrayList;
import java.util.List;

public class Casa {

    List<Ambiente> ambientes;

    public Casa(){
        ambientes = new ArrayList<>();
        criaAmbientes();
        definirLocalItens();
    }

    private void criaAmbientes(){
        Ambiente quarto1 = new Ambiente("Quarto 1", "quarto á esquerda da casa");
        Ambiente quarto2 = new Ambiente("Quarto 2", "quarto á direita da casa");
        Ambiente wc1 = new Ambiente("WC1", "ambiente á esquerda da casa");
        Ambiente wc2 = new Ambiente("WC2", "ambiente á direita da casa");
        Ambiente salaJantar = new Ambiente("Sala de Jantar", "ambiente grande à esquerda da casa");
        Ambiente cozinha = new Ambiente("Cozinha", "cozinha suja");
        Ambiente salaTV = new Ambiente("Sala de TV", "tem uma tv quebrada e um sofá velho");
        Ambiente areaServico = new Ambiente("Área de Serviço", "várias plantas mortas");
        Ambiente corredor = new Ambiente("Corredor", "escuro e sem luz");

        ambientes.add(quarto1);
        ambientes.add(quarto2);
        ambientes.add(wc1);
        ambientes.add(wc2);
        ambientes.add(salaJantar);
        ambientes.add(cozinha);
        ambientes.add(salaTV);
        ambientes.add(areaServico);
        ambientes.add(corredor);

        //define as saidas
        quarto1.ajustarSaidas("Corredor", new Porta(corredor));

        quarto2.ajustarSaidas("Corredor", new Porta(corredor));

        wc1.ajustarSaidas("Corredor", new Porta(corredor));

        wc2.ajustarSaidas("Corredor", new Porta(corredor));

        salaJantar.ajustarSaidas("Corredor", new Porta(corredor));
        salaJantar.ajustarSaidas("Sala de TV", new Porta(salaTV));

        cozinha.ajustarSaidas("Corredor", new Porta(corredor));
        cozinha.ajustarSaidas("Área de Serviço", new Porta(areaServico));

        salaTV.ajustarSaidas("Sala de Jantar", new Porta(salaJantar));

        areaServico.ajustarSaidas("Cozinha", new Porta(cozinha));

        corredor.ajustarSaidas("Quarto 1", new Porta(quarto1));
        corredor.ajustarSaidas("Quarto 2", new Porta(quarto2));
        corredor.ajustarSaidas("WC1", new Porta(wc1));
        corredor.ajustarSaidas("WC2", new Porta(wc2));
        corredor.ajustarSaidas("Sala de Jantar", new Porta(salaJantar));
        corredor.ajustarSaidas("Cozinha", new Porta(cozinha));
    }

    private void definirLocalItens(){
        if(!existeTesouro()){
            definirLocalTesouro();
        }

        criarDica();
        criarDica();

        definirLocalChave();
    }

    private void definirLocalTesouro(){
        int indiceAmbTesouro = GeradorAleatorios.indiceAleatorio(ambientes.size());
        ambientes.get(indiceAmbTesouro).adcionarItem(new Tesouro());
    }

    private void criarDica(){
        existeTesouro();
        int indiceAmbDica = GeradorAleatorios.indiceAleatorio(ambientes.size());
        int indiceConteudoDica = indiceAmbSemTesouro();

        ambientes.get(indiceAmbDica).adcionarItem(new Dica(ambientes.get(indiceConteudoDica)));
    }

    private void definirLocalChave(){
        existeTesouro();
        int indiceSemTesouro = indiceAmbSemTesouro();

        ambientes.get(indiceSemTesouro).adcionarItem(new ChaveMestra());

    }

    private boolean existeTesouro(){
        for(Ambiente amb: ambientes){
            if(amb.getTemTesouro()) return true;
        }

        return false;
    }

    private int indiceAmbSemTesouro(){
        int indiceSemTesouro;
        
        do { 
            indiceSemTesouro = GeradorAleatorios.indiceAleatorio(ambientes.size());
        } while (ambientes.get(indiceSemTesouro).getTemTesouro());
        
        return indiceSemTesouro;
    }

    public Ambiente getAmbienteByNome(String nome){
        for(Ambiente ambiente: ambientes){
            if(ambiente.getNome().equals(nome)){
                return ambiente;
            }
        }

        throw new RuntimeException("Ambiente não existe");
    }


}
