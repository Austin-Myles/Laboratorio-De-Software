package Ejercicio8;

public enum Notas {
    DO("C")
    ,RE("D")
    ,MI("E")
    ,FA("F")
    ,SOL("G")
    ,LA("A")
    ,SI("B");

    private String cifrado;

    Notas(String cifrado) {
        this.cifrado = cifrado;
    }

    public String getCifrado() {
        return cifrado;
    }
}
