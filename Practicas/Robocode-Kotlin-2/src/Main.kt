import laboratorio.NicoustinRobot
import estrategas.EstrategaChill

fun main(args: Array<String>) {
    val robot = NicoustinRobot()
    robot.run()
    // En tiempo de ejecución podrías cambiarla:
    // robot.setEstratega(EstrategaChill())
}
