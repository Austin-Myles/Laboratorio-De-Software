package Ejercicio2;

import Ejercicio2.geometria.FiguraGeometrica;
import java.util.Arrays;
import java.util.Comparator;

public class PaintTest {
    public static void main(String[] args) {
        Paint pt = new Paint();
        pt.init();
        FiguraGeometrica[] paleta = pt.getPaleta();
        Arrays.sort(paleta, Comparator.comparingDouble(FiguraGeometrica::area));

        System.out.println("Area del paleta: " + Arrays.toString(paleta));
        /*
        for (FiguraGeometrica figuraGeometrica : paleta) {

        }

         */
    }
}
