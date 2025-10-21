package laboratorio

/**
 * Configuración del robot con valores por defecto
 */
data class RobotConfig(
    val colors: Triple<Int, Int, Int> = Triple(0, 0, 14),
    val moveAmount: Int = 100,
    val wallDistance: Int = 20,
    val celebrationTurns: Int = 4,
    val maxWaitTurns: Int = 50,
    val adjustmentInterval: Int = 20
)

/**
 * Configuración específica para estrategias
 */
data class StrategyConfig(
    val walls: RobotConfig = RobotConfig(
        colors = Triple(0, 0, 14),
        moveAmount = 100,
        wallDistance = 20,
        celebrationTurns = 4
    ),
    val antiWalls: RobotConfig = RobotConfig(
        colors = Triple(6, 0, 14),
        moveAmount = 50,
        wallDistance = 30,
        celebrationTurns = 4,
        maxWaitTurns = 50,
        adjustmentInterval = 20
    )
)
