package estrategias;

import laboratorio.LaboRobot;
import robocode.JuniorRobot;

public interface Strategy {
    //private JuniorRobot robot;

    void run();
    void onScannedRobot();
    void onHitByBullet();
    void onHitWall();
    void setRobot(LaboRobot robotito);
    void analyzeStrategy();
}
