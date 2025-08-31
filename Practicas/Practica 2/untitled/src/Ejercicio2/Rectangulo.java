package Ejercicio2;

import Ejercicio2.geometria.FiguraGeometrica;

public class Rectangulo extends FiguraGeometrica {
    private int alto;
    private int ancho;

    public Rectangulo(){
    }

    public Rectangulo(int alto, int ancho){
        this.alto = alto;
        this.ancho = ancho;
    }

    public void dibujar(){
        System.out.println("Se dibuja un rectangulo con alto de " + this.alto + " y de ancho "+this.ancho+", de color " + this.getColor() +".");
    }

    public int area(){
        return alto*ancho;
    }
}
