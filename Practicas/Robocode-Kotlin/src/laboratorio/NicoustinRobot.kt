package laboratorio

import robocode.*
import estrategias.Estrategia
import estrategias.StrategyEvaluator
import estrategias.StrategyMapper

class NicoustinRobot() : JuniorRobot() {
    private var estrategia : Estrategia? = null
    private var stategyEvaluator : StrategyEvaluator? = null
    private var currentSituationKey : String = "start"

    init {
        stategyEvaluator = StrategyEvaluator(this)
        estrategia = StrategyMapper.getStrategyForSituation(this)
        currentSituationKey = StrategyMapper.getCurrentSituation()
    }

    constructor(estrategia: Estrategia, evaluator: StrategyEvaluator) : this() {
        this.stategyEvaluator = evaluator
        setEstrategia(estrategia)
    }

    fun setEstrategia(estrategia: Estrategia) {
        this.estrategia = estrategia
        estrategia.setRobot(this)
    }

    fun setStrategyEvaluator(evaluator: StrategyEvaluator) {
        this.stategyEvaluator = evaluator
    }

    private fun updateStrategy(){
        if(estrategia != null && stategyEvaluator != null){
            val newSituationKey = stategyEvaluator!!.evaluateStrategy()

            if(stategyEvaluator!!.shouldChangeStrategy(currentSituationKey, newSituationKey)){
                out.println("NICOUSTIN: Cambiando estrategia '$currentSituationKey' -> '$newSituationKey'")

                currentSituationKey = newSituationKey
                StrategyMapper.updateSituation(newSituationKey)
                estrategia = StrategyMapper.getStrategyForSituation(this)
            }
        }
    }
    override fun run() {
        updateStrategy()
        estrategia?.runB(this)
    }

    /**
     * Evento: Robot enemigo detectado
     */
    override fun onScannedRobot() {
        updateStrategy()
        estrategia?.onScannedRobot()
    }

    /**
     * Evento: Impacto de bala recibido
     */
    override fun onHitByBullet() {
        updateStrategy()
        estrategia?.onHitByBullet()
    }

    /**
     * Evento: Colisión con pared
     */
    override fun onHitWall() {
        updateStrategy()
        estrategia?.onHitWall()
    }
}