package estrategias

import laboratorio.NicoustinRobot
import laboratorio.Corner
import laboratorio.RobotConstants
import laboratorio.moveToCorner
import laboratorio.adjustPosition
import laboratorio.retreatFromHit
import laboratorio.adjustFromWall
import laboratorio.fireByDistance
import laboratorio.celebrateWithGun
import robocode.WinEvent
import java.lang.System.out
import kotlin.math.*

class EstrategiaAntiWalls : Estrategia {

    private lateinit var robot: NicoustinRobot
    private var currentCorner: Int = 0
    private var inPosition: Boolean = false
    private var waitTurns: Int = 0
    private var targetDetected: Boolean = false

    override fun runB(robot: NicoustinRobot) {
        this.robot = robot

        // Seteamos colores
        robot.setColors(6, 0, 14)

        println("ANTI-WALLS: Iniciando caza de robots perimetrales")

        // Detectar tamaño del campo para posicionamiento preciso
        FieldDetector.detectFieldSize(robot)
        println("ANTI-WALLS: Campo '${FieldDetector.getFieldWidth()}' x '${FieldDetector.getFieldHeight()}' detectado")

        while (true) {
            if (!inPosition) {
                moveToCorner(currentCorner)
            } else {
                cornerAmbush()
            }
        }
    }

    private fun moveToCorner(corner: Int) {
        val cornerEnum = when (corner) {
            0 -> Corner.TOP_RIGHT
            1 -> Corner.TOP_LEFT
            2 -> Corner.BOTTOM_LEFT
            3 -> Corner.BOTTOM_RIGHT
            else -> Corner.TOP_RIGHT
        }
        
        robot.moveToCorner(cornerEnum)
        inPosition = true
        waitTurns = 0
        println("ANTI-WALLS: En posición esquina '$corner' - Esperando target")
    }

    private fun cornerAmbush() {
        waitTurns++

        if (!targetDetected) {
            robot.turnGunLeft(10)
        }

        if (waitTurns > 50) {
            changeCorner()
        }

        if (waitTurns % 20 == 0) {
            adjustPosition()
        }
    }

    private fun changeCorner() {
        currentCorner = (currentCorner + 1) % 4
        inPosition = false
        targetDetected = false
        println("ANTI-WALLS: Cambiando a esquina '$currentCorner'")
    }

    private fun adjustPosition() {
        robot.adjustPosition()
    }

    override fun onScannedRobot() {
        val distance: Int = robot.scannedDistance
        val bearing: Int = robot.scannedBearing

        targetDetected = true

        robot.turnGunTo(robot.scannedAngle)
        robot.fireByDistance(distance)

        if (distance < RobotConstants.CLOSE_RANGE) {
            robot.turnRight(robot.scannedBearing + 180)
            robot.ahead(50)
            robot.turnRight(robot.scannedBearing)
        }
    }

    override fun onHitByBullet() {
        println("🎯 ANTI-WALLS: ¡Detectado! Cambiando posición")

        robot.retreatFromHit()
        changeCorner()
    }

    override fun onHitWall() {
        robot.adjustFromWall()
        println("🎯 ANTI-WALLS: Ajuste de pared")
    }

    override fun setRobot(robot: NicoustinRobot) {
        this.robot = robot
    }

    override fun onWin(event: WinEvent) {
        println("🏆 ANTI-WALLS: ¡Victoria táctica!")

        // Celebración tipo Walls - movimiento cuadrado
        robot.celebrateWithGun()
    }
}