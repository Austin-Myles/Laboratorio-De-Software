package estrategias;

import laboratorio.NicoustinRobot;
import robocode.JuniorRobot;
import robocode.WinEvent;

public interface Estrategia {

    void runB(NicoustinRobot robot);
    void onScannedRobot();
    void onHitByBullet();
    void onHitWall();
    void setRobot(NicoustinRobot robot);
    void analyzeStrategy();
    void onWin(WinEvent e);
}
