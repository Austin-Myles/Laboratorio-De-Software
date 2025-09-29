package estrategias;

import laboratorio.NicoustinRobot;
import robocode.WinEvent;

public class EstrategiaCampera implements Estrategia{
    private NicoustinRobot robot;
    private boolean sweepRight = true;
    @Override
    public void runB(NicoustinRobot robot) {
        this.robot = robot;

        robot.setColors(0, 0, 11); // black, black, purple

        System.out.println("📷 CAMPING: Hora de campear y shootear");

        while (true) {
            if (sweepRight) {
                this.robot.turnGunRight(30);
            } else {
                this.robot.turnGunLeft(30);
            }
            sweepRight = !sweepRight;
        }
    }

    private void moveToCorner(){
        // Vamos a la esquina Norte
        this.robot.turnGunLeft((int)(robot.heading % 90));
        this.robot.ahead(4000);
        this.robot.turnRight(90);
        this.robot.ahead(4000);
        this.robot.turnRight(90);
    }

    @Override
    public void onScannedRobot() {
        // No nos movemos, solo disparamos.
        this.robot.fire(2);
    }

    @Override
    public void onHitByBullet() {
        this.robot.turnGunTo(this.robot.hitByBulletAngle);
    }

    @Override
    public void onHitWall() {
        moveToCorner();
    }

    @Override
    public void setRobot(NicoustinRobot robot) {
        this.robot = robot;
    }

    @Override
    public void onWin(WinEvent e) {
        System.out.println("🏆 CAMPING: ¡Victoria Campera!");

        for (int i = 0; i < 4; i++) {
            robot.ahead(50);
            robot.turnRight(90);
        }
    }

    @Override
    public String getSituationKey() {
        return "Estrategia Campera";
    }

    @Override
    public String evalStrat() {
        return "Campeando duro.";
    }
}