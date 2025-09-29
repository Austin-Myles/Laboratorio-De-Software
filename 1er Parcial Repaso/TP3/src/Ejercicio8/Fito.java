package Ejercicio8;

public enum Fito {
    FITO;
    private Piano piano;

    Fito() {
        this.piano = new Piano();
    }

    public Piano getPiano() {
        return piano;
    }

    public void tocarCancion(Notas[] notas, int[] duracionXNota) {
        for (Notas nota : notas) {
            for (int i=0; i<duracionXNota[i]; i++) {
                piano.hacerSonar(nota, i);
            }
        }
    }
}
