package RobotGod.estrategias;

import NicoustinRobot;
import java.util.HashMap;
import java.util.Map;

/**
 * StrategyMapper - Mapea situaciones de batalla a RobotGod.estrategias específicas
 * 
 * Gestiona el cambio automático entre RobotGod.estrategias según el contexto:
 * - "start": Muchos enemigos (>3) → EstrategiaWalls (supervivencia)
 * - "end": Pocos enemigos (≤3) → EstrategiaAntiWalls (caza)
 */
public class StrategyMapper {
    
    private static final Map<String, Class<? extends Estrategia>> strategyMap = new HashMap<>();
    private static String currentSituation = "start";
    
    static {
        strategyMap.put("start", EstrategiaWalls.class);
        strategyMap.put("end", EstrategiaAntiWalls.class);
    }
    
    /**
     * Obtiene la estrategia apropiada para la situación actual
     * 
     * @param robot El robot que necesita la estrategia
     * @return Instancia de la estrategia apropiada
     */
    public static Estrategia getStrategyForSituation(NicoustinRobot robot) {
        String situation = evaluateSituation(robot);
        currentSituation = situation;
        
        Class<? extends Estrategia> strategyClass = strategyMap.get(situation);
        
        try {
            Estrategia strategy = strategyClass.newInstance();
            strategy.setRobot(robot);
            System.out.println("🔄 MAPPER: " + situation + " → " + strategyClass.getSimpleName());
            return strategy;
        } catch (Exception e) {
            System.err.println("⚠️ Error creando estrategia: " + e.getMessage());
            EstrategiaWalls fallback = new EstrategiaWalls();
            fallback.setRobot(robot);
            return fallback;
        }
    }
    
    /**
     * Evalúa la situación actual basada en el número de enemigos
     */
    private static String evaluateSituation(NicoustinRobot robot) {
        return robot.others > 3 ? "start" : "end";
    }
    
    /**
     * Obtiene la situación actual
     */
    public static String getCurrentSituation() {
        return currentSituation;
    }
    
    /**
     * Actualiza la situación actual
     */
    public static void updateSituation(String newSituation) {
        currentSituation = newSituation;
    }
}
