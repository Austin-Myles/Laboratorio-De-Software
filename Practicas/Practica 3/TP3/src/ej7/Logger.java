package ej7;

import java.util.logging.*;

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
        java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.INFO, mensaje);

    }

    public void logError(String mensaje){
        java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, mensaje);
    }

    public void logWarning(String mensaje){
        java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.WARNING, mensaje);
    }

}
