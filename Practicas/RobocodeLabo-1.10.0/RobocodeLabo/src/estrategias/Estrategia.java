package estrategias;

import laboratorio.LaboRobot;
import robocode.JuniorRobot;

public interface Estrategia {
    //private JuniorRobot robot;

    void run();
    void onScannedRobot();
    void onHitByBullet();
    void onHitWall();
    void setRobot(LaboRobot robot);
    void analyzeStrategy();

}
