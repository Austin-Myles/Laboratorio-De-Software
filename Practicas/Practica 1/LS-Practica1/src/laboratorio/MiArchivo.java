package laboratorio;
import java.io.File;
public class MiArchivo extends File {
    public MiArchivo() {
        super("MiArchivo.txt");
        System.out.println("Mi Archivo instanciado") ;
    }
}