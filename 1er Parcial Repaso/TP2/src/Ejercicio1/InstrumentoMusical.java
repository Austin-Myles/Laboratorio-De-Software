package Ejercicio1;

public interface InstrumentoMusical {
    void hacerSonar();
    String queEs();
    void afinar();
}

abstract class InstrumentoDeViento implements InstrumentoMusical{
    public void hacerSonar(){
        System.out.println("Sonar Vientos");
    }
    public String queEs(){
        return "Instrumento de Viento";
    }

}

class InstrumentoDeCuerda implements InstrumentoMusical {
    public void hacerSonar(){
        System.out.println("Sonar Cuerdas");
    }
    public String queEs(){
        return "Instrumento de Cuerdas";
    }
    @Override
    public void afinar() {
        System.out.println("Afinamos las cuerdas");
    }
}
