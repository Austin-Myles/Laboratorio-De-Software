package RobotGod.estrategas;

import RobotGod.estrategias.Estrategia;
import RobotGod.estrategias.EstrategiaCampera;
import RobotGod.estrategias.EstrategiaWalls;
import NicoustinRobot;

public class EstrategaSunTzu implements Estratega{
    @Override
    public Estrategia chooseStrategy(NicoustinRobot robot, String estrategiaActual) {
        if(robot.energy > 50 && robot.others <= 2) {
            return new EstrategiaCampera();
        } else {
            return new EstrategiaWalls();
        }
    }
}
