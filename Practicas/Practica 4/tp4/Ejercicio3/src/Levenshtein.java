import java.util.*;

public class Levenshtein {

    public List<String> comparacionLev(Map<String, Set<String>> diccionario, String cadena){
        //Creamos el objeto Qgrama para realizar la comparación.
        Qgrama motorQ = new Qgrama();

        //Tomamos la cadena procesada como Qgrama para verificar las palabras que cumplan el criterio de comparación.
        List<String> gramas = motorQ.generarQgrama(cadena);

        //Lista auxiliar para guardar las palabras que cumplan el criterio de comparación
        Set<String> candidatos = new HashSet<>();

        //Recolectamos las palabras que contienen al menos un grama
        for(String grama: gramas){
            if(diccionario.containsKey(grama)){
                candidatos.add(grama);
            }
        }

        //Filtramos por distancia de Levenshtein
        List<String> similares = new ArrayList<>();
        for(String palabra: candidatos){
            if(distanciaLevenshtein(cadena, palabra) < 3){
                similares.add(palabra);
            }
        }

        return similares;
    }

    private int distanciaLevenshtein(String a, String b){
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) dp[i][0] = i;
        for (int j = 0; j <= b.length(); j++) dp[0][j] = j;

        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                int costo = (a.charAt(i - 1) == b.charAt(j - 1)) ? 0 : 1;
                dp[i][j] = Math.min(
                        Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                        dp[i - 1][j - 1] + costo
                );
            }
        }

        return dp[a.length()][b.length()];
    }
}
