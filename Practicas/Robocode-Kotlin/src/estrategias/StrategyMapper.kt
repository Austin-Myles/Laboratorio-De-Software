package estrategias

import laboratorio.NicoustinRobot
import java.lang.System.out
import kotlin.jvm.JvmStatic

/**
 * StrategyMapper - Mapea situaciones de batalla a estrategias específicas
 *
 * Gestiona el cambio automático entre estrategias según el contexto:
 * - "start": Muchos enemigos (>3) → EstrategiaWalls (supervivencia)
 * - "end": Pocos enemigos (≤3) → EstrategiaAntiWalls (caza)
 */
object StrategyMapper {

    private val strategyMap: MutableMap<String, Class<out Estrategia>> = mutableMapOf(
        "start" to EstrategiaWalls::class.java,
        "end" to EstrategiaAntiWalls::class.java
    )

    private var currentSituation: String = "start"

    /**
     * Obtiene la estrategia apropiada para la situación actual
     *
     * @param robot El robot que necesita la estrategia
     * @return Instancia de la estrategia apropiada
     */
    @JvmStatic
    fun getStrategyForSituation(robot: NicoustinRobot): Estrategia {
        val situation = evaluateSituation(robot)
        currentSituation = situation

        val strategyClass = strategyMap[situation]

        return try {
            val strategy = strategyClass?.getDeclaredConstructor()?.newInstance()
                ?: EstrategiaWalls()
            strategy.setRobot(robot)
            out.println("🔄 MAPPER: $situation → ${strategyClass?.simpleName}")
            strategy
        } catch (e: Exception) {
            System.err.println("⚠️ Error creando estrategia: ${e.message}")
            return EstrategiaWalls().apply { setRobot(robot) }
        }
    }

    /**
     * Evalúa la situación actual basada en el número de enemigos
     */
    private fun evaluateSituation(robot: NicoustinRobot): String {
        return if (robot.others > 3) "start" else "end"
    }

    /**
     * Obtiene la situación actual
     */
    @JvmStatic
    fun getCurrentSituation(): String = currentSituation

    /**
     * Actualiza la situación actual
     */
    @JvmStatic
    fun updateSituation(newSituation: String) {
        currentSituation = newSituation
    }
}
