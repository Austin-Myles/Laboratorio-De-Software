package Ejercicio8;

import java.util.Arrays;

public class Piano implements InstrumentoMusical{

    @Override
    public void hacerSonar(Notas n, int duracion) {
        for (int i = 0; i < duracion; i++){
            System.out.println(Arrays.toString(Notas.values()));
        }
    }

    @Override
    public String queEs() {
        return "Soy un piano juju";
    }

    @Override
    public void afinar(FrecuenciasDeLA f) {
        System.out.println("Afinando a frecuencias de la piano de " + f.getDescripcion());
    }
}
