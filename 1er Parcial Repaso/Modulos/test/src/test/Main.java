package test;

import loggingutils.*;

public class Main {
    public static void main(String[] args){
        Logger log = Logger.getInstance();

        log.getInstance().logError("Error o no");
        log.getInstance().logWarning("Peligro? de que?");
        log.getInstance().logInfo("INFO? de que?");
    }
}
