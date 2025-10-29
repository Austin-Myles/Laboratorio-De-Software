import java.lang.Thread.sleep
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
// Usamos un alias para evitar confusión entre los dos tipos de 'Instant'
import kotlin.time.Instant as KotlinInstant
import java.time.Instant as JavaInstant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalTime::class)
fun main() {
    val zonaHoraria = ZoneId.of("America/Argentina/Buenos_Aires")
    val formateador = DateTimeFormatter.ofPattern("HH:mm:ss")

    var momentoKotlin: KotlinInstant
    var momentoJava: JavaInstant
    var ahoraConZona: ZonedDateTime
    var tiempoFormateado: String

    while(true) {
        // 1. Obtenés el momento actual usando kotlin.time.Clock
        momentoKotlin = Clock.System.now()

        momentoJava = JavaInstant.ofEpochSecond(
            momentoKotlin.epochSeconds,
            momentoKotlin.nanosecondsOfSecond.toLong() // Se necesita convertir el Int a Long
        )

        ahoraConZona = momentoJava.atZone(zonaHoraria)

        tiempoFormateado = ahoraConZona.format(formateador)

        println("La hora actual es: $tiempoFormateado")

        sleep(1000)
    }
}