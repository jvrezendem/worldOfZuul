import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import exception.ErroArquivoException;

public class Gabarito {
    private String nomeArquivo;

    public Gabarito(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public void gravar(List<Ambiente> ambientes) {
        try (FileWriter arq = new FileWriter(nomeArquivo)) {
            arq.write("GABARITO DO JOGO\n");

            for (Ambiente ambiente : ambientes) {
                for (Item item : ambiente.getItens()) {
                    arq.write(ambiente.getNome() + ": " + item.getDescricao() + "\n");
                }
            }
        } catch (IOException e) {
            throw new ErroArquivoException("Falha ao salvar no arquivo " + nomeArquivo);
        }
    }
}