package estrategias

import laboratorio.NicoustinRobot
import laboratorio.turnToCardinal
import laboratorio.adjustFromWall
import laboratorio.celebrate
import laboratorio.smartMove
import laboratorio.smartTurn
import laboratorio.smartScan
import robocode.WinEvent

class EstrategiaWalls : Estrategia {

    private var robot: NicoustinRobot? = null
    private var peek: Boolean = false
    private var moveAmount: Int = 0
    
    // Clase anidada para manejar la configuración inicial
    inner class InitializationHandler {
        fun setupColors() {
            robot?.setColors(0, 0, 14)
        }
        
        fun detectField() {
            FieldDetector.detectFieldSize(robot!!)
        }
        
        fun setupMovement() {
            moveAmount = FieldDetector.getMaxFieldSize()
            peek = false
        }
        
        fun positionRobot() {
            robot?.turnToCardinal()
            robot?.ahead(moveAmount)
        }
        
        fun setupGun() {
            peek = true
            robot?.turnGunRight(90)
            robot?.turnRight(90)
        }
    }
    
    // Clase anidada para manejar el movimiento perimetral
    inner class PerimeterMovement {
        fun executeMovementCycle() {
            // Usar función local con lambda para el ciclo de movimiento
            val movementCycle: () -> Unit = {
                peek = true
                robot?.smartMove(moveAmount) { peek }
                peek = false
                robot?.smartTurn(90) { !peek }
            }
            
            movementCycle()
        }
        
        fun shouldContinueMoving(): Boolean = true // Siempre continuar en Walls
    }
    
    // Clase anidada para manejar eventos de combate
    inner class CombatHandler {
        fun handleEnemyDetected() {
            robot?.fire(2.0)
            println("WALLS: Enemigo en perímetro - Disparando (peek: $peek)")
        }
        
        fun handleHitReceived() {
            println("🧱 WALLS: Impacto recibido - Manteniendo patrulla")
        }
        
        fun handleWallContact() {
            robot?.adjustFromWall()
            println("🧱 WALLS: Contacto con pared - Ajustando posición perimetral")
        }
    }
    
    private val initHandler = InitializationHandler()
    private val movementHandler = PerimeterMovement()
    private val combatHandler = CombatHandler()

    override fun runB(robot: NicoustinRobot) {
        this.robot = robot

        // Función local para inicialización completa
        fun initializeStrategy() {
            initHandler.setupColors()
            println("WALLS: Iniciando patrulla perimetral")
            initHandler.detectField()
            initHandler.setupMovement()
            println("WALLS: Campo detectado '${FieldDetector.getFieldWidth()}' x '${FieldDetector.getFieldHeight()}'")
            initHandler.positionRobot()
            initHandler.setupGun()
        }
        
        // Función local para el loop principal con lambda
        fun runMainLoop() {
            val mainLoop: () -> Unit = {
                while (movementHandler.shouldContinueMoving()) {
                    movementHandler.executeMovementCycle()
                }
            }
            mainLoop()
        }

        initializeStrategy()
        runMainLoop()
    }

    override fun onScannedRobot() {
        combatHandler.handleEnemyDetected()
    }

    override fun onHitByBullet() {
        combatHandler.handleHitReceived()
    }

    override fun onHitWall() {
        combatHandler.handleWallContact()
    }

    override fun setRobot(robot: NicoustinRobot) {
        this.robot = robot
    }

    override fun onWin(event: WinEvent) {
        println("🏆 WALLS: ¡Victoria perimetral!")

        // Usar función local con lambda para celebración
        val celebrationAction: () -> Unit = {
            robot?.celebrate()
        }
        
        celebrationAction()
    }
}