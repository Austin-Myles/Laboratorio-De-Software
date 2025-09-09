package estrategas;

import estrategias.Estrategia;
import estrategias.EstrategiaWalls;
import estrategias.EstrategiaAntiWalls;
import laboratorio.NicoustinRobot;

public class EstrategaSunTzu implements Estratega{
    public Estrategia chooseStrategy(NicoustinRobot robot) {
        if(robot.energy < 50) {
            return new EstrategiaAntiWalls();
        } else {
            return new EstrategiaWalls();
        }
    }
}
