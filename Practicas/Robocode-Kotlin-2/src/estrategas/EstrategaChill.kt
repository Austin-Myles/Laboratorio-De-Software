package estrategas

import laboratorio.NicoustinRobot
import estrategias.Estrategia
import estrategias.EstrategiaWalls
import estrategias.EstrategiaAntiWalls

class EstrategaChill : Estratega {
    
    // Clase anidada para evaluar condiciones de estrategia
    inner class StrategyEvaluator {
        fun evaluateEnemyCount(enemyCount: Int): StrategyType {
            return when {
                enemyCount > MANY_ENEMIES_THRESHOLD -> StrategyType.DEFENSIVE
                else -> StrategyType.OFFENSIVE
            }
        }
        
        fun shouldSwitchStrategy(currentType: StrategyType, newType: StrategyType): Boolean {
            return currentType != newType
        }
    }
    
    // Enum anidado para tipos de estrategia
    enum class StrategyType {
        DEFENSIVE, OFFENSIVE
    }
    
    // Clase anidada para crear estrategias
    inner class StrategyFactory {
        fun createStrategy(type: StrategyType): Estrategia {
            return when (type) {
                StrategyType.DEFENSIVE -> EstrategiaWalls()
                StrategyType.OFFENSIVE -> EstrategiaAntiWalls()
            }
        }
    }
    
    private val evaluator = StrategyEvaluator()
    private val factory = StrategyFactory()
    private var currentStrategyType: StrategyType? = null
    
    // Función local con lambda para determinar estrategia
    private fun determineStrategy(enemyCount: Int): Estrategia {
        val strategyType = evaluator.evaluateEnemyCount(enemyCount)
        
        // Usar lambda para crear estrategia
        val createStrategy: (StrategyType) -> Estrategia = { type ->
            factory.createStrategy(type)
        }
        
        return createStrategy(strategyType)
    }
    
    // Función local para validar cambio de estrategia
    private fun validateStrategyChange(enemyCount: Int): Boolean {
        val newType = evaluator.evaluateEnemyCount(enemyCount)
        return currentStrategyType?.let { currentType ->
            evaluator.shouldSwitchStrategy(currentType, newType)
        } ?: true // Primera vez siempre cambia
    }
    
    override fun chooseStrategy(robot: NicoustinRobot): Estrategia {
        val enemigos = robot.others
        
        // Usar función local con validación
        return if (validateStrategyChange(enemigos)) {
            val strategy = determineStrategy(enemigos)
            currentStrategyType = evaluator.evaluateEnemyCount(enemigos)
            strategy
        } else {
            // Mantener estrategia actual usando lambda
            val currentType = currentStrategyType ?: StrategyType.DEFENSIVE
            factory.createStrategy(currentType)
        }
    }
    
    companion object {
        private const val MANY_ENEMIES_THRESHOLD = 3
    }
}

