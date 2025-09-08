package RobotGod.estrategias;

import NicoustinRobot;
import robocode.JuniorRobot;
import robocode.WinEvent;

public interface Estrategia {

    void runB(NicoustinRobot robot);
    void onScannedRobot();
    void onHitByBullet();
    void onHitWall();
    void setRobot(NicoustinRobot robot);
    void onWin(WinEvent e);
    
    // Nuevo método para evaluación de estrategia
    String evalStrat();
}
