package src.loggingutils;

import java.util.logging.*;

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
        java.util.logging.Logger.getLogger("INFO").info(mensaje);
    }

    public void logError(String mensaje) {
        java.util.logging.Logger.getLogger("ERROR").info(mensaje);
    }

    public void logWarning(String mensaje) {
        java.util.logging.Logger.getLogger("WARNING").info(mensaje);
    }
}
