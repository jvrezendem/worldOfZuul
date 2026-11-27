import java.util.ArrayList;
import java.util.List;

import exception.SemCargaExplosivaException;

/**
 * Representa o que o jogador possui durante a partida, guardado em sua mochila.
 * A figura do jogador no mapa é representada pela classe {@link Jogador}.
 *
 * @author Paulo Sergio Mendes Taciano
 * @author João Vitor Rezende Marciano
 */
public class JogadorLogico {

    // jogo deve ser capaz de reconhecer se o jogador achou a chave, para que ela
    // possa ser usada,
    // e de reconhecer se o jogador ja usou a carga explosiva ou não.
    private List<Item> mochila;

    /**
     * Cria um jogador cuja mochila contém apenas a carga explosiva.
     */
    public JogadorLogico() {
        mochila = new ArrayList<>();
        mochila.add(new CargaExplosiva());
    }

    /**
     * Guarda um item na mochila.
     *
     * @param item Item encontrado.
     */
    public void pegouItem(Item item) {
        mochila.add(item);
    }

    /**
     * Retira um item da mochila.
     *
     * @param item Item a ser retirado.
     */
    public void tirouItem(Item item) {
        mochila.remove(item);
    }

    /**
     * Procura a chave mestra na mochila.
     *
     * @return A chave, ou null se o jogador não a tiver.
     */
    public ChaveMestra getChaveMestra() {
        for (Item item : mochila) {
            if (item instanceof ChaveMestra) {
                return (ChaveMestra) item;
            }
        }
        return null;
    }

    /**
     * Procura a carga explosiva na mochila.
     *
     * @return A carga, ou null se ela já foi usada.
     */
    private CargaExplosiva getCargaExplosiva() {
        for (Item item : mochila) {
            if (item instanceof CargaExplosiva) {
                return (CargaExplosiva) item;
            }
        }

        return null;
    }

    /**
     * Informa se o jogador tem a chave mestra.
     *
     * @return true se a chave está na mochila.
     */
    public boolean temChave() {
        for (Item item : mochila) {
            if (item instanceof ChaveMestra)
                return true;
        }
        return false;
    }

    /**
     * Gasta um uso da chave mestra, se o jogador a tiver e ela tiver durabilidade.
     */
    public void usarChave() {
        if (temChave() && getChaveMestra().getDurabilidade() > 0) {
            getChaveMestra().usarChave();
        }
    }

    /**
     * Informa se o jogador ainda tem a carga explosiva.
     *
     * @return true se a carga está na mochila.
     */
    public boolean temCargaExplosiva() {
        for (Item item : mochila) {
            if (item instanceof CargaExplosiva)
                return true;
        }

        return false;
    }

    /**
     * Descreve o conteúdo da mochila.
     *
     * @return As descrições dos itens separadas por espaço.
     */
    public String getItensMochilaDescricao() {
        String itens = "";
        for (Item item : mochila) {
            itens += item.getDescricao() + " ";
        }
        return itens;
    }

    /**
     * Usa a carga explosiva, retirando-a da mochila.
     *
     * @throws exception.SemCargaExplosivaException Se a carga já foi usada.
     */
    public void usarCargaExplosiva() {
        if (temCargaExplosiva()) {
            mochila.remove(getCargaExplosiva());
        } else {
            throw new SemCargaExplosivaException();
        }

    }

    /**
     * Retorna as dicas que estão na mochila do jogador.
     *
     * @return Lista de dicas guardadas na mochila.
     */
    public List<Dica> getDicas() {
        List<Dica> dicas = new ArrayList<>();

        for (Item item : mochila) {
            if (item instanceof Dica) {
                dicas.add((Dica) item);
            }
        }

        return dicas;
    }

}
