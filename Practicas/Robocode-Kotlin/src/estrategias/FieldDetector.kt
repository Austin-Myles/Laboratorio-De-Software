package estrategias

import laboratorio.NicoustinRobot
import java.lang.System.out
import kotlin.jvm.JvmStatic

/**
 * Detector de tamaño del campo de batalla para JuniorRobot
 * Usa exploración por rebote + fallback a tamaños estándar
 */
object FieldDetector {

    private var fieldWidth: Int = 0
    private var fieldHeight: Int = 0
    private var fieldMeasured: Boolean = false

    // Tamaños estándar de Robocode como fallback
    private const val DEFAULT_WIDTH = 800
    private const val DEFAULT_HEIGHT = 600

    /**
     * Detecta el tamaño del campo usando exploración por rebote
     */
    @JvmStatic
    fun detectFieldSize(robot: NicoustinRobot) {
        if (fieldMeasured) return

        println("🔍 DETECTOR: Iniciando detección de campo...")

        try {
            measureByBouncing(robot)
            fieldMeasured = true
            println("🔍 DETECTOR: Campo detectado - ${fieldWidth}x${fieldHeight}")
        } catch (e: Exception) {
            // Fallback a tamaños estándar
            fieldWidth = DEFAULT_WIDTH
            fieldHeight = DEFAULT_HEIGHT
            fieldMeasured = true
            println("🔍 DETECTOR: Usando tamaño estándar - ${fieldWidth}x${fieldHeight}")
        }
    }

    /**
     * Mide el campo usando rebotes contra paredes
     */
    private fun measureByBouncing(robot: NicoustinRobot) {
        val startHeading = robot.heading
        var wallHitCount = 0
        var totalMovement = 0

        // Alinearse hacia un cardinal (Norte)
        robot.turnLeft((robot.heading % 90).toInt())

        // Ir hacia una pared para establecer punto de referencia
        robot.ahead(1000) // onHitWall nos detendrá
        wallHitCount++

        // Medir una dimensión (por ejemplo, ancho)
        robot.turnRight(90)

        // Contar movimiento hasta la siguiente pared
        var moveDistance = 0
        while (moveDistance < 1000) {
            try {
                robot.ahead(50) // Movimientos pequeños para medir
                moveDistance += 50
                totalMovement += 50
            } catch (e: Exception) {
                break
            }
        }

        // Estimar dimensiones basado en el movimiento
        fieldWidth = when {
            totalMovement < 300 -> 400
            totalMovement < 500 -> 600
            totalMovement < 700 -> 800
            else -> 1000
        }
        fieldHeight = when (fieldWidth) {
            400 -> 400
            600 -> 400
            800 -> 600
            else -> 800
        }

        // Volver a posición inicial aproximada
        robot.turnLeft(180)
        robot.ahead(100)
    }

    /**
     * Metodo alternativo usando aproximación inteligente
     */
    @JvmStatic
    fun smartDetection(robot: NicoustinRobot) {
        if (fieldMeasured) return

        val testDistance = 200
        var wallsHit = 0

        // Probar Norte
        robot.turnLeft(robot.heading.toInt())
        try {
            robot.ahead(testDistance)
        } catch (e: Exception) {
            wallsHit++
        }

        // Probar Este
        robot.turnRight(90)
        try {
            robot.ahead(testDistance)
        } catch (e: Exception) {
            wallsHit++
        }

        // Estimar tamaño basado en cuántas paredes encontramos
        when {
            wallsHit >= 2 -> {
                fieldWidth = 400; fieldHeight = 400
            }
            wallsHit == 1 -> {
                fieldWidth = 600; fieldHeight = 400
            }
            else -> {
                fieldWidth = 800; fieldHeight = 600
            }
        }

        fieldMeasured = true
        println("🔍 DETECTOR: Estimación inteligente - ${fieldWidth}x${fieldHeight}")
    }

    @JvmStatic
    fun getFieldWidth(): Int =
        if (!fieldMeasured) DEFAULT_WIDTH else fieldWidth

    @JvmStatic
    fun getFieldHeight(): Int =
        if (!fieldMeasured) DEFAULT_HEIGHT else fieldHeight

    @JvmStatic
    fun getMaxFieldSize(): Int =
        maxOf(getFieldWidth(), getFieldHeight())

    @JvmStatic
    fun isFieldDetected(): Boolean = fieldMeasured

    @JvmStatic
    fun reset() {
        fieldMeasured = false
        fieldWidth = 0
        fieldHeight = 0
    }
}
