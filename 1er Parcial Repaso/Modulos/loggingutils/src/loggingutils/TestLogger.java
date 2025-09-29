package src.loggingutils;

public class TestLogger {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.logInfo("Omg info");
        logger.logWarning("Alerta omg");
        logger.logError("Error omfg!!!");

    }
}
