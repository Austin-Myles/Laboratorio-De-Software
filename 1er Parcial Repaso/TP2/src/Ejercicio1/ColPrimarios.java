package Ejercicio1;

interface ColPrimarios {
    int ROJO=1, VERDE=2, AZUL=4;
}

interface ColArcoIris extends ColPrimarios {
    int AMARILLO=3, NARANJA=5, INDIGO=6, VIOLETA=7;
}

interface ColImpresion extends ColPrimarios {
    int AMARILLO=8, CYAN=16, MAGENTA=32;
}

interface TodosLosColores extends ColImpresion, ColArcoIris {
    int FUCSIA=17, BORDO=ROJO+90;
}

class MisColores implements ColImpresion, ColArcoIris {
    public MisColores() {
        //int unColor=AMARILLO; //Es incorrecto dado que la implementación resulta ser ambigua xd, hay que aclarar de que paquete implementamos la var.

    }
}
