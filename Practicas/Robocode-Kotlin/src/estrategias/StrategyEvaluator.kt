package estrategias

import laboratorio.NicoustinRobot

/**
 * StrategyEvaluator - Evaluador de estrategias
 *
 * Analiza las condiciones actuales de batalla y determina qué estrategia
 * es la más apropiada para la situación. Mantiene una referencia al robot
 * para evaluar sin necesidad de pasar parámetros.
 */
class StrategyEvaluator(private val robot: NicoustinRobot) {

    /**
     * Evalúa la situación actual y devuelve la clave de estrategia apropiada
     *
     * @return Clave de la estrategia recomendada ("start" o "end")
     */
    fun evaluateStrategy(): String {
        val enemigos = robot.others

        return if (enemigos > 3) {
            "start" // Muchos enemigos: priorizar supervivencia
        } else {
            "end"   // Pocos enemigos: cambiar a caza
        }
    }

    /**
     * Determina si es necesario cambiar de estrategia
     *
     * @param currentStrategy Estrategia actual
     * @param newStrategy Nueva estrategia recomendada
     * @return true si debe cambiar, false en caso contrario
     */
    fun shouldChangeStrategy(currentStrategy: String, newStrategy: String): Boolean {
        return currentStrategy != newStrategy
    }
}
