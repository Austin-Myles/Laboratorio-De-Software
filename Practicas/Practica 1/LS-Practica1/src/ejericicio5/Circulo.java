package ejericicio5;

import ejericicio5.geometria.FiguraGeometrica;

public class Circulo extends FiguraGeometrica {
    private int radio;
    public Circulo(){
    }

    public void dibujar(){
        System.out.println("Se dibuja un círculo con radio de " + this.radio +" y de color " + this.getColor() +".");
    }

    public int area(){
        return (int) (Math.PI * Math.pow(radio,2));
    }

    public void setRadio(int radio){
        this.radio = radio;
    }

    public int getRadio(){
        return this.radio;
    }
}
