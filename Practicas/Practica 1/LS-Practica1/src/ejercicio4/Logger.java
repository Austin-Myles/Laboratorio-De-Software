package ejercicio4;

public class Logger {

    private static Logger instance;

    private Logger() {
    }

    public static Logger getInstance(){
        if(instance == null){
            instance = new Logger();
            System.out.println("Nueva instancia de Logger creada");
        }
        return instance;
    }

    public void logInfo(String mensaje){
        System.out.println("[INFO] " + mensaje);
    }

    public void logError(String mensaje){
        System.out.println("[ERROR] " + mensaje);
    }

    public void logWarning(String mensaje){
        System.out.println("[WARNING] " + mensaje);
    }

}
