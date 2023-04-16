package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Logger SINGLETON capaz de armazenar informações relevantes da partida
 *
 * @author chinaglia
 */
public class Logger {

    private ArrayList<Log> logs = new ArrayList<>();
    private boolean silencioso = false;

    private static Logger instance;

    /**
     * Retorna a instância de Logger, garantindo que a classe seja Singleton
     *
     * @return instância de Logger
     */
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    /**
     * Ativa os prints de log
     */
    public void ativar() {
        silencioso = true;
    }

    /**
     * Desativa os prints de log
     */
    public void desativar() {
        silencioso = false;
    }

    public void limpar() {
        this.logs.clear();
    }

    public void log(String texto) {
        Log l = new Log(texto);
        logs.add(l);
        if (!silencioso) {
            System.out.println(l.toString());
        }
    }

    public void divide() {
        Log l = new Log("================");
        logs.add(l);
        if (!silencioso) {
            System.out.println("================");
        }
    }
}

class Log {

    String texto;
    LocalDateTime data = LocalDateTime.now();

    public Log(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return "[" + dtf.format(this.data) + "]: " + texto;
    }
}
