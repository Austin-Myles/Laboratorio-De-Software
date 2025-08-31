package Ejercicio4;

import Ejercicio4.geometria.FiguraGeometrica;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Comparator;

public class PaintTest {
    public static void main(String[] args) {
        Paint pt = new Paint();
        pt.init();
        FiguraGeometrica[] paleta = pt.getPaleta();

        try (FileOutputStream archivo = new FileOutputStream("Paleta.ser");
             ObjectOutputStream salida = new ObjectOutputStream(archivo)) {
            salida.writeObject(pt);
            System.out.println("Objeto serializado correctamente");
        } catch (IOException e){
            e.printStrackTrace;
        }
    }
}
