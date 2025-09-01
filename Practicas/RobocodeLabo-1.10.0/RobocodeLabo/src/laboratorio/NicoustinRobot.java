package laboratorio;

import estrategias.Estrategia;
import estrategias.StrategyMapper;
import estrategias.StrategyEvaluator;
import robocode.*;

/**
 * NicoustinRobot - Robot principal del laboratorio
 * 
 * Implementa un sistema de estrategias dinámico que se adapta según el número de enemigos:
 * - Inicio (muchos enemigos): EstrategiaWalls (supervivencia defensiva)
 * - Final (pocos enemigos): EstrategiaAntiWalls (caza ofensiva)
 * 
 * Utiliza el patrón Strategy Pattern con evaluación automática de situaciones.
 */
public class NicoustinRobot extends JuniorRobot {
    
    private Estrategia estrategia;
    private StrategyEvaluator strategyEvaluator;
    private String currentSituationKey = "start";

    /**
     * Constructor predeterminado para Robocode
     * Inicializa con el evaluador y la estrategia apropiada según la situación actual
     */
    public NicoustinRobot() {
        // Configurar el evaluador de estrategias con referencia al robot
        this.strategyEvaluator = new StrategyEvaluator(this);
        
        // Inicializar con la estrategia apropiada
        this.estrategia = StrategyMapper.getStrategyForSituation(this);
        this.currentSituationKey = StrategyMapper.getCurrentSituation();
    }
    
    /**
     * Constructor con estrategia y evaluador personalizados (para testing)
     */
    public NicoustinRobot(Estrategia estrategia, StrategyEvaluator evaluator) {
        this.strategyEvaluator = evaluator;
        setEstrategia(estrategia);
    }

    /**
     * Establece una nueva estrategia y la vincula al robot
     */
    public void setEstrategia(Estrategia estrategia) {
        this.estrategia = estrategia;
        estrategia.setRobot(this);
    }
    
    /**
     * Establece un nuevo evaluador de estrategias
     */
    public void setStrategyEvaluator(StrategyEvaluator evaluator) {
        this.strategyEvaluator = evaluator;
    }
    
    /**
     * Evalúa la situación actual y cambia de estrategia si es necesario
     * Se ejecuta antes de cada acción importante
     */
    private void updateStrategy() {
        if (estrategia != null && strategyEvaluator != null) {
            String newSituationKey = strategyEvaluator.evaluateStrategy();
            
            if (strategyEvaluator.shouldChangeStrategy(currentSituationKey, newSituationKey)) {
                System.out.println("🔄 NICOUSTIN: Cambiando estrategia '" + currentSituationKey + "' → '" + newSituationKey + "'");
                
                this.currentSituationKey = newSituationKey;
                StrategyMapper.updateSituation(newSituationKey);
                this.estrategia = StrategyMapper.getStrategyForSituation(this);
            }
        }
    }

    /**
     * Bucle principal del robot
     */
    @Override	
    public void run() {
        updateStrategy();
        estrategia.runB(this);
    }

    /**
     * Evento: Robot enemigo detectado
     */
    public void onScannedRobot() {
        updateStrategy();
        estrategia.onScannedRobot();
    }

    /**
     * Evento: Impacto de bala recibido
     */
    @Override
    public void onHitByBullet() {
        updateStrategy();
        estrategia.onHitByBullet();
    }
    
    /**
     * Evento: Colisión con pared
     */
    @Override
    public void onHitWall() {
        updateStrategy();
        estrategia.onHitWall();
    }
}