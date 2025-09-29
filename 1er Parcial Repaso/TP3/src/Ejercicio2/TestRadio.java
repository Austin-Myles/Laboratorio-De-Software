package Ejercicio2;

public class TestRadio {
    public static void main(String[] args) {
        InnerStatic.Circulo cir = new InnerStatic.Circulo(5);
        cir.getArea();
        cir.getLongitudCircunsferencia();

        InnerStatic.Circulo cir2 = new InnerStatic.Circulo(7);
        cir2.getArea();
        cir2.getLongitudCircunsferencia();
    }
}
