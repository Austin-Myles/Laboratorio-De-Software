package laboratorio

/**
 * Estados posibles del robot durante la batalla
 */
enum class RobotState {
    INITIALIZING,    // Configurando colores y detectando campo
    PATROLLING,      // Patrullando perimetralmente (Walls)
    AMBUSHING,       // Esperando en esquina (AntiWalls)
    MOVING_TO_CORNER, // Moviéndose hacia una esquina
    RETREATING,      // Retrocediendo por impacto
    CELEBRATING,     // Celebrando victoria
    ADJUSTING        // Ajustando posición
}

/**
 * Esquinas del campo de batalla
 */
enum class Corner(val angle: Int) {
    TOP_RIGHT(315),
    TOP_LEFT(225), 
    BOTTOM_LEFT(135),
    BOTTOM_RIGHT(45)
}
