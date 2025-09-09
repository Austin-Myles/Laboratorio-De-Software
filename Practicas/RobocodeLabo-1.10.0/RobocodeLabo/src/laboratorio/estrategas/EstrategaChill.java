package estrategas;

import estrategias.Estrategia;
import estrategias.EstrategiaAntiWalls;
import estrategias.EstrategiaWalls;
import laboratorio.NicoustinRobot;

public class EstrategaChill implements Estratega {
    public Estrategia chooseStrategy(NicoustinRobot robot) {
        if (robot.others > 3){
            return new EstrategiaWalls();
        } else {
            return new EstrategiaAntiWalls();
        }
    }
}
