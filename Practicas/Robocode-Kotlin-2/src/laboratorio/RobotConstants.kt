package laboratorio

/**
 * Constantes del robot para evitar números mágicos
 */
object RobotConstants {
    
    // Distancias de movimiento
    const val DEFAULT_MOVE_AMOUNT = 100
    const val WALL_DISTANCE = 20
    const val RETREAT_DISTANCE = 100
    const val ADJUSTMENT_DISTANCE = 20
    const val CELEBRATION_DISTANCE = 50
    
    // Ángulos de giro
    const val RIGHT_ANGLE = 90
    const val HALF_RIGHT_ANGLE = 45
    const val FULL_CIRCLE = 360
    
    // Tiempos y contadores
    const val MAX_WAIT_TURNS = 50
    const val ADJUSTMENT_INTERVAL = 20
    const val CELEBRATION_TURNS = 4
    const val GUN_SCAN_STEP = 10
    
    // Distancias de detección
    const val CLOSE_RANGE = 100
    const val MEDIUM_RANGE = 200
    const val LONG_RANGE = 400
    
    // Potencia de disparo
    const val HIGH_POWER = 3.0
    const val MEDIUM_POWER = 2.0
    const val LOW_POWER = 1.0
    
    // Colores (RGB)
    const val WALLS_BODY_COLOR = 0
    const val WALLS_GUN_COLOR = 0
    const val WALLS_RADAR_COLOR = 14
    
    const val ANTIWALLS_BODY_COLOR = 6
    const val ANTIWALLS_GUN_COLOR = 0
    const val ANTIWALLS_RADAR_COLOR = 14
    
    // Límites de enemigos para cambio de estrategia
    const val MANY_ENEMIES_THRESHOLD = 3
}
