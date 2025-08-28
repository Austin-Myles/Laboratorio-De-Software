package laboratorio;
import estrategias.Estrategia;
import robocode.*;


public class LaboRobot extends JuniorRobot
{
    private Estrategia estrategia;

    public LaboRobot(Estrategia estrategia) {
        this.estrategia = estrategia;
        estrategia.setRobot(this);
    }

	@Override	
	public void run() {
        estrategia.run(this);
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	@Override
	public void onScannedRobot() {
		estrategia.onScannedRobot();
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
		estrategia.onHitByWall();
	}	
}