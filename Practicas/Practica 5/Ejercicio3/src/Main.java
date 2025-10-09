import app.Contenedor;
import app.MiServidor;

public class Main {
    public static void main(String[] args) {
        Contenedor.iniciar(MiServidor.class);
    }
}
