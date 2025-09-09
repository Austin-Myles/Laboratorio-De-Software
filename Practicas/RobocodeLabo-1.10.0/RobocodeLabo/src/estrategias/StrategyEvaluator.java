package estrategias;

import laboratorio.NicoustinRobot;

/**
 * StrategyEvaluator - Evaluador de estrategias
 * 
 * Analiza las condiciones actuales de batalla y determina qué estrategia
 * es la más apropiada para la situación. Mantiene una referencia al robot
 * para evaluar sin necesidad de pasar parámetros.
 */
public class StrategyEvaluator {
    
    private NicoustinRobot robot;
    
    /**
     * Constructor que establece la referencia al robot
     * 
     * @param robot El robot al cual evaluar las estrategias
     */
    public StrategyEvaluator(NicoustinRobot robot) {
        this.robot = robot;
    }
    
    /**
     * Evalúa la situación actual y devuelve la clave de estrategia apropiada
     * 
     * @return Clave de la estrategia recomendada ("start" o "end")
     */
    public String evaluateStrategy() {
        int enemigos = robot.others;
        
        // Lógica de evaluación basada en número de enemigos
        if (enemigos > 3) {
            return "start"; // Muchos enemigos: priorizar supervivencia
        } else {
            return "end";   // Pocos enemigos: cambiar a caza
        }
    }
    
    /**
     * Determina si es necesario cambiar de estrategia
     * 
     * @param currentStrategy Estrategia actual
     * @param newStrategy Nueva estrategia recomendada
     * @return true si debe cambiar, false en caso contrario
     */
    public boolean shouldChangeStrategy(String currentStrategy, String newStrategy) {
        return !currentStrategy.equals(newStrategy);
    }
}
