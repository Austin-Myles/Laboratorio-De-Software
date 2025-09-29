package loggingutils;

public class Main {
    public static void main(String[] args) {
        Logger.getInstance().logError("Error o no");
        Logger.getInstance().logWarning("Peligro? de que?");
        Logger.getInstance().logInfo("INFO? de que?");
    }
}
