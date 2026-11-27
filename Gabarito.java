import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import exception.ErroArquivoException;

/**
 * Grava em um arquivo texto o local sorteado para cada item da partida.
 *
 * @author Paulo Sergio Mendes Taciano
 * @author João Vitor Rezende Marciano
 */
public class Gabarito {
    private String nomeArquivo;

    /**
     * Define o arquivo do gabarito.
     *
     * @param nomeArquivo Nome do arquivo a ser gravado.
     */
    public Gabarito(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    /**
     * Grava uma linha para cada item, substituindo o conteúdo anterior.
     *
     * @param ambientes Ambientes com os itens já distribuídos.
     * @throws exception.ErroArquivoException Se o arquivo não puder ser gravado.
     */
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