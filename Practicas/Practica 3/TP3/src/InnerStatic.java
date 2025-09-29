import java.lang.Math;

import static java.lang.Math.*;

public class InnerStatic {
    //EJERCICIO 2
    static double PI = 3.1416;
    static class Circulo {
        static double radio;

        // Ejercicio a
        public Circulo(double radio) {
            this.radio = radio;
        }

        static double getArea(){
            double a = PI * pow(radio, 2);
            System.out.println("El area es: "+a);
            return a;
        }

        static double getLongitudCircunsferencia(){
            double l = 2 * PI * radio;
            System.out.println("El longitud es: "+l);
            return l;
        }

    }
}
