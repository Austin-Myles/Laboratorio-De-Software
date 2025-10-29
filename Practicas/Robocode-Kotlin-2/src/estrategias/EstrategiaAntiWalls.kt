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
import laboratorio.moveTowardsCorner
import laboratorio.isNearCorner
import laboratorio.smartScan
import robocode.WinEvent
import java.lang.System.out
import kotlin.math.*

class EstrategiaAntiWalls : Estrategia {

    private lateinit var robot: NicoustinRobot
    private var currentCorner: Int = 0
    private var inPosition: Boolean = false
    private var waitTurns: Int = 0
    private var targetDetected: Boolean = false
    
    // Clase anidada para manejar el posicionamiento en esquinas
    inner class CornerManager {
        fun getCornerEnum(cornerIndex: Int): Corner {
            return when (cornerIndex) {
                0 -> Corner.TOP_RIGHT
                1 -> Corner.TOP_LEFT
                2 -> Corner.BOTTOM_LEFT
                3 -> Corner.BOTTOM_RIGHT
                else -> Corner.TOP_RIGHT
            }
        }
        
        fun moveToCorner(cornerIndex: Int) {
            val cornerEnum = getCornerEnum(cornerIndex)
            
            // Usar función local con lambda para movimiento inteligente
            val moveCondition: (Corner) -> Boolean = { corner ->
                !robot.isNearCorner(corner, 50)
            }
            
            robot.moveTowardsCorner(cornerEnum, moveCondition)
            inPosition = true
            waitTurns = 0
            println("ANTI-WALLS: En posición esquina '$cornerIndex' - Esperando target")
        }
        
        fun changeCorner() {
            currentCorner = (currentCorner + 1) % 4
            inPosition = false
            targetDetected = false
            println("ANTI-WALLS: Cambiando a esquina '$currentCorner'")
        }
    }
    
    // Clase anidada para manejar la emboscada
    inner class AmbushHandler {
        fun executeAmbush() {
            waitTurns++
            
            // Usar función local con lambda para escaneo inteligente
            val scanAction: () -> Unit = {
                if (!targetDetected) {
                    robot.turnGunLeft(10)
                }
            }
            
            robot.smartScan(scanAction)
            
            // Usar función local para validar cambio de esquina
            val shouldChangeCorner: () -> Boolean = {
                waitTurns > RobotConstants.MAX_WAIT_TURNS
            }
            
            if (shouldChangeCorner()) {
                cornerManager.changeCorner()
            }
            
            // Usar función local para ajuste periódico
            val shouldAdjust: () -> Boolean = {
                waitTurns % RobotConstants.ADJUSTMENT_INTERVAL == 0
            }
            
            if (shouldAdjust()) {
                adjustPosition()
            }
        }
        
        private fun adjustPosition() {
            robot.adjustPosition()
        }
    }
    
    // Clase anidada para manejar el combate
    inner class CombatManager {
        fun handleEnemyDetected() {
            val distance: Int = robot.scannedDistance
            val bearing: Int = robot.scannedBearing

            targetDetected = true

            robot.turnGunTo(robot.scannedAngle)
            robot.fireByDistance(distance)

            // Usar función local con lambda para reacción a enemigo cercano
            val closeRangeReaction: () -> Unit = {
                robot.turnRight(robot.scannedBearing + 180)
                robot.ahead(50)
                robot.turnRight(robot.scannedBearing)
            }
            
            if (distance < RobotConstants.CLOSE_RANGE) {
                closeRangeReaction()
            }
        }
        
        fun handleHitReceived() {
            println("🎯 ANTI-WALLS: ¡Detectado! Cambiando posición")
            robot.retreatFromHit()
            cornerManager.changeCorner()
        }
        
        fun handleWallContact() {
            robot.adjustFromWall()
            println("🎯 ANTI-WALLS: Ajuste de pared")
        }
    }
    
    private val cornerManager = CornerManager()
    private val ambushHandler = AmbushHandler()
    private val combatManager = CombatManager()

    override fun runB(robot: NicoustinRobot) {
        this.robot = robot

        // Función local para inicialización
        fun initializeStrategy() {
            robot.setColors(6, 0, 14)
            println("ANTI-WALLS: Iniciando caza de robots perimetrales")
            FieldDetector.detectFieldSize(robot)
            println("ANTI-WALLS: Campo '${FieldDetector.getFieldWidth()}' x '${FieldDetector.getFieldHeight()}' detectado")
        }
        
        // Función local para el loop principal con lambda
        fun runMainLoop() {
            val mainLoop: () -> Unit = {
                while (true) {
                    if (!inPosition) {
                        cornerManager.moveToCorner(currentCorner)
                    } else {
                        ambushHandler.executeAmbush()
                    }
                }
            }
            mainLoop()
        }

        initializeStrategy()
        runMainLoop()
    }

    override fun onScannedRobot() {
        combatManager.handleEnemyDetected()
    }

    override fun onHitByBullet() {
        combatManager.handleHitReceived()
    }

    override fun onHitWall() {
        combatManager.handleWallContact()
    }

    override fun setRobot(robot: NicoustinRobot) {
        this.robot = robot
    }

    override fun onWin(event: WinEvent) {
        println("🏆 ANTI-WALLS: ¡Victoria táctica!")

        // Usar función local con lambda para celebración
        val celebrationAction: () -> Unit = {
            robot.celebrateWithGun()
        }
        
        celebrationAction()
    }
}