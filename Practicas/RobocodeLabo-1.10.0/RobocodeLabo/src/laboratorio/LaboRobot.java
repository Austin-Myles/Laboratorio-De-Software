package laboratorio;
import estrategias.Estrategia;
import robocode.*;


public class LaboRobot extends JuniorRobot
{
    private Estrategia estrategia;

    public LaboRobot(Estrategia estrategia) {
        setEstrategia(estrategia);
    }

    public void setEstrategia(Estrategia estrategia) {
        this.estrategia = estrategia;
        estrategia.setRobot(this);
    }

	@Override	
	public void run() {
        estrategia.runB(this);
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		estrategia.onScannedRobot(e);
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	@Override
	public void onHitByBullet() {
        estrategia.analyzeStrategy();
		estrategia.onHitByBullet();
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	@Override
	public void onHitWall() {
		estrategia.onHitWall();
	}

}