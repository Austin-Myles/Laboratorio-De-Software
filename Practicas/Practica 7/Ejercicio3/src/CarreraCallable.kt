import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.ExecutionException
import java.util.concurrent.Future
import kotlin.jvm.Throws
import kotlin.random.Random

class CarreraCallable(val nombre: String) : Callable<String> {

    @Throws(Exception::class)
    override fun call(): String {
        var distancia = 0
        println("¡Arranca $nombre!")

        while (distancia < 100) {
            distancia++
            //println("$nombre ha recorrido $distancia metros.")

            // SIMULACIÓN DE ABANDONO ⚠️
            if (distancia > 75 && Random.nextInt(1, 100) > 95) {
                // Disparamos una excepción si abandona
                throw Exception("$nombre se lesionó y abandonó en el metro $distancia.")
            }

            Thread.sleep(Random.nextLong(10, 50))
        }
        return "¡¡¡ $nombre LLEGÓ A LA META !!!"
    }
}

fun main(){
    val totalCorredores = 5
    val executor = Executors.newFixedThreadPool(5)

    println("¡Preparando la carrera con $totalCorredores corredores!")

    val listaDeResultados: MutableList<Future<String>> = mutableListOf()

    for(i in 1..totalCorredores){
        val corredor = CarreraCallable("Corredor $i")

        val futuro: Future<String> = executor.submit(corredor)
        listaDeResultados.add(futuro)
    }

    executor.shutdown()

    println("\n--- Obteniendo resultados de la carrera! ---")

    for (futuro in listaDeResultados) {
        try{
            val resultado: String = futuro.get()
            println(resultado)
        } catch (e: ExecutionException){
            println("ERROR: ${e.cause?.message}")
        }
    }

    println("¡La carrera ha concluido y se han reportado todos los resultados!")
}