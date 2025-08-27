package laboratorio;

import robocode.JuniorRobot;


public class Momo extends JuniorRobot
{

	@Override	
	public void run() {

		setColors(brown, white, white, red, brown);
		ahead(100);
		turnGunRight(360);
		back(100);
		turnGunRight(360);
		
	}

	/**
	 * onScannedRobot: What to do when you see another robot
     *
     * Apenas detecta a un robot en el scanner, giramos la mira a su dirección
     * Podriamos disparar con un offset de unos pixeles tambien...
	 */
	@Override
	public void onScannedRobot() {
		fire(1);
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
     *
     * En el caso de que nos pegue es ir para atras si la pared no esta cerca, si no hay pared adelante
     * ir adelante, sino girar a derecha o izq para evitar más daño
	 */
	@Override
	public void onHitByBullet() {
		back(10);
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
     * Centrarse en el angulo correcto para retroceder y evitar nuevamente chocarse contra la pared nuevamente.
     *
     *
	 */
	@Override
	public void onHitWall() {
		back(20);
	}	
}