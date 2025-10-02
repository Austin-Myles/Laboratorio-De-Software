package estrategias

import laboratorio.NicoustinRobot
import java.lang.reflect.Field

class EstrategiaWalls : Estrategia {

    private lateinit var robot: NicoustinRobot
    private var peek : Boolean = false
    private var moveAmount : Int = 0

    override fun runB(robot: NicoustinRobot) {
        this.robot = robot

        robot.setColors(0, 0, 14)

        println("WALLS: Iniciando patrulla perimetral")

        // Detectar tamaño del campo primero
        FieldDetector.detectFieldSize(robot)

        // Inicialización EXACTA como Walls original
        // Ahora usamos el tamaño real del campo detectado
        moveAmount = FieldDetector.getMaxFieldSize()
        peek = false

        println("WALLS: Campo detectado '${FieldDetector.getFieldWidth()}' x '${FieldDetector.getFieldHeight()}'")

        // Posicionamiento inicial EXACTO como Walls
        // turnLeft(getHeading() % 90)
        robot.turnLeft((robot.heading % 90).toInt())

        // Moverse hasta encontrar la  pared
        robot.ahead(moveAmount)

        // Configuración inicial del cañón EXACTA
        peek = true
        robot.turnGunRight(90)
        robot.turnRight(90)

        // Loop principal
        while(true){
            // Mirar antes de movernos cuando ahead() termine
            peek = true
            // Moverse por la pared - usar tamaño real de campo
            robot.ahead(moveAmount)
            // No mirar ahora
            peek = false
            // Girar a la siguiente pared
            robot.turnRight(90)
        }
    }

    override fun onScannedRobot() {
        robot.fire(2.0)

        if(peek){
            // Aquí iria un scan() opcional...
            pass
        }

        println("WALLS: Enemigo en perímetro - Disparando (peek: $peek)")
    }

    override fun onHitByBullet() {
        println("🧱 WALLS: Impacto recibido - Manteniendo patrulla"))
    }

    override fun onHitWall() {
        // Comportamiento Walls: ajustarse cuando toca pared
        robot.back(20.0)

        // Asegurar que seguimos el patrón perimetral
        if (!peek) {
            robot.turnRight(90)
        }

        println("🧱 WALLS: Contacto con pared - Ajustando posición perimetral")
    }

    private fun onHitRobotLogic() {
        // Si está enfrente nuestro, retroceder un poco
        if (robot.scannedBearing > -90 && robot.scannedBearing < 90) {
            robot.back(100.0)
        } else {
            // Si está detrás nuestro, avanzar un poco
            robot.ahead(100.0)
        }

        println("🧱 WALLS: Colisión con robot - Ajustando posición")
    }

    override fun setRobot(robot: NicoustinRobot) {
        this.robot = robot
    }

    override fun evalStrat(): String? {
        // La evaluación la hace el robot, no la estrategia individual
        return null
    }
    override fun onWin(e: WinEvent) {
        println("🏆 WALLS: ¡Victoria perimetral!")

        // Celebración tipo Walls - movimiento cuadrado
        repeat(4) {
            robot.ahead(50.0)
            robot.turnRight(90)
        }
    }
}