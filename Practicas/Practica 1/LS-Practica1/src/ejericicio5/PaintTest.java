package ejericicio5;

import ejericicio5.geometria.FiguraGeometrica;

public class PaintTest {
    public static void main(String[] args) {
        Paint pt = new Paint();
        pt.init();
        FiguraGeometrica[] paleta = pt.getPaleta();
        for (FiguraGeometrica figuraGeometrica : paleta) {
            if (figuraGeometrica instanceof Circulo) {
                System.out.println(((Circulo) figuraGeometrica).getRadio());
            }
        }
    }
}
