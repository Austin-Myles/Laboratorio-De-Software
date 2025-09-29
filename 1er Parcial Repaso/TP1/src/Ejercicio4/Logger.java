package Ejercicio4;

public class Logger {
    private static Logger instance;

    private Logger() {
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void logInfo(String mensaje) {
        System.out.println(mensaje);
    }

    public void logError(String mensaje) {
        System.out.println(mensaje);
    }

    public void logWarning(String mensaje) {
        System.out.println(mensaje);
    }
}
