package laboratorio;

import estrategas.Estratega;
import estrategas.EstrategaChill;
import estrategias.Estrategia;
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

    private Estratega estratega;
    private Estrategia estrategia;
    private String currentSituationKey = "start";

    /**
     * Constructor predeterminado para Robocode
     * Inicializa con el evaluador y la estrategia apropiada según la situación actual
     */
    public NicoustinRobot() {
        // Lo inicializamos con el estratega chill.
        this.estratega = new EstrategaChill();
        
        // Inicializar con la estrategia apropiada
        this.estrategia = this.estratega.chooseStrategy(this, currentSituationKey);
        this.currentSituationKey = estrategia.getSituationKey();
    }
    
    /**
     * Constructor con estrategia y evaluador personalizados (para testing)
     */
    public NicoustinRobot(Estratega estratega) {
        this.estratega = estratega;
        setEstrategia(this.estratega.chooseStrategy(this, currentSituationKey));
    }

    /**
     * Establece una nueva estrategia aunque en este caso el que la decide es el estratega.
     */
    public void setEstrategia(Estrategia estrategia) {
        this.estrategia = estrategia;
        estrategia.setRobot(this);
    }
    

    /**
     * Evalúa la situación actual y cambia de estrategia si es necesario
     * Se ejecuta antes de cada acción importante
     */
    /// Tendriamos que cambiar este, despues lo cambiamos del todo
    private void updateStrategy() {
        if (estratega != null) {
            Estrategia newStrat = estratega.chooseStrategy(this, currentSituationKey);
            if (newStrat != null && !newStrat.getClass().equals(estrategia.getClass())){
                currentSituationKey = newStrat.getSituationKey();
                System.out.println("🔄 NICOUSTIN: Cambiando estrategia '" + currentSituationKey + "' → '" + newStrat + "'");
                setEstrategia(newStrat);
            }
        }
    }

    public void setEstratega(Estratega estratega) {
        this.estratega = estratega;
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