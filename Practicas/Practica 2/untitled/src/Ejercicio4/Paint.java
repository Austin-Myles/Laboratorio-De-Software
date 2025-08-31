package Ejercicio4;

import Ejercicio4.geometria.FiguraGeometrica;

public class Paint {
    private FiguraGeometrica[] paleta;

    public Paint(){
    }

    public void setPaleta(FiguraGeometrica[] paleta){}

    public FiguraGeometrica[] getPaleta(){
        return this.paleta;
    }

    public void init(){
        Circulo cir1 = new Circulo();
        cir1.setRadio(2);
        cir1.setColor("azul");
        Circulo cir2 = new Circulo();
        cir2.setRadio(3);
        cir2.setColor("amarillo");
        Rectangulo rec1 = new Rectangulo(2,3);
        rec1.setColor("verde");
        Rectangulo rec2 = new Rectangulo(4,10);
        rec2.setColor("rojo");

        paleta = new FiguraGeometrica[4];
        paleta[0] = cir1;
        paleta[1] = cir2;
        paleta[2] = rec1;
        paleta[3] = rec2;
    }
}
