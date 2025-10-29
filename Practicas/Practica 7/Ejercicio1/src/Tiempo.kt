import java.text.SimpleDateFormat
import java.util.Date
import java.lang.Thread.sleep


fun main(){
    val sdf = SimpleDateFormat("hh:mm:ss")
    while(true) {
        val currentDate = sdf.format(Date())
        println("La hora actual es: "+currentDate)
        sleep(1000)
    }
}