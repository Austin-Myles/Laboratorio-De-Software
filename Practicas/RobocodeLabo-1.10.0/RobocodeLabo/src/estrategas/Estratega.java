package estrategas;

import estrategias.Estrategia;
import laboratorio.NicoustinRobot;

public interface Estratega {

    /**
     * Decide que estrategia se debera usar en función del estado del robot
     */
    Estrategia chooseStrategy(NicoustinRobot robot, String estrategiaActual);
}
