import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.swing.ImageIcon;

import exception.ImagemNaoEncontradaException;

/**
 * Fornece a imagem usada para desenhar cada item do mapa.
 *
 * @author Paulo Sergio Mendes Taciano
 * @author João Vitor Rezende Marciano
 */
public class CarregadorImagem {

    /**
     * Carrega uma imagem incluída junto com o programa.
     *
     * @param caminho Caminho do arquivo de imagem.
     * @return A imagem carregada.
     * @throws exception.ImagemNaoEncontradaException Se o arquivo não existir.
     */
    private Image carregarImagemCaminho(String caminho) {

        URL recurso = getClass().getResource(caminho);

        if (recurso == null) {
            throw new ImagemNaoEncontradaException("Caminho da imagem inválido: " + caminho);
        }

        return new ImageIcon(recurso).getImage();
    }

    /**
     * Desenha uma imagem contendo um caractere.
     *
     * @param c Caractere a ser desenhado.
     * @return A imagem criada.
     */
    private Image carregarImagemChar(char c) {
        int largura = 40;
        int altura = 40;

        BufferedImage imagem = new BufferedImage(
                largura,
                altura,
                BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = imagem.createGraphics();

        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.setColor(Color.BLACK);

        g.drawString(String.valueOf(c), 5, 30);

        g.dispose();

        return imagem;
    }

    /**
     * Escolhe a imagem de acordo com o tipo do item.
     *
     * @param item Item a ser desenhado.
     * @return A imagem do item.
     * @throws RuntimeException Se o tipo do item for desconhecido.
     */
    public Image carregarImagem(ItemMapa item) {
        if (item instanceof Letra) {
            Letra letra = (Letra) item;
            return carregarImagemChar(letra.getC());
        }

        if (item instanceof Jogador) {
            return carregarImagemCaminho("/Imagens/pessoa.png");
        }

        if (item instanceof Parede) {
            return carregarImagemCaminho("/Imagens/tijolo.png");
        }

        throw new RuntimeException("Classe de item inválida");
    }

}
