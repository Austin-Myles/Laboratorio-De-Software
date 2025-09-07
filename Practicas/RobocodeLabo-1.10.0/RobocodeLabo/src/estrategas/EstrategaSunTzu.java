package estrategas;

import estrategias.Estrategia;
import estrategias.EstrategiaAntiWalls;
import estrategias.EstrategiaWalls;
import laboratorio.NicoustinRobot;

public class EstrategaSunTzu implements Estratega{
    @Override
    public Estrategia chooseStrategy(NicoustinRobot robot, String estrategiaActual) {
        if(robot.energy > 50 && robot.others <= 2) {
            return new EstrategiaAntiWalls();
        } else {
            return new EstrategiaWalls();
        }
    }
}
