package Ejercicio4.geometria;

import java.io.Serial;
import java.io.Serializable;

public abstract class FiguraGeometrica implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String color;


    public FiguraGeometrica() {}

    public abstract void dibujar();

    public abstract int area();

    public void setColor(String color){
        this.color = color;
    }

    public String getColor(){
        return this.color;
    }
}
