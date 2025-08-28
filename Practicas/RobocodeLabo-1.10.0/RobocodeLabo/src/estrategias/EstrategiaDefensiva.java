package estrategias;

import laboratorio.LaboRobot;
import robocode.ScannedRobotEvent;
import robocode.WinEvent;
import robocode.util.Utils;

public class EstrategiaDefensiva implements Estrategia{

    private LaboRobot robot;
    double anguloDisparo;
    /*La idea en este caso es que la estrategía sea defensiva enfocandonos
    más en la movilidad que en el ataque. La idea es dar vueltas disparando
    solo cuando en nuestro sensor detectemos enemigos, pero no perseguiremos.
    Disparos de "advertencia" con poco gasto energetico.

    Run contiene todo el comportamiento predeterminado.
    * */
    public void run() {
    }

    @Override
    public void runB(LaboRobot robot) {

    }

    /*Como se establecio anteriormente, en el caso de encontrarnos con
     * un robot en nuestro escaner, se disparará y se seguira adelante*/
    @Override
    public void onScannedRobot(ScannedRobotEvent e) {
        anguloDisparo = Utils.normalRelativeAngleDegrees(e.getBearing() + (this.robot.heading - this.robot.gunHeading));
        this.robot.turnGunRight((int)(anguloDisparo));
        this.robot.fire(1);
    }


    /*La estrategia de hacer donas o dar vueltas seguira siendo la misma
    * hasta que nuestra energia se encuentre por debajo del 15%, a esa energia
    * optaremos por una estrategia más desesperada*/
    @Override
    public void onHitByBullet() {
        if(robot.energy <= 15){
            //Hacemos el cambio de estrategia.
            analyzeStrategy();
        }

        int anguloAct = robot.heading;
        int anguloNuevo = (anguloAct + 180) % 360;
        robot.turnTo(anguloNuevo);
        robot.ahead(10);
    }

    /*Intentaremos evitar tocar las paredes, asi que dudo que esta función
    * posea mucho desarrollo.*/
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
