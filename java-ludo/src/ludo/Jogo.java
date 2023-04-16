package ludo;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;
import util.Logger;

/**
 * Controla o jogo como um todo
 */
public class Jogo {

    /**
     * Lista de peões no tabuleiro
     */
    ArrayList<Peão> peão = new ArrayList<>();
    /**
     * Ordem dos turnos, por cor
     */
    ArrayList<String> ordemTurnos = new ArrayList<>();
    Logger logger = Logger.getInstance();
    /**
     * Objeto responsável por calcular coisas relacionadas a um peão e ao
     * tabuleiro, mas sem o contexto dos demais peões (isso é repsonsabilidade
     * do Jogo)
     */
    Posições tabuleiro = new Posições();
    /**
     * Índice que indica de quem é o turno
     */
    int turno = 0;

    public Jogo() {
        // Estabelece a ordem de jogo
        this.ordemTurnos.add("amarelo");
        this.ordemTurnos.add("vermelho");
        this.ordemTurnos.add("azul");
        this.ordemTurnos.add("verde");

        // Instancia os peões do jogo
        for (int i = 0; i < 4; i++) {
            this.peão.add(new Peão("amarelo", "amarelo-" + i));
            this.peão.add(new Peão("vermelho", "vermelho-" + i));
            this.peão.add(new Peão("azul", "azul-" + i));
            this.peão.add(new Peão("verde", "verde-" + i));
        }

        do {
            proximoTurno();
        } while (!encerraTurno());

        System.out.println("VITOOOOOOOOORIA");

    }

    /**
     * Rola um D6 e retorna o resultado
     *
     * @return um valor aleatório entre 1 e 6
     */
    public int rolaDado() {
        int r = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        logger.log("Dado rolado. Resultado: " + r);

        return r;
    }

    /**
     * Executa o próximo turno do jogo de acordo com a ordem (ordemTurnos)
     */
    private void proximoTurno() {
        String cor = this.ordemTurnos.get(this.turno);
        logger.log("Turno de " + cor);

        // Envia para o cliente da vez uma requisição de arremesso de dado
        // TODO: implementar requisição para lançar dado
        // Cliente avisa que o dado foi lançado no cliente
        int resultadoDado = this.rolaDado();

        // Publica para todos os clientes o resultado do dado
        // TODO: implementar publicação do resultado do dado
        Peão peãoNaBase = this.próximoPeãoNaBase();
        // TODO: verificar aqui quais peões PODEM ser movidos a quantidade de
        // espaços resultantes no dado
        ArrayList<Peão> peõesEmJogo = this.getPeõesEmJogo();

        // Condição de vitória é ter nenhum peão em jogo e nenhum peão na base
        if (peãoNaBase == null && peõesEmJogo.isEmpty()) {
            logger.log("CARA EU ACHO QUE O " + cor + " VENCEU!!");
        }

        for (Peão p : peõesEmJogo) {
            System.out.println("    " + p.getId() + " - " + p.getPosição());
        }

        // Verifica ações possíveis de acordo com o resultado do dado
        if (resultadoDado == 6) {
            if (peãoNaBase != null && !peõesEmJogo.isEmpty()) {
                logger.log("Jogador " + cor + " pode libertar um novo peão ou mover um peão liberto");
                // TODO: perguntar ao jogador da vez a sua decisão
                boolean escolheuLiberar = true;
                if (escolheuLiberar == true) {
                    this.libertaPeão(peãoNaBase);
                } else {
                    this.movePeão(peõesEmJogo.get(0).getId(), resultadoDado);
                }
            } else if (peãoNaBase != null && peõesEmJogo.isEmpty()) {
                logger.log("Jogador " + cor + " deve libertar um peão");
                this.libertaPeão(peãoNaBase);
            } else if (peãoNaBase == null && !peõesEmJogo.isEmpty()) {
                logger.log("Jogador " + cor + " deve escolher um peão para mover");
                this.movePeão(peõesEmJogo.get(0).getId(), resultadoDado);
            }
        } else {
            if (peõesEmJogo.isEmpty()) {
                logger.log("Jogador " + cor + " perdeu a vez");
            } else {
                logger.log("Jogador " + cor + " deve escolher um peão para mover");
                this.movePeão(peõesEmJogo.get(0).getId(), resultadoDado);
            }
        }
    }

    /**
     * Libera um novo peão do jogador da vez
     *
     * @param p peão a ser liberto
     */
    private void libertaPeão(Peão p) {
        String cor = this.ordemTurnos.get(this.turno);
        int posiçãoInicial = Posições.getPosiçãoInicial(cor);
        p.setPosição(posiçãoInicial);
        logger.log("Peão " + p.getId() + " do jogador " + cor + " foi liberto na casa " + posiçãoInicial);
    }

    /**
     * Move um peão uma quantidade de espaços, verificando se é possível e
     * calculando possíveis colisões com peões inimigos
     *
     * @param id ID do peão a mover (vindo do cliente)
     * @param quantidade quantidade de espaços a mover
     */
    private void movePeão(String id, int quantidade) {
        Peão p = this.getPeãoById(id);
        int posiçãoFutura = this.tentaMovimentoVálido(p, quantidade);
        if (posiçãoFutura != -1) {
            p.setPosição(posiçãoFutura);
            this.verificaColisão(p);
            this.verificaConclusão(p);
            logger.log("Peão " + p.getId() + " moveu-se " + quantidade + " espaço(s) para a posição " + p.getPosição() + ".");
        } else {
            logger.log("Peão " + p.getId() + " não pode mover " + quantidade + " espaço(s).");
        }
    }

    public void verificaConclusão(Peão p) {
        if (Posições.getPosiçãoFinal(p.getCor()) == p.getPosição()) {
            p.setConcluído(true);
        }
    }

    /**
     * Verifica colisões com Peões inimigos e os reseta se for preciso
     *
     * @param p Peão do jogador da vez
     */
    public void verificaColisão(Peão p) {
        for (Peão currentPeão : this.peão) {
            if (currentPeão.getCor() == p.getCor() || currentPeão.getPosição() == -1) {
                continue;
            }
            if (!tabuleiro.posiçãoSegura(currentPeão.getPosição(), currentPeão.getCor()) && currentPeão.getPosição() == p.getPosição()) {
                logger.log("Peão " + currentPeão.getId() + " do jogador " + currentPeão.getCor() + " foi resetado!");
                currentPeão.reseta();
                return;
            }
        }
    }

    /**
     * Retorna um peão com base em seu ID
     *
     * @param id ID a procurar
     * @return O Peão com o ID correspondente
     */
    private Peão getPeãoById(String id) {
        for (Peão p : this.peão) {
            if (p.getId() == id) {
                return p;
            }
        }

        throw new NoSuchElementException("Um peão com esse ID não existe!");
    }

    /**
     * Verifica se um movimento é válido, retornando a futura posição caso seja
     * ou -1 caso seja inválido
     *
     * @param p o Peão a se mover
     * @param quantidade a quantidade de espaços para mover
     * @return a nova posição do peão caso seja possível, ou -1 caso contrário
     */
    private int tentaMovimentoVálido(Peão p, int quantidade) {
        int posiçãoFutura = p.getPosição();
        if (!this.tabuleiro.temEspaçoNaRetalFinal(posiçãoFutura, p.getCor(), quantidade)) {
            return -1;
        }
        // Simula o andar da quantidade correta de casas para verificar colisão
        for (int i = 0; i < quantidade; i++) {
            posiçãoFutura = this.tabuleiro.próximaPosição(posiçãoFutura, p.getCor());
        }

//        Removido porque parece que peões do mesmo time podem ocupar o mesmo espaço
//        for (Peão currentPeão : this.peão) {
//            if (currentPeão.getCor() != p.getCor() || currentPeão.getId() == p.getId()) {
//                continue;
//            }
//            if (currentPeão.getPosição() == posiçãoFutura) {
//                return -1;
//            }
//        }
        return posiçãoFutura;
    }

    private ArrayList<Peão> getPeõesEmJogo() {
        ArrayList<Peão> lista = new ArrayList<>();
        String cor = this.ordemTurnos.get(this.turno);
        for (Peão p : this.peão) {
            if (p.getCor() != cor) {
                continue;
            }
            if (!p.isConcluído() && p.getPosição() != -1) {
                lista.add(p);
            }
        }

        return lista;
    }

    /**
     * Verifica se o jogador da vez tem pelo menos um peão na base e retorna uma
     * referência para ele
     *
     * @return Peão encontrado ou null
     */
    private Peão próximoPeãoNaBase() {
        String cor = this.ordemTurnos.get(this.turno);
        for (Peão p : this.peão) {
            if (p.getCor() != cor) {
                continue;
            }

            if (p.getPosição() == -1) {
                return p;
            }
        }
        return null;
    }

    private boolean encerraTurno() {
        String cor = this.ordemTurnos.get(this.turno);
        logger.log("Fim o turno de " + cor);
        logger.divide();

        this.turno = (this.turno + 1) % this.ordemTurnos.size();

        for (Peão p : this.peão) {
            if (p.getCor() != cor) {
                continue;
            }
            if (!p.isConcluído()) {
                return false;
            }
        }

        return true;
    }
}
