package app;

import annotations.Servidor;
import annotations.Invocar;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Contenedor {

    public static void iniciar(Class<?> claseServidor) {

        // 1. Lectura de anotación @Servidor
        if(!claseServidor.isAnnotationPresent(Servidor.class)){
            System.out.println("La clase no está anotada con @Servidor. Abortando");
            return;
        }

        Servidor servidorConf = claseServidor.getAnnotation(Servidor.class);
        String ip = servidorConf.direccion();
        int puerto = servidorConf.puerto();
        String archivo = servidorConf.archivo();

        System.out.println("Iniciando servidor en " + ip + ":" + puerto + ". Y logueando en: " + archivo);

        try (ServerSocket serverSocket = new ServerSocket(puerto)){

            Object instanciaServidor = claseServidor.getDeclaredConstructor().newInstance();

            while(true){
                System.out.println("\n Esperando conexión...");

                //Bloquea hasta que un cliente se conecte.
                Socket clienteSocket = serverSocket.accept();

                String clienteIP = clienteSocket.getInetAddress().getHostAddress();
                System.out.println("Conexión aceptada desde: " + clienteIP);

                // --- Tarea 1: Loguear ---
                loguearPeticion(archivo, clienteIP);

                // --- Tarea 2: Invocar métodos @Invocar ---
                invocarMetodos(instanciaServidor, clienteIP);

                // Cierre simple para finalizar la conexión
                clienteSocket.close();
            }
        } catch (IOException e) {
            System.err.println("Error de I/O en el servidor: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error de Reflexión/Instanciación: " + e.getMessage());
        }
    }

    private static void loguearPeticion(String archivoLog, String clienteIP) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/yyyy HH:mm:ss"));
        String logEntry = String.format("Fecha: %s, IP Cliente: %s\n", timestamp, clienteIP);

        try (PrintWriter write = new PrintWriter(new FileWriter(archivoLog, true))){
            write.println(logEntry);
            System.out.println("[app.Contenedor] Petición logueada.");
        } catch (IOException e) {
            System.err.println("Error de I/O en el servidor: " + e.getMessage());
        }
    }

    private static void invocarMetodos(Object instanciaServidor, String clienteIP) {
        Class<?> claseServidor = instanciaServidor.getClass();

        // Iterar sobre todos los métodos de la clase
        for (Method metodo : claseServidor.getDeclaredMethods()){

            // Verificar si el método de la clase la anotación @Invocar
            if(metodo.isAnnotationPresent(Invocar.class)){
                System.out.println("[app.Contenedor] Invocando método: " + metodo.getName());
            } else {
                // Si la firma es incorrecta, solo invocar sin argumentos
                System.err.println("Firma de método @Invoco incorrecta. Se esperaba: (String IP)");
            }
        }
    }
}
