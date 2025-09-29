package ej7;

public class Main {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        logger1.logInfo("Inicio de la aplicación");
        logger2.logWarning("Esto es una advertencia");
        logger1.logError("Ocurrió un error");

        // Verificamos que ambos apuntan al mismo objeto
        System.out.println(logger1 == logger2); // true
    }
}

