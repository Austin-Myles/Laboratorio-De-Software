package Ejercicio3;

import java.util.HashSet;
import java.util.Set;

public class TestHash {
    public static void main(String[] args) {
        AltSetAgregados ha = new AltSetAgregados();

        Set<String> lista = new HashSet<String>();
        lista.add("1");
        lista.add("2");
        lista.add("3");

        ha.addAll(lista);

        System.out.println(ha.getCantidadAgregados());

        ha.remove("1");
        System.out.println(ha.getCantidadEliminados());

    }
}
