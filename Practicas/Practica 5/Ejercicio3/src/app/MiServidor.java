package app;

import annotations.Invocar;
import annotations.Servidor;

@Servidor(direccion="localhost", puerto = 8080, archivo = "server_log.txt")
public class MiServidor {

    @Invocar
    public void atenderPeticion(String ipCliente){
        System.out.println("Petición atendida por app.MiServidor. Lógica de app ejecutada para: " + ipCliente);
    }

    //@Invocar
    public void otraPeticion(String ipCliente) {
        System.out.println("Otra petición atendida por mi Servidor para: " + ipCliente);
    }

    @Invocar
    public void metodoNormal(){
        System.out.println("Metodo normal");
    }

}
