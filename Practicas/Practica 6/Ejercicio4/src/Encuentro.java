public class Encuentro extends Partido
        implements Tormenta {

    Encuentro() throws Lluvia,
            FutbolException {}

    Encuentro(String fecha) throws Falta,
            FutbolException {}

    public void diluvio() {}

    public void evento() {}

    void jugada() throws Mano {}

    public static void main(String[] args) {
        try {
            Encuentro enc = new Encuentro();
            enc.jugada();
        } catch (Mano | Lluvia _) {
        } catch (FutbolException e) {
            try {
                Partido par = new Encuentro();
                par.jugada();
            } catch (Lluvia | FutbolException _) {
            }
        }
    }
}