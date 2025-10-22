package estrategas

import laboratorio.NicoustinRobot
import estrategias.Estrategia
import estrategias.EstrategiaWalls
import estrategias.EstrategiaAntiWalls

class EstrategaChill : Estratega {
    
    override fun chooseStrategy(robot: NicoustinRobot): Estrategia {
        val enemigos = robot.others
        
        return if (enemigos > 3) {
            EstrategiaWalls()
        } else {
            EstrategiaAntiWalls()
        }
    }
}

