class FutbolException extends Exception{}

class Falta extends FutbolException{}

class EquipoIncompleto extends
        FutbolException{}

class ClimaException extends Exception{}

class Lluvia extends ClimaException{}

class Mano extends Falta{}

class Partido {
    Partido() throws FutbolException{}
    void evento() throws FutbolException{}
    void jugada() throws EquipoIncompleto,
            Falta{}
    void penal(){}
}

interface Tormenta {
    void evento() throws Lluvia;
    void diluvio() throws Lluvia;
}
