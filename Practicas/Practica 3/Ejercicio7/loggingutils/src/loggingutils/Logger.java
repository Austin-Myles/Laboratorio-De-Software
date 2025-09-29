package loggingutils;

import java.util.logging.*;

public class Logger {
    private static Logger instance;

    public static Logger getInstance(){
        if(instance == null){
            instance = new Logger();
            System.out.println("Nueva instancia de Logger creada");
        }
        return instance;
    }

    public void logInfo(String mensaje){
        java.util.logging.Logger.getLogger("[INFO]  " + mensaje).info(mensaje);
    }


    public void logError(String mensaje){
        java.util.logging.Logger.getLogger("[ERROR]  " +  mensaje).info(mensaje);
    }

    public void logWarning(String mensaje){
        java.util.logging.Logger.getLogger("[WARNING]  " +  mensaje).info(mensaje);
    }

}
