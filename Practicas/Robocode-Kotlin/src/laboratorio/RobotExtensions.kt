package laboratorio

import robocode.*
import estrategias.FieldDetector

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
 * Celebración con movimiento cuadrado
 */
fun NicoustinRobot.celebrate() {
    repeat(RobotConstants.CELEBRATION_TURNS) {
        ahead(RobotConstants.CELEBRATION_DISTANCE)
        turnRight(RobotConstants.RIGHT_ANGLE)
    }
}

/**
 * Celebración con giro de cañón
 */
fun NicoustinRobot.celebrateWithGun() {
    repeat(RobotConstants.CELEBRATION_TURNS) {
        turnGunRight(RobotConstants.RIGHT_ANGLE)
        ahead(RobotConstants.CELEBRATION_DISTANCE / 2)
    }
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
 * Dispara con potencia según la distancia
 */
fun NicoustinRobot.fireByDistance(distance: Int) {
    val power = when {
        distance < RobotConstants.CLOSE_RANGE -> RobotConstants.HIGH_POWER
        distance < RobotConstants.MEDIUM_RANGE -> RobotConstants.MEDIUM_POWER
        else -> RobotConstants.LOW_POWER
    }
    fire(power)
}
