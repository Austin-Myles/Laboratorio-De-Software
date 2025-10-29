package estrategas

import laboratorio.NicoustinRobot
import estrategias.Estrategia

interface Estratega {
    fun chooseStrategy(robot: NicoustinRobot): Estrategia
}

