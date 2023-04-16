package ludo;

import java.util.ArrayList;

/**
 * Classe responsável por conhecer e lidar com posições no tabuleiro
 * quadriculado 15x15 e fazer verificações relacionadas ao caminho dos peões
 * durante a partida
 */
public class Posições {

    public static final int POSIÇÃO_INICIAL_AMARELO = 0;
    public static final int POSIÇÃO_INICIAL_AZUL = 13;
    public static final int POSIÇÃO_INICIAL_VERMELHO = 26;
    public static final int POSIÇÃO_INICIAL_VERDE = 39;

    public static final int POSIÇÃO_FINAL_AMARELO = 57;
    public static final int POSIÇÃO_FINAL_AZUL = 63;
    public static final int POSIÇÃO_FINAL_VERMELHO = 69;
    public static final int POSIÇÃO_FINAL_VERDE = 75;

    // 76 casas no total
    private final ArrayList<Posição> posições = new ArrayList<>();

    public Posições() {
        posições.add(new Posição(1, 8, false)); // 0
        posições.add(new Posição(2, 8, false)); // 1
        posições.add(new Posição(3, 8, false)); // 2
        posições.add(new Posição(4, 8, false)); // 3
        posições.add(new Posição(5, 8, false)); // 4
        posições.add(new Posição(6, 9, false)); // 5
        posições.add(new Posição(6, 10, false)); // 6
        posições.add(new Posição(6, 11, false)); // 7
        posições.add(new Posição(6, 12, true));  // 8
        posições.add(new Posição(6, 13, false)); // 9
        posições.add(new Posição(6, 14, false)); //10
        posições.add(new Posição(7, 14, false)); //11
        posições.add(new Posição(8, 14, false)); //12
        posições.add(new Posição(8, 13, false)); //13
        posições.add(new Posição(8, 12, false)); //14
        posições.add(new Posição(8, 11, false)); //15
        posições.add(new Posição(8, 10, false)); //16
        posições.add(new Posição(8, 9, false)); //17
        posições.add(new Posição(9, 8, false)); //18
        posições.add(new Posição(10, 8, false)); //19
        posições.add(new Posição(11, 8, false)); //20
        posições.add(new Posição(12, 8, true));  //21
        posições.add(new Posição(13, 8, false)); //22
        posições.add(new Posição(14, 8, false)); //23
        posições.add(new Posição(14, 7, false)); //24
        posições.add(new Posição(14, 6, false)); //25
        posições.add(new Posição(13, 6, false)); //26
        posições.add(new Posição(12, 6, false)); //27
        posições.add(new Posição(11, 6, false)); //28
        posições.add(new Posição(10, 6, false)); //29
        posições.add(new Posição(9, 6, false)); //30
        posições.add(new Posição(8, 5, false)); //31
        posições.add(new Posição(8, 4, false)); //32
        posições.add(new Posição(8, 3, false)); //33
        posições.add(new Posição(8, 2, true));  //34
        posições.add(new Posição(8, 1, false)); //35
        posições.add(new Posição(8, 0, false)); //36
        posições.add(new Posição(7, 0, false)); //37
        posições.add(new Posição(6, 0, false)); //38
        posições.add(new Posição(6, 1, false)); //39
        posições.add(new Posição(6, 2, false)); //40
        posições.add(new Posição(6, 3, false)); //41
        posições.add(new Posição(6, 4, false)); //42
        posições.add(new Posição(6, 5, false)); //43
        posições.add(new Posição(5, 6, false)); //44
        posições.add(new Posição(4, 6, false)); //45
        posições.add(new Posição(3, 6, false)); //46
        posições.add(new Posição(2, 6, true));  //47
        posições.add(new Posição(1, 6, false)); //48
        posições.add(new Posição(0, 6, false)); //49
        posições.add(new Posição(0, 7, false)); //50
        posições.add(new Posição(0, 8, false)); //51
        posições.add(new Posição(1, 7, false)); //52
        posições.add(new Posição(2, 7, false)); //53
        posições.add(new Posição(3, 7, false)); //54
        posições.add(new Posição(4, 7, false)); //55
        posições.add(new Posição(5, 7, false)); //56
        posições.add(new Posição(6, 7, false)); //57
        posições.add(new Posição(7, 13, false)); //58
        posições.add(new Posição(7, 12, false)); //59
        posições.add(new Posição(7, 11, false)); //60
        posições.add(new Posição(7, 10, false)); //61
        posições.add(new Posição(7, 9, false)); //62
        posições.add(new Posição(7, 8, false)); //63
        posições.add(new Posição(13, 7, false)); //64
        posições.add(new Posição(12, 7, false)); //65
        posições.add(new Posição(11, 7, false)); //66
        posições.add(new Posição(10, 7, false)); //67
        posições.add(new Posição(9, 7, false)); //68
        posições.add(new Posição(8, 7, false)); //69
        posições.add(new Posição(7, 1, false)); //70
        posições.add(new Posição(7, 2, false)); //71
        posições.add(new Posição(7, 3, false)); //72
        posições.add(new Posição(7, 4, false)); //73
        posições.add(new Posição(7, 5, false)); //74
        posições.add(new Posição(7, 6, false)); //75
    }

    /**
     * Retorna a posição inicial de um peão de acordo com a sua cor
     *
     * @param cor amarelo | azul | vermelho | verde
     * @return posição inicial de acordo com a cor
     */
    public static int getPosiçãoInicial(String cor) {
        switch (cor) {
            case "amarelo":
                return Posições.POSIÇÃO_INICIAL_AMARELO;
            case "azul":
                return Posições.POSIÇÃO_INICIAL_AZUL;
            case "vermelho":
                return Posições.POSIÇÃO_INICIAL_VERMELHO;
            case "verde":
                return Posições.POSIÇÃO_INICIAL_VERDE;
            default:
                throw new IllegalArgumentException(
                        "Cor deve ser: amarelo | azul | vermelho | verde"
                );
        }
    }

    /**
     * Retorna a posição final de um peão de acordo com a sua cor
     *
     * @param cor amarelo | azul | vermelho | verde
     * @return posição final de acordo com a cor
     */
    public static int getPosiçãoFinal(String cor) {
        switch (cor) {
            case "amarelo":
                return Posições.POSIÇÃO_FINAL_AMARELO;
            case "azul":
                return Posições.POSIÇÃO_FINAL_AZUL;
            case "vermelho":
                return Posições.POSIÇÃO_FINAL_VERMELHO;
            case "verde":
                return Posições.POSIÇÃO_FINAL_VERDE;
            default:
                throw new IllegalArgumentException(
                        "Cor deve ser: amarelo | azul | vermelho | verde"
                );
        }
    }

    /**
     * Retorna a Posição no índice index
     *
     * @param index posição do tabuleiro para buscar posições
     * @return a Posição
     */
    public Posição getPosição(int index) {
        return this.posições.get(index);
    }

    /**
     * Indica se a posição é segura de acordo com cada cor
     *
     * @param posição posição do peão
     * @param cor amarelo | azul | vermelho | verde
     * @return boolean indicando se a posição é segura
     */
    public boolean posiçãoSegura(int posição, String cor) {
        // Verifica se é uma posição segura para todos
        if (this.getPosição(posição).isSegura()) {
            return true;
        }

        // Verifica se é uma posição segura para a cor (a casa inicial é segura)
        // para a cor correspondente a ela
        switch (cor) {
            case "amarelo":
                return posição == 0;
            case "azul":
                return posição == 13;
            case "vermelho":
                return posição == 26;
            case "verde":
                return posição == 39;
            default:
                throw new IllegalArgumentException(
                        "Cor deve ser: amarelo | azul | vermelho | verde"
                );
        }
    }

    /**
     * Retorna a próxima posição do Peão no tabuleiro de acordo com sua cor
     *
     * @param posição posição do peão
     * @param cor amarelo | azul | vermelho | verde
     * @return int próxima posição do peão
     */
    public int próximaPosição(int posição, String cor) {
        // Permite que peçass dêem "a volta" no tabuleiro
        if (posição == 51) {
            return 0;
        }
        switch (cor) {
            case "amarelo":
                if (posição == 50) {
                    return 52;
                }
                break;
            case "azul":
                if (posição == 11) {
                    return 58;
                }
                break;
            case "vermelho":
                if (posição == 24) {
                    return 64;
                }
                break;
            case "verde":
                if (posição == 37) {
                    return 70;
                }
                break;
            default:
                throw new IllegalArgumentException(
                        "Cor deve ser: amarelo | azul | vermelho | verde"
                );

        }

        return posição + 1;
    }

    /**
     * Verifica se há espaço para um peão se mover na reta final
     *
     * @param posição posição do peão
     * @param cor amarelo | azul | vermelho | verde
     * @param dado quantidade de casas que o peão andaria
     * @return boolena indicando se há ou não espaço
     */
    public boolean temEspaçoNaRetalFinal(int posição, String cor, int dado) {
        switch (cor) {
            case "amarelo":
                return posição + dado <= 57;
            case "azul":
                return posição + dado <= 63;
            case "vermelho":
                return posição + dado <= 69;
            case "verde":
                return posição + dado <= 75;
            default:
                throw new IllegalArgumentException(
                        "Cor deve ser: amarelo | azul | vermelho | verde"
                );
        }
    }
}
