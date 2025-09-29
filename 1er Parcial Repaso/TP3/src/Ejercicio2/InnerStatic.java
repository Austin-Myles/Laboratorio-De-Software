package Ejercicio2;

class InnerStatic {
    static double PI = 3.1416;
    static class Circulo{
        static double radio;

         public Circulo(double radio) {
            Circulo.radio = radio;
        }
        static double getArea(){
            double a = PI * Math.pow(radio, 2);
            System.out.println("El area es: "+a);
            return a;
        }
        static double getLongitudCircunsferencia(){
            double l = 2 * PI * radio;
            System.out.println("El longitud de circunsferencia es: "+l);
            return l;
        }
    }
}
