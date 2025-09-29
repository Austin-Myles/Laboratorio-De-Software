package Ejercicio8;

public interface InstrumentoMusical {
    void hacerSonar(Notas n, int duracion);
    String queEs();
    void afinar(FrecuenciasDeLA f);
}

abstract class InstrumentoDeViento implements InstrumentoMusical{
    public void hacerSonar(Notas n, int duracion) {
        System.out.println("Sonar Vientos");
        for(int i = 0; i < duracion; i++){
            System.out.println(n.getCifrado());
        }
    }
    public String queEs(){
        return "Instrumento de Viento";
    }

}

class InstrumentoDeCuerda implements InstrumentoMusical {
    public void hacerSonar(Notas n, int duracion) {
        System.out.println("Sonar Cuerdas");
        for(int i = 0; i < duracion; i++){
            System.out.println(n.getCifrado());
        }
    }
    public String queEs(){
        return "Instrumento de Cuerdas";
    }
    @Override
    public void afinar(FrecuenciasDeLA f) {
        System.out.println("Afinamos las cuerdas");
        System.out.println(f.getDescripcion());
    }
}