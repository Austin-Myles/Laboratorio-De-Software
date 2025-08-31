package estrategias;

import laboratorio.LaboRobot;
import robocode.ScannedRobotEvent;
import robocode.WinEvent;
import robocode.util.Utils;

import java.awt.*;

public class EstrategiaDesesperada implements Estrategia{

    private LaboRobot robot;
    /*
    La idea en este caso es que la estrategía sea lo más cobarde, haciendo que
    el robot solo trace el perímetro del área, en el caso de que trackee un
    robot frente suyo solo se dará la vuelta.

    * */
    public void run() {
        this.runB(this.robot);
    }

    @Override
    public void runB(LaboRobot robot) {
        this.robot = robot;

        this.robot.setColors(33,33, 33);

        this.robot.ahead(50);
        this.robot.turnRight(90);

        while (true){
            this.robot.ahead(100);
            this.robot.turnRight(90);
        }
    }

    /*Al detectar un robot simplemente dara un giro de 180° y seguira
     * trazando el perimetro del cuadrado.*/
    @Override
    public void onScannedRobot(ScannedRobotEvent e) {
        double anguloAbsoluto = this.robot.heading + e.getBearing();
        double anguloGiro = Utils.normalRelativeAngleDegrees(anguloAbsoluto - this.robot.gunHeading);
        this.robot.turnGunRight((int)(anguloGiro));
        this.robot.fire(0.5);
    }

    /*Seguiremos escapando.*/
    @Override
    public void onHitByBullet() {
        if(this.robot.others < 4) {
            this.analyzeStrategy();
        }
        int anguloAct = this.robot.heading;
        int anguloNuevo = (anguloAct + 180) % 360;
        robot.turnTo(anguloNuevo);
    }

    /*Mepa que este esta al pedo*/
    @Override
    public void onHitWall() {
        int anguloAct = this.robot.heading;
        int anguloNuevo = (anguloAct + 180) % 360;
        this.robot.back(20);
        this.robot.turnTo(anguloNuevo);
        this.robot.ahead(10);
    }


    @Override
    public void setRobot(LaboRobot robot) {
        this.robot = robot;
    }

    @Override
    public void analyzeStrategy() {
        if (this.robot.energy <= 25 || this.robot.others <= 3) {
            // Caso más extremo: poca vida y pocos enemigos
            this.robot.setEstrategia(new EstrategiaGenocida());
            System.out.println("Jean Claude Van Dam en... Matar o Morir.");
        } else if (this.robot.energy >= 35) {
            // Vida relativamente baja → defensivo
            this.robot.setEstrategia(new EstrategiaDefensiva());
            System.out.println("Recargamos milagrosamente, juguemos inteligente!!");
        }
    }

    @Override
    public void onWin(WinEvent e) {
        this.robot.turnGunLeft(360);
        this.robot.turnRight(360);
        this.robot.turnLeft(360);
        this.robot.turnGunRight(360);
    }
}
