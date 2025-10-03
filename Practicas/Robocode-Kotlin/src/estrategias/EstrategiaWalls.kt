package estrategias

import laboratorio.NicoustinRobot
import robocode.WinEvent
import java.lang.System.out
import java.lang.reflect.Field

class EstrategiaWalls() : Estrategia {

    private lateinit var robot: NicoustinRobot
    private var peek : Boolean = false
    private var moveAmount : Int = 0

    override fun runB(robot: NicoustinRobot) {
        this.robot = robot

        robot.setColors(0, 0, 14)

        out.println("WALLS: Iniciando patrulla perimetral")

        // Detectar tamaño del campo primero
        FieldDetector.detectFieldSize(robot)

        // Inicialización EXACTA como Walls original
        // Ahora usamos el tamaño real del campo detectado
        moveAmount = FieldDetector.getMaxFieldSize()
        peek = false

        out.println("WALLS: Campo detectado '${FieldDetector.getFieldWidth()}' x '${FieldDetector.getFieldHeight()}'")

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

        out.println("WALLS: Enemigo en perímetro - Disparando (peek: $peek)")
    }

    override fun onHitByBullet() {
        out.println("🧱 WALLS: Impacto recibido - Manteniendo patrulla")
    }

    override fun onHitWall() {
        // Comportamiento Walls: ajustarse cuando toca pared
        robot.back(20)

        // Asegurar que seguimos el patrón perimetral
        if (!peek) {
            robot.turnRight(90)
        }

        out.println("🧱 WALLS: Contacto con pared - Ajustando posición perimetral")
    }

    private fun onHitRobotLogic() {
        // Si está enfrente nuestro, retroceder un poco
        if (robot.scannedBearing > -90 && robot.scannedBearing < 90) {
            robot.back(100)
        } else {
            // Si está detrás nuestro, avanzar un poco
            robot.ahead(100)
        }

        out.println("🧱 WALLS: Colisión con robot - Ajustando posición")
    }

    override fun setRobot(robot: NicoustinRobot) {
        this.robot = robot
    }


    override fun evalStrat(): String? {
        // La evaluación la hace el robot, no la estrategia individual
        return null
    }
    override fun onWin(event: WinEvent) {
        out.println("🏆 WALLS: ¡Victoria perimetral!")

        // Celebración tipo Walls - movimiento cuadrado
        for (i in 0 until 4) {
            robot.ahead(50)
            robot.turnRight(90)
        }
    }
}