package RobotGod.estrategas;

import RobotGod.estrategias.Estrategia;
import RobotGod.estrategias.EstrategiaCampera;
import RobotGod.estrategias.EstrategiaWalls;
import RobotGod.laboratorio.NicoustinRobot;

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
