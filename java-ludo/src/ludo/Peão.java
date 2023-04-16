package ludo;

/**
 * Classe respresentando cada peão do jogo de Ludo.
 *
 * @author chinaglia
 */
public class Peão {

    /**
     * Posição do peão no tabuleiro. Quando o valor é -1, o peão não está em
     * jogo.
     */
    private int posição = -1;

    private String cor;
    /**
     * Identificador do peão para que ele possa ser identificado remotamente
     */
    private String id;
    /**
     * Indica se o peão já chegou ao final do tabuleiro
     */
    private boolean concluído = false;

    public Peão(String cor, String id) {
        if (cor != "amarelo" && cor != "azul" && cor != "vermelho" && cor != "verde") {
            throw new IllegalArgumentException(
                    "Cor deve ser: amarelo | azul | vermelho | verde"
            );
        }
        this.id = id;
        this.cor = cor;
    }

    /**
     * @return the posição
     */
    public int getPosição() {
        return posição;
    }

    /**
     * @param posição the posição to set
     */
    public void setPosição(int posição) {
        this.posição = posição;
    }

    /**
     * Move a peça um número de posições adiante
     *
     * @param distância
     */
    public void move(int distância) {
        this.posição += distância;
    }

    /**
     * Retorna o peão para a posição inicial
     */
    public void reseta() {
        this.setPosição(-1);
    }

    /**
     * @return the cor
     */
    public String getCor() {
        return cor;
    }

    /**
     * @param cor the cor to set
     */
    public void setCor(String cor) {
        this.cor = cor;
    }

    /**
     * @return the concluído
     */
    public boolean isConcluído() {
        return concluído;
    }

    /**
     * @param concluído the concluído to set
     */
    public void setConcluído(boolean concluído) {
        this.concluído = concluído;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

}
