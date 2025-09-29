import java.util.*;

public class Qgrama {
    private Map<String, Set<String>> diccionario;

    public Qgrama() {
        this.diccionario = new HashMap<>();
    }

    public void agregarPalabra(String palabra) {
        List<String> qgrama = this.generarQgrama(palabra);
        for (String qgramas : qgrama) {
            diccionario.computeIfAbsent(qgramas, k -> new HashSet<>()).add(palabra);
        }
    }

    public List<String> generarQgrama(String palabra) {
        List<String> gramas = new ArrayList<>();
        String auxPalabra = "#" + palabra + "$";
        for (int i = 1; i < auxPalabra.length(); i++) {
            gramas.add(auxPalabra.substring(i-1, i+1));
        }
        return gramas;
    }

    public Map<String, Set<String>> getDiccionario() {
        return diccionario;
    }
}

