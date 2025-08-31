package Ejercicio3;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        HashSetAgregadosFix hash = new HashSetAgregadosFix();
        System.out.println("Cant:" + hash.getCantidadAgregados());

        Set<String> numeros = new HashSet<String>();
        numeros.add("1");
        numeros.add("2");

        hash.addAll(numeros);
        System.out.println("Cant:" + hash.getCantidadAgregados());



    }
}
