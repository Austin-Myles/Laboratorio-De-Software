package estrategias;

import laboratorio.LaboRobot;
import robocode.ScannedRobotEvent;
import robocode.WinEvent;

public class EstrategiaGenocida implements Estrategia{

    /*
    * Ultimo caso, simplemente acabaremos con todo lo que existe en el mapa
    * no queremos dejar nada con vida, solo optaremos por esta estrategia
    * cuando no queden menos de 3 robots, sin piedad, que corra sangre.
    */
    public void run() {

    }

    @Override
    public void runB(LaboRobot robot) {

    }

    /*Atacamos sin piedad y no le sacamos el ojo de encima*/
    @Override
    public void onScannedRobot(ScannedRobotEvent e) {
        int anguloArma;
        // Si el enemigo esta lejos nos acercamos
        if (e.getDistance() > 150) {
            anguloArma = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));

            turnGunTo(anguloArma);
            turnTo(e.getBearing());
            ahead(e.getDistance() - 140);
            return;
        }

        // Tenemos a nuestra victima cerca
        anguloArma = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));
        turnGunTo(anguloArma);
        fire(3);

        // Si estamos muy cerca nos alejamos un poco
        if (e.getDistance() < 40) {
            if (e.getBearing() > -30 && e.getBearing() <= 30) {
                back(10);
            } else {
                ahead(10);
            }
        }
        scan();
    }

    /*Solo movemos el cuerpo teniendo cuidado de no comernos la pared*/
    @Override
    public void onHitByBullet() {
        int anguloGolpe = robot.hitByBulletBearing;
        robot.turnToGun(anguloGolpe);
        robot.turnTo(anguloGolpe);
        robot.fire(1);
        robot.ahead(10);
    }

    /*Nuevamente evitaremos seguir golpeandonos las paredes eligiendo
    * un angulo para que el robot se desplace adecuadamente*/
    @Override
    public void onHitWall() {
        int anguloAct = robot.heading;
        int anguloNuevo = (anguloAct + 180) % 360;

        robot.back(20);
        robot.turnTo(anguloNuevo);
        robot.ahead(10);
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
