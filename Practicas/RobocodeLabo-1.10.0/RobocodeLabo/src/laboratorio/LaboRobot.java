package laboratorio;
import estrategias.EstrategiaAgresiva;
import robocode.*;


public class LaboRobot extends JuniorRobot
{
    private Strategy strat;

    public LaboRobot(Strategy strat) {
        this.strat = strat;
        strat.setRobot(this);
    }

	@Override	
	public void run() {
        strat.run(this);
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	@Override
	public void onScannedRobot() {
		strat.onScannedRobot();
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	@Override
	public void onHitByBullet() {
        strat.analyzeStrategy();
		strat.onHitByBullet();
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	@Override
	public void onHitWall() {
		strat.onHitByWall();
	}	
}