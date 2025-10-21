package laboratorio

import robocode.*
import estrategias.Estrategia
import estrategas.Estratega
import estrategas.EstrategaChill

class NicoustinRobot(
    private val estratega: Estratega = EstrategaChill()
) : JuniorRobot() {
    
    // Clase anidada para manejar eventos del robot
    inner class EventHandler {
        fun handleScannedRobot() {
            updateStrategy()
            estrategia.onScannedRobot()
        }
        
        fun handleHitByBullet() {
            updateStrategy()
            estrategia.onHitByBullet()
        }
        
        fun handleHitWall() {
            updateStrategy()
            estrategia.onHitWall()
        }
        
        fun handleWin(event: WinEvent) {
            estrategia.onWin(event)
        }
    }
    
    private var estrategia: Estrategia
    private val eventHandler = EventHandler()
    
    // Función local para validar estrategia
    private fun isValidStrategy(strategy: Estrategia): Boolean {
        return strategy.javaClass != estrategia.javaClass
    }
    
    // Función local para aplicar estrategia con lambda
    private fun applyStrategy(strategy: Estrategia, action: (Estrategia) -> Unit) {
        action(strategy)
    }

    init {
        estrategia = estratega.chooseStrategy(this)
    }

    fun setEstrategia(estrategia: Estrategia) {
        this.estrategia = estrategia
        estrategia.setRobot(this)
    }

    private fun updateStrategy() {
        val newStrat = estratega.chooseStrategy(this)
        
        // Usar función local con lambda
        if (isValidStrategy(newStrat)) {
            applyStrategy(newStrat) { strategy ->
                setEstrategia(strategy)
            }
        }
    }

    override fun run() {
        updateStrategy()
        estrategia.runB(this)
    }

    override fun onScannedRobot() {
        eventHandler.handleScannedRobot()
    }

    override fun onHitByBullet() {
        eventHandler.handleHitByBullet()
    }

    override fun onHitWall() {
        eventHandler.handleHitWall()
    }
    
    override fun onWin(event: WinEvent) {
        eventHandler.handleWin(event)
    }
}