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
    
    // Clase anidada para manejar la estimación de dimensiones
    inner class DimensionEstimator {
        fun estimateDimensions(totalMovement: Int): Pair<Int, Int> {
            // Usar función local con lambda para estimación
            val widthEstimator: (Int) -> Int = { movement ->
                when {
                    movement < 300 -> 400
                    movement < 500 -> 600
                    movement < 700 -> 800
                    else -> 1000
                }
            }
            
            val heightEstimator: (Int) -> Int = { width ->
                when (width) {
                    400 -> 400
                    600 -> 400
                    800 -> 600
                    else -> 800
                }
            }
            
            val width = widthEstimator(totalMovement)
            val height = heightEstimator(width)
            
            return Pair(width, height)
        }
        
        fun estimateByWallHits(wallsHit: Int): Pair<Int, Int> {
            // Usar función local con lambda para estimación por paredes
            val estimateByHits: (Int) -> Pair<Int, Int> = { hits ->
                when {
                    hits >= 2 -> Pair(400, 400)
                    hits == 1 -> Pair(600, 400)
                    else -> Pair(800, 600)
                }
            }
            
            return estimateByHits(wallsHit)
        }
    }
    
    // Clase anidada para manejar el movimiento de exploración
    inner class ExplorationHandler {
        fun alignToCardinal(robot: NicoustinRobot) {
            robot.turnLeft((robot.heading % 90).toInt())
        }
        
        fun moveUntilWall(robot: NicoustinRobot, maxDistance: Int = 1000) {
            robot.ahead(maxDistance) // onHitWall nos detendrá
        }
        
        fun measureDistance(robot: NicoustinRobot): Int {
            var moveDistance = 0
            val stepSize = 50
            
            // Usar función local con lambda para movimiento de medición
            val measureMovement: () -> Int = {
                var totalMovement = 0
                while (moveDistance < 1000) {
                    try {
                        robot.ahead(stepSize)
                        moveDistance += stepSize
                        totalMovement += stepSize
                    } catch (e: Exception) {
                        break
                    }
                }
                totalMovement
            }
            
            return measureMovement()
        }
        
        fun returnToStart(robot: NicoustinRobot) {
            robot.turnLeft(180)
            robot.ahead(100)
        }
    }
    
    private val estimator = DimensionEstimator()
    private val explorer = ExplorationHandler()

    /**
     * Detecta el tamaño del campo usando exploración por rebote
     */
    @JvmStatic
    fun detectFieldSize(robot: NicoustinRobot) {
        if (fieldMeasured) return

        println("🔍 DETECTOR: Iniciando detección de campo...")

        // Usar función local con lambda para manejo de errores
        val detectionAction: () -> Unit = {
            measureByBouncing(robot)
            fieldMeasured = true
            println("🔍 DETECTOR: Campo detectado - ${fieldWidth}x${fieldHeight}")
        }
        
        val fallbackAction: () -> Unit = {
            fieldWidth = DEFAULT_WIDTH
            fieldHeight = DEFAULT_HEIGHT
            fieldMeasured = true
            println("🔍 DETECTOR: Usando tamaño estándar - ${fieldWidth}x${fieldHeight}")
        }

        try {
            detectionAction()
        } catch (e: Exception) {
            fallbackAction()
        }
    }

    /**
     * Mide el campo usando rebotes contra paredes
     */
    private fun measureByBouncing(robot: NicoustinRobot) {
        val startHeading = robot.heading
        var wallHitCount = 0

        // Usar función local para alineación
        fun alignRobot() {
            explorer.alignToCardinal(robot)
        }
        
        // Usar función local para movimiento inicial
        fun initialMovement() {
            explorer.moveUntilWall(robot)
            wallHitCount++
        }
        
        // Usar función local para medición
        fun measureDimensions() {
            robot.turnRight(90)
            val totalMovement = explorer.measureDistance(robot)
            
            val (width, height) = estimator.estimateDimensions(totalMovement)
            fieldWidth = width
            fieldHeight = height
        }
        
        // Usar función local para retorno
        fun returnToPosition() {
            explorer.returnToStart(robot)
        }

        alignRobot()
        initialMovement()
        measureDimensions()
        returnToPosition()
    }

    /**
     * Metodo alternativo usando aproximación inteligente
     */
    @JvmStatic
    fun smartDetection(robot: NicoustinRobot) {
        if (fieldMeasured) return

        val testDistance = 200
        var wallsHit = 0
        
        // Usar función local con lambda para prueba de dirección
        val testDirection: (Int) -> Unit = { angle ->
            robot.turnLeft(angle)
            try {
                robot.ahead(testDistance)
            } catch (e: Exception) {
                wallsHit++
            }
        }

        // Probar Norte
        testDirection(robot.heading.toInt())

        // Probar Este
        robot.turnRight(90)
        testDirection(0)

        // Usar función local para estimación final
        val estimateFinalDimensions: () -> Unit = {
            val (width, height) = estimator.estimateByWallHits(wallsHit)
            fieldWidth = width
            fieldHeight = height
            fieldMeasured = true
            println("🔍 DETECTOR: Estimación inteligente - ${fieldWidth}x${fieldHeight}")
        }
        
        estimateFinalDimensions()
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
