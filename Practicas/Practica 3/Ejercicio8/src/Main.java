public class Main {
    public static void main(String[] args) {
        FrecuenciasDeLA[] frecuencias = FrecuenciasDeLA.values();
        //Notas[] notas = Notas.values();
        Piano p = new Piano();

        /**
        for (FrecuenciasDeLA f : frecuencias) {
            for (Notas nota : notas) {
                p.afinar(f);
                p.hacerSonar(nota,1);
            }
        }
         */

        Notas[] notas = {Notas.C, Notas.D, Notas.E};
        int[] tiempos = {10, 2, 3};

        // Siempre la misma instancia
        Fito fito = Fito.FITOPAEZ;
        fito.tocarPiano(notas, tiempos);
    }
}
