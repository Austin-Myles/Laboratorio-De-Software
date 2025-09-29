public enum Fito {

    FITOPAEZ;

    private Piano piano;

    private Fito() {
        this.piano = new Piano();
    }

    public void tocarPiano(Notas[] notas, int[] tiempos) {
        //Todas las giladas que decis! 🎶🎵
        for (int i = 0; i < notas.length; i++) {
            this.piano.hacerSonar(notas[i], tiempos[i]);
        }
    }
}
