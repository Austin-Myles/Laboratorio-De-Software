package estrategias

import laboratorio.NicoustinRobot
import laboratorio.turnToCardinal
import laboratorio.adjustFromWall
import laboratorio.celebrate
import robocode.WinEvent

class EstrategiaWalls : Estrategia {

    private var robot: NicoustinRobot? = null
    private var peek: Boolean = false
    private var moveAmount: Int = 0

    override fun runB(robot: NicoustinRobot) {
        this.robot = robot

        robot.setColors(0, 0, 14)

        println("WALLS: Iniciando patrulla perimetral")

        // Detectar tamaño del campo primero
        FieldDetector.detectFieldSize(robot)

        // Inicialización EXACTA como Walls original
        moveAmount = FieldDetector.getMaxFieldSize()
        peek = false

        println("WALLS: Campo detectado '${FieldDetector.getFieldWidth()}' x '${FieldDetector.getFieldHeight()}'")

        // Posicionamiento inicial EXACTO como Walls
        robot.turnToCardinal()

        // Moverse hasta encontrar la pared
        robot.ahead(moveAmount)

        // Configuración inicial del cañón EXACTA
        peek = true
        robot.turnGunRight(90)
        robot.turnRight(90)

        // Loop principal
        while (true) {
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
        robot?.fire(2.0)
        println("WALLS: Enemigo en perímetro - Disparando (peek: $peek)")
    }

    override fun onHitByBullet() {
        println("🧱 WALLS: Impacto recibido - Manteniendo patrulla")
    }

    override fun onHitWall() {
        robot?.adjustFromWall()
        println("🧱 WALLS: Contacto con pared - Ajustando posición perimetral")
    }

    override fun setRobot(robot: NicoustinRobot) {
        this.robot = robot
    }

    override fun onWin(event: WinEvent) {
        println("🏆 WALLS: ¡Victoria perimetral!")

        // Celebración tipo Walls - movimiento cuadrado
        robot?.celebrate()
    }
}