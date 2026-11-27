/**
 * Objeto capaz de receber os comandos digitados pelo jogador. Evita que o
 * terminal dependa diretamente da classe do jogo.
 *
 * @author Paulo Sergio Mendes Taciano
 * @author João Vitor Rezende Marciano
 */
public interface IReceptorComando {
    
    /**
     * Trata uma linha digitada pelo jogador.
     *
     * @param linha Texto digitado.
     */
    void receberComando(String linha);
}
