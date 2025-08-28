package estrategias;

import laboratorio.LaboRobot;
import robocode.ScannedRobotEvent;
import robocode.WinEvent;

public class EstrategiaDesesperada implements Estrategia{

    /*
    La idea en este caso es que la estrategía sea lo más cobarde, haciendo que
    el robot solo trace el perímetro del área, en el caso de que trackee un
    robot frente suyo solo se dará la vuelta.

    * */
    public void run() {

    }

    @Override
    public void runB(LaboRobot robot) {

    }

    /*Al detectar un robot simplemente dara un giro de 180° y seguira
     * trazando el perimetro del cuadrado.*/
    @Override
    public void onScannedRobot(ScannedRobotEvent e) {
        int anguloAct = robot.heading;
        int anguloNuevo = (anguloAct + 180) % 360;
        robot.turnTo(anguloNuevo);
    }

    /*Seguiremos escapando.*/
    @Override
    public void onHitByBullet() {
        int anguloAct = robot.heading;
        int anguloNuevo = (anguloAct + 180) % 360;
        robot.turnTo(anguloNuevo);
    }

    /*Mepa que este esta al pedo*/
    @Override
    public void onHitWall() {

    }

    @Override
    public void setRobot(LaboRobot robot) {

    }

    @Override
    public void analyzeStrategy() {

    }

    @Override
    public void onWin(WinEvent e) {

    }
}
