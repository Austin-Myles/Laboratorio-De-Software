import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class Carrera (val nombre: String) : Runnable {
    var distancia: Int = 0

    override fun run() {
        try {
            println("¡Arrancó $nombre")
            while (distancia < 100) {
                distancia++
                //println("$nombre ha recorrido $distancia metros.")

                Thread.sleep(Random.nextLong(10, 50))
            }
            println("¡¡¡$nombre LLEGÓ A LA META !!!")
        } catch (e: InterruptedException) {
            println("$nombre fue descalificado.")
        }
        }
    }

fun main() {
    val totalCorredores = 5
    val tamañoDelPool = 3

    println("¡Preparando la carrera con $totalCorredores corredores y un pool de $tamañoDelPool hilos!")

    // Creamos una "cuadrilla" de 5 hilos
    val executor = Executors.newFixedThreadPool(tamañoDelPool)

    // Creamos los corredores (tareas) y se los damos al ejecutor
    for (i in 1..totalCorredores) {
        val corredor = Carrera("Corredor $i")
        executor.execute(corredor)
    }

    // Le informamos al ejecutor que no acepte más tareas nuevas...
    executor.shutdown()

    // Esperamos a que terminen todas las tareas para finalizar main
    try{
        executor.awaitTermination(1, TimeUnit.MINUTES)
    } catch (e: InterruptedException){
        e.printStackTrace()
    }

    println("¡Todas las carreras terminaron!")


}

