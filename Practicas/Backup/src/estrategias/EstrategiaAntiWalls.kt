package estrategias

import laboratorio.NicoustinRobot
import java.lang.reflect.Field
import kotlin.math.max


class EstrategiaAntiWalls : Estrategia{

    private lateinit var robot : NicoustinRobot
    private var currentCorner : Int = 0
    private var inPosition: Boolean = false
    private var waitTurns: Int = 0
    private var targetDetected : Boolean = false

    override fun runB(robot: NicoustinRobot) {
        this.robot = robot

        // Seteamos colores
        robot.setColors(6, 0, 14)

        println("ANTI-WALLS: Iniciando caza de robots perimetrales")

        // Detectar tamaño del campo para posicionamiento preciso
        FieldDetector.detectFieldSize(robot)
        println("ANTI-WALLS: Campo '${FieldDetector.getFieldWidth()}' x '${FieldDetector.getFieldHeight()}' detectado")

        while (true){
            if(!inPosition){
                moveToCorner(currentCorner)
            }
            else{
                cornetAmbush()
            }
        }
    }

    private fun moveToCorner(corner: Int){
        val fieldWidth : Int = FieldDetector.getFieldWidth()
        val fieldHeight : Int = FieldDetector.getFieldHeight()
        val cornerDistance = max(fieldWidth, fieldHeight) / 2

        when (corner){
            0 -> {robot.turnLeft((315 - robot.heading).toInt())
                robot.ahead(cornerDistance)
                robot.turnRight((135 - robot.heading).toInt())
                //break??
            }
            1 -> {robot.turnLeft((225 - robot.heading).toInt())
                robot.ahead(cornerDistance)
                robot.turnRight((225 - robot.heading).toInt())
                //break??
            }
            2 -> {robot.turnLeft((135 - robot.heading).toInt())
                robot.ahead(cornerDistance)
                robot.turnRight((315 - robot.heading).toInt())
                //break??
            }
            3 -> {robot.turnLeft((45 - robot.heading).toInt())
                robot.ahead(cornerDistance)
                robot.turnRight((45 - robot.heading).toInt())
                //break??
            }
        }

        inPosition = true
        waitTurns = 0
        println("ANTI-WALLS: En posición esquina '$corner' - Esperando target")
    }

    private fun cornetAmbush(){
        waitTurns++

        if(!targetDetected){
            robot.turnGunLeft(10)
        }

        if(waitTurns > 50){
            changeCorner()
        }

        if(waitTurns % 20 == 0){
            adjustPosition()
        }
    }

    private fun changeCorner(){
        currentCorner = (currentCorner + 1) % 4
        inPosition = false
        targetDetected = false
        println("ANTI-WALLS: Cambiando a esquina '$currentCorner'")
    }

    private fun adjustPosition(){
        robot.ahead(20)
        robot.back(10)
    }

    override fun onScannedRobot() {
        var distance : Double = robot.scannedDistance
        var bearing : Double = robot.scannedBearing

        targetDetected = true

        if(distance < 200){
            robot.turnGunTo(robot.scannedAngle)
            robot.fire(3.0)
            println("ANTI-WALLS: ¡Emboscada exitosa! Target a '$distance' px")
        }
        else if (distance < 400){
            robot.turnGunTo(robot.scannedAngle)
            robot.fire(2.0)
            println("ANTI-WALLS: ¡Disparo de seguimiento a '$distance' px!")
        }

        if(distance < 100){
            robot.turnRight(robot.scannedBearing + 180)
            robot.ahead(50.0)
            robot.turnRight(robot.scannedBearing)
        }
    }

    override fun onHitByBullet() {
        println("🎯 ANTI-WALLS: ¡Detectado! Cambiando posición")

        robot.turnRight(robot.hitByBulletBearing + 90)
        robot.ahead(100)

        changeCorner()
    }

    override fun onHitWall() {
        robot.back(30)
        robot.turnRight(45.0)
        println("🎯 ANTI-WALLS: Ajuste de pared")
    }

    override fun setRobot(robot: NicoustinRobot) {
        this.robot = robot
    }

    override fun onWin(e: WinEvent) {
        println("🏆 ANTI-WALLS: ¡Victoria táctica!")

        // Celebración tipo Walls - movimiento cuadrado
        repeat(4) {
            robot.turnGunRight(90)
            robot.ahead(25.0)
        }
    }
}