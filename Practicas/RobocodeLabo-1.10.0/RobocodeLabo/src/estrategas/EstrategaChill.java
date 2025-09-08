package RobotGod.estrategas;

import RobotGod.estrategias.Estrategia;
import RobotGod.estrategias.EstrategiaAntiWalls;
import RobotGod.estrategias.EstrategiaWalls;
import NicoustinRobot;

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
