import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.swing.ImageIcon;

import exception.ImagemNaoEncontradaException;

public class CarregadorImagem {

    private Image carregarImagemCaminho(String caminho) {

        URL recurso = getClass().getResource(caminho);

        if (recurso == null) {
            throw new ImagemNaoEncontradaException("Caminho da imagem inválido: " + caminho);
        }

        return new ImageIcon(recurso).getImage();
    }

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
