package Ejercicio1;

public interface InstrumentoMusical {
    void hacerSonar();
    String queEs();
    void afinar();
}
abstract class InstrumentoDeViento implements InstrumentoMusical {
    @Override
    public void hacerSonar(){
        System.out.println("Sonar Vientos");
    }

    @Override
    public String queEs() {
        return "Instrumento de Viento";
    }

}
class InstrumentoDeCuerda implements InstrumentoMusical {
    public void hacerSonar(){
        System.out.println("Sonar Cuerdas");
    }
    public String queEs() {
        return "Instrumento de Cuerda";
    }

    public void afinar(){
        System.out.println("Cuerda");
    }
}
