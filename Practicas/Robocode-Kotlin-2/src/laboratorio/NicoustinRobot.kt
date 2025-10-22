package laboratorio

import robocode.*
import estrategias.Estrategia
import estrategas.Estratega
import estrategas.EstrategaChill

class NicoustinRobot(
    private val estratega: Estratega = EstrategaChill()
) : JuniorRobot() {
    private var estrategia: Estrategia

    init {
        estrategia = estratega.chooseStrategy(this)
    }

    fun setEstrategia(estrategia: Estrategia) {
        this.estrategia = estrategia
        estrategia.setRobot(this)
    }

    private fun updateStrategy() {
        val newStrat = estratega.chooseStrategy(this)
        if (newStrat.javaClass != estrategia.javaClass) {
            setEstrategia(newStrat)
        }
    }

    override fun run() {
        updateStrategy()
        estrategia.runB(this)
    }

    override fun onScannedRobot() {
        updateStrategy()
        estrategia.onScannedRobot()
    }

    override fun onHitByBullet() {
        updateStrategy()
        estrategia.onHitByBullet()
    }

    override fun onHitWall() {
        updateStrategy()
        estrategia.onHitWall()
    }
}