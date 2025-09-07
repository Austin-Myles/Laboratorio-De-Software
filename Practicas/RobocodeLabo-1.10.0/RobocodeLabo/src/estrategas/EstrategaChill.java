package estrategas;

import estrategias.Estrategia;
import estrategias.EstrategiaAntiWalls;
import estrategias.EstrategiaWalls;
import laboratorio.NicoustinRobot;

public class EstrategaChill implements Estratega {
    @Override
    public Estrategia chooseStrategy(NicoustinRobot robot, String estrategiaActual) {
        if (robot.others > 3){
            return new EstrategiaWalls();
        } else {
            return new EstrategiaAntiWalls();
        }
    }
}
