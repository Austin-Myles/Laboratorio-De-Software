

public interface InstrumentoMusical {
    void hacerSonar(Notas n, int duracion);
    String queEs();
    void afinar(FrecuenciasDeLA f);
}
abstract class InstrumentoDeViento implements InstrumentoMusical {
    @Override
    public void hacerSonar(Notas n, int duracion) {
        for(int i = 0; i < duracion; i++){
            System.out.println(n.getNombre());
        }
    }

    @Override
    public String queEs() {
        return "Instrumento de Viento";
    }

}
class InstrumentoDeCuerda implements InstrumentoMusical {
    public void hacerSonar(Notas n, int duracion) {
        for(int i = 0; i < duracion; i++){
            System.out.println(n.getNombre());
        }
    }
    public String queEs() {
        return "Instrumento de Cuerda";
    }

    public void afinar(FrecuenciasDeLA f){
        System.out.println(f.getFrecuencia());
    }
}

class Piano implements InstrumentoMusical {

    @Override
    public void hacerSonar(Notas n, int duracion) {
        for(int i = 0; i < duracion; i++){
            System.out.println(n.getNombre());
        }
    }

    @Override
    public String queEs() {
        return "Es un piano";
    }

    @Override
    public void afinar(FrecuenciasDeLA f) {
        System.out.println(f.getFrecuencia());
    }
}
