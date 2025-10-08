package estrategias

import laboratorio.NicoustinRobot
import robocode.WinEvent

interface Estrategia {
    fun runB(robot: NicoustinRobot)
    fun onScannedRobot()
    fun onHitByBullet()
    fun onHitWall()
    fun setRobot(robot: NicoustinRobot)
    fun onWin(event: WinEvent)
}