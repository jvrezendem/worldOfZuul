import java.util.ArrayList;
import java.util.List;

import exception.SemCargaExplosivaException;

public class JogadorLogico {

    // jogo deve ser capaz de reconhecer se o jogador achou a chave, para que ela
    // possa ser usada,
    // e de reconhecer se o jogador ja usou a carga explosiva ou não.
    private List<Item> mochila;

    public JogadorLogico() {
        mochila = new ArrayList<>();
        mochila.add(new CargaExplosiva());
    }

    public void pegouItem(Item item) {
        mochila.add(item);
    }

    public void tirouItem(Item item) {
        mochila.remove(item);
    }

    public ChaveMestra getChaveMestra() {
        for (Item item : mochila) {
            if (item instanceof ChaveMestra) {
                return (ChaveMestra) item;
            }
        }
        return null;
    }

    private CargaExplosiva getCargaExplosiva() {
        for (Item item : mochila) {
            if (item instanceof CargaExplosiva) {
                return (CargaExplosiva) item;
            }
        }

        return null;
    }

    public boolean temChave() {
        for (Item item : mochila) {
            if (item instanceof ChaveMestra)
                return true;
        }
        return false;
    }

    public void usarChave() {
        if (temChave() && getChaveMestra().getDurabilidade() > 0) {
            getChaveMestra().usarChave();
        }
    }

    public boolean temCargaExplosiva() {
        for (Item item : mochila) {
            if (item instanceof CargaExplosiva)
                return true;
        }

        return false;
    }

    public String getItensMochilaDescricao() {
        String itens = "";
        for (Item item : mochila) {
            itens += item.getDescricao() + " ";
        }
        return itens;
    }

    public void usarCargaExplosiva() {
        if (temCargaExplosiva()) {
            mochila.remove(getCargaExplosiva());
        } else {
            throw new SemCargaExplosivaException();
        }

    }

}
