package laboratorio;
import estrategias.Estrategia;
import robocode.*;


public class NicoustinRobot extends JuniorRobot
{
    private Estrategia estrategia;

    // Constructor predeterminado para Robocode
    public NicoustinRobot() {
        // Inicializamos con estrategia Elite por defecto
        setEstrategia(new estrategias.EstrategiaElite());
    }
    
    // Constructor con estrategia personalizada
    public NicoustinRobot(Estrategia estrategia) {
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
		estrategia.onHitWall();
	}

}