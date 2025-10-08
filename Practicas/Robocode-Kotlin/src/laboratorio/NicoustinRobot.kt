package laboratorio

import robocode.*
import estrategias.Estrategia
import estrategas.Estratega
import estrategas.EstrategaChill

class NicoustinRobot() : JuniorRobot() {
    private var estratega: Estratega
    private var estrategia: Estrategia

    init {
        estratega = EstrategaChill()
        estrategia = estratega.chooseStrategy(this)
    }

    constructor(estratega: Estratega) : this() {
        this.estratega = estratega
        setEstrategia(this.estratega.chooseStrategy(this))
    }

    fun setEstrategia(estrategia: Estrategia) {
        this.estrategia = estrategia
        estrategia.setRobot(this)
    }

    fun setEstratega(estratega: Estratega) {
        this.estratega = estratega
    }

    private fun updateStrategy() {
        if (estratega != null) {
            val newStrat = estratega.chooseStrategy(this)
            if (newStrat != null && newStrat.javaClass != estrategia.javaClass) {
                setEstrategia(newStrat)
            }
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