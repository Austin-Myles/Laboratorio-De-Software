import annotations.Archivo;
import annotations.AlmacenarAtributo;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

public class ProcesadorMapeo {

    /**
     * Procesa la instancia de un objeto y almacena sus atributos 
     * anotados en un archivo con formato XML-like.
     * @param objeto La instancia del objeto a serializar.
     */
    public static void mapearYAlmacenar(Object objeto) {
        Class<?> clazz = objeto.getClass();

        // 1. Verificar y obtener la anotación de clase
        if (!clazz.isAnnotationPresent(Archivo.class)) {
            System.err.println("❌ Error: La clase " + clazz.getSimpleName() + " no tiene la anotación @annotations.Archivo.");
            return;
        }

        // 2. Determinar el nombre del archivo
        Archivo anotacionArchivo = clazz.getAnnotation(Archivo.class);
        String nombreArchivo = anotacionArchivo.name().isEmpty() ?
                clazz.getSimpleName() + ".txt" :
                anotacionArchivo.name();

        // 3. Construir el contenido XML-like
        StringBuilder contenido = new StringBuilder();

        // Tag de nombre de la clase
        contenido.append("<nombreClase>").append(clazz.getSimpleName()).append("</nombreClase>\n");

        // 4. Iterar y procesar los campos anotados
        for (Field campo : clazz.getDeclaredFields()) {

            if (campo.isAnnotationPresent(AlmacenarAtributo.class)) {

                // Permitir el acceso a campos privados
                campo.setAccessible(true);

                try {
                    String nombre = campo.getName();
                    Object valor = campo.get(objeto);

                    // Asegurar que el formato de Float sea coherente (30.2, no 30.20)
                    String valorFormateado = (valor instanceof Float)
                            ? String.valueOf(valor).replace("0f", "").replace("f", "")
                            : String.valueOf(valor);

                    // Agregar tags de atributo y valor
                    contenido.append("<nombreAtributo>").append(nombre).append("</nombreAtributo>\n");
                    contenido.append("<nombreValor>").append(valorFormateado).append("</nombreValor>\n");

                } catch (IllegalAccessException e) {
                    System.err.println("No se pudo acceder al campo: " + campo.getName());
                }
            }
        }

        // 5. Escribir el contenido en el archivo
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            writer.write(contenido.toString());
            System.out.println("✅ Mapeo XML-like guardado en: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("❌ Error al escribir en el archivo " + nombreArchivo + ": " + e.getMessage());
        }
    }
}