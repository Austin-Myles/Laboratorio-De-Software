package estrategas;

import estrategias.Estrategia;
import estrategias.EstrategiaCampera;
import estrategias.EstrategiaWalls;
import laboratorio.NicoustinRobot;

public class EstrategaSunTzu implements Estratega{
    @Override
    public Estrategia chooseStrategy(NicoustinRobot robot, String estrategiaActual) {
        if(robot.energy < 50) {
            return new EstrategiaCampera();
        } else {
            return new EstrategiaWalls();
        }
    }
}
