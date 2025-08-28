package estrategias;

import laboratorio.LaboRobot;
import robocode.JuniorRobot;
import robocode.ScannedRobotEvent;
import robocode.WinEvent;

public interface Estrategia {

    void runB(LaboRobot robot);
    void onScannedRobot(ScannedRobotEvent e);
    void onHitByBullet();
    void onHitWall();
    void setRobot(LaboRobot robot);
    void analyzeStrategy();
    void onWin(WinEvent e);
}
