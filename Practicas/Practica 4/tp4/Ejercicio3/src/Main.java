import java.util.*;

public class Main {
    public static void main(String[] args) {
        Qgrama qg = new Qgrama();

        qg.agregarPalabra("glucosa");
        qg.agregarPalabra("glucolisis");
        qg.agregarPalabra("glutamina");

        System.out.println("Diccionario de gramas:");
        for (Map.Entry<String, Set<String>> entry : qg.getDiccionario().entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
    }
}
