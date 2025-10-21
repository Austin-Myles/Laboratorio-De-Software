package laboratorio

import robocode.*
import estrategias.FieldDetector
import kotlin.math.*

/**
 * Extension functions para operaciones comunes del robot
 */

/**
 * Gira el robot hacia el cardinal más cercano (Norte, Sur, Este, Oeste)
 */
fun NicoustinRobot.turnToCardinal() {
    turnLeft((heading % 90).toInt())
}

/**
 * Mueve el robot hacia una esquina específica
 */
fun NicoustinRobot.moveToCorner(corner: Corner) {
    val fieldWidth = FieldDetector.getFieldWidth()
    val fieldHeight = FieldDetector.getFieldHeight()
    val cornerDistance = maxOf(fieldWidth, fieldHeight) / 2
    
    turnLeft((corner.angle - heading).toInt())
    ahead(cornerDistance)
    turnRight((corner.angle - heading).toInt())
}

/**
 * Realiza un ajuste de posición pequeño
 */
fun NicoustinRobot.adjustPosition() {
    ahead(RobotConstants.ADJUSTMENT_DISTANCE)
    back(RobotConstants.ADJUSTMENT_DISTANCE / 2)
}

/**
 * Celebración con movimiento cuadrado usando lambda
 */
fun NicoustinRobot.celebrate() {
    val celebrationAction: (Int) -> Unit = { turn ->
        ahead(RobotConstants.CELEBRATION_DISTANCE)
        turnRight(RobotConstants.RIGHT_ANGLE)
    }
    
    repeat(RobotConstants.CELEBRATION_TURNS, celebrationAction)
}

/**
 * Celebración con giro de cañón usando lambda
 */
fun NicoustinRobot.celebrateWithGun() {
    val gunCelebrationAction: (Int) -> Unit = { turn ->
        turnGunRight(RobotConstants.RIGHT_ANGLE)
        ahead(RobotConstants.CELEBRATION_DISTANCE / 2)
    }
    
    repeat(RobotConstants.CELEBRATION_TURNS, gunCelebrationAction)
}

/**
 * Retrocede cuando es impactado
 */
fun NicoustinRobot.retreatFromHit() {
    turnRight(hitByBulletBearing + RobotConstants.RIGHT_ANGLE)
    ahead(RobotConstants.RETREAT_DISTANCE)
}

/**
 * Ajusta posición cuando toca pared
 */
fun NicoustinRobot.adjustFromWall() {
    back(RobotConstants.WALL_DISTANCE)
    turnRight(RobotConstants.HALF_RIGHT_ANGLE)
}

/**
 * Dispara con potencia según la distancia usando lambda
 */
fun NicoustinRobot.fireByDistance(distance: Int) {
    val powerCalculator: (Int) -> Double = { dist ->
        when {
            dist < RobotConstants.CLOSE_RANGE -> RobotConstants.HIGH_POWER
            dist < RobotConstants.MEDIUM_RANGE -> RobotConstants.MEDIUM_POWER
            else -> RobotConstants.LOW_POWER
        }
    }
    
    val power = powerCalculator(distance)
    fire(power)
}

/**
 * Función de extensión para ejecutar acciones con manejo de errores
 */
fun NicoustinRobot.safeExecute(action: () -> Unit) {
    try {
        action()
    } catch (e: Exception) {
        println("Error ejecutando acción: ${e.message}")
    }
}

/**
 * Función de extensión para movimiento inteligente con lambda
 */
fun NicoustinRobot.smartMove(distance: Int, condition: () -> Boolean = { true }) {
    if (condition()) {
        safeExecute { ahead(distance) }
    }
}

/**
 * Función de extensión para giro inteligente con lambda
 */
fun NicoustinRobot.smartTurn(angle: Int, condition: () -> Boolean = { true }) {
    if (condition()) {
        safeExecute { turnRight(angle) }
    }
}

/**
 * Función de extensión para escaneo inteligente con lambda
 */
fun NicoustinRobot.smartScan(scanAction: () -> Unit = { turnGunLeft(10) }) {
    safeExecute(scanAction)
}

/**
 * Función de extensión para calcular distancia a esquina
 */
fun NicoustinRobot.distanceToCorner(corner: Corner): Double {
    val fieldWidth = FieldDetector.getFieldWidth()
    val fieldHeight = FieldDetector.getFieldHeight()
    
    val cornerX = when (corner) {
        Corner.TOP_RIGHT, Corner.BOTTOM_RIGHT -> fieldWidth.toDouble()
        else -> 0.0
    }
    
    val cornerY = when (corner) {
        Corner.TOP_RIGHT, Corner.TOP_LEFT -> fieldHeight.toDouble()
        else -> 0.0
    }
    
    val distanceX = cornerX - x
    val distanceY = cornerY - y
    
    return sqrt(distanceX * distanceX + distanceY * distanceY)
}

/**
 * Función de extensión para determinar si está cerca de una esquina
 */
fun NicoustinRobot.isNearCorner(corner: Corner, threshold: Int = 100): Boolean {
    return distanceToCorner(corner) < threshold
}

/**
 * Función de extensión para movimiento hacia esquina con lambda
 */
fun NicoustinRobot.moveTowardsCorner(corner: Corner, moveCondition: (Corner) -> Boolean = { true }) {
    if (moveCondition(corner)) {
        val distance = distanceToCorner(corner).toInt()
        val moveDistance = minOf(distance, RobotConstants.DEFAULT_MOVE_AMOUNT)
        
        turnLeft((corner.angle - heading).toInt())
        safeExecute { ahead(moveDistance) }
    }
}
