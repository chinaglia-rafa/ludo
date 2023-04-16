package ludo;

/**
 * Posição no formato linha x coluna
 */
public class Posição {

    /**
     * Linha da grade onde o objeto está, começando de zero
     */
    private int linha = 0;

    /**
     * Coluna da grade onde o objeto está, começando de zero
     */
    private int coluna = 0;

    /**
     * Indica se a posição é segura ou se é uma posição normal
     */
    private boolean segura = false;

    /**
     * Criar uma posição com coordenadas de linha e coluna
     *
     * @param linha
     * @param coluna
     * @param segura
     */
    public Posição(int linha, int coluna, boolean segura) {
        this.linha = linha;
        this.coluna = coluna;
        this.segura = segura;
    }

    /**
     * @return the linha
     */
    public int getLinha() {
        return linha;
    }

    /**
     * @param linha the linha to set
     */
    public void setLinha(int linha) {
        this.linha = linha;
    }

    /**
     * @return the coluna
     */
    public int getColuna() {
        return coluna;
    }

    /**
     * @param coluna the coluna to set
     */
    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    /**
     * @return the segura
     */
    public boolean isSegura() {
        return segura;
    }

    /**
     * @param segura the segura to set
     */
    public void setSegura(boolean segura) {
        this.segura = segura;
    }
}
