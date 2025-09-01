package estrategias;

import laboratorio.NicoustinRobot;
import robocode.JuniorRobot;
import robocode.WinEvent;

import java.awt.*;

public class EstrategiaAgresiva implements Estrategia{

    private NicoustinRobot robot;
    private boolean movingForward = true;
    private int scanDirection = 1;

    /*La idea en este caso es que la estrategía sea ofensiva pero tampoco
    teniendo el descuido de desperidiciar enegia de manera ineficiente dado
    que los disparos desperdician energia. Por lo demás Run contiene todo el comportamiento predeterminado.
    * */
    public void run() {
        this.runB(this.robot);
    }

    @Override
    public void runB(NicoustinRobot robot) {
        this.robot = robot;
        robot.setColors(6, 0, 11); // Rojo, negro, amarillo
        
        System.out.println("⚔️ AGRESIVA: Ataque continuo y persecución");
        
        // LOOP AGRESIVO - Basado en RamFire + SpinBot
        while(true) {
            // MOVIMIENTO AGRESIVO CONTINUO
            robot.ahead(150);  // Movimiento rápido hacia adelante
            robot.turnRight(45);  // Giros amplios para cubrir área
            
            // ESCANEO AGRESIVO
            robot.turnGunRight(180 * scanDirection);
            scanDirection *= -1;  // Alternar dirección
        }
    }

    /*
     * Al escanear un robot se apuntará y se quedará quieto trackeandolo
     * con la función hitRobotBearing(). acto seguido le disparará hasta
     * recibir daños con 1 de energia.
     * */
    @Override
    public void onScannedRobot() {
        // ATAQUE INMEDIATO Y DIRECTO
        robot.turnGunTo(robot.scannedAngle);
        
        // FUEGO POTENTE - Más agresivo que elite
        double firepower = 3.0;  // Siempre máximo poder
        if(robot.energy < 10) {
            firepower = 1.0;  // Solo conservar si estamos muy mal
        }
        robot.fire(firepower);
        
        // PERSECUCIÓN DIRECTA - Inspirado en RamFire
        robot.turnTo(robot.scannedBearing);
        if(robot.scannedDistance > 100) {
            robot.ahead((int)robot.scannedDistance - 80);  // Acercarse agresivamente
        }
        
        System.out.println("⚔️ AGRESIVA: ¡Al ataque! Enemigo a " + (int)robot.scannedDistance);
    }

    /*
    * En el caso de ser lastimado podría optarse por realizar un desplazamiento
    * dependiendo de la dirección donde esté ubicado el robot, esto se calcula
    * con la función hitByBulletBearing() e iremos a la carga donde fuimos golpeados
    *
    * En cada momento al ser golpeado si se reduce demasiado la vida después
    * de un golpe, sería una buena idea analizar los HP del robot para
    * realizar un cambio de estrategia.*/
    @Override
    public void onHitByBullet() {
        if(robot.energy <= 45){
            analyzeStrategy();
        }
        
        // CONTRAATAQUE AGRESIVO
        robot.turnGunTo(robot.hitByBulletBearing);
        robot.turnTo(robot.hitByBulletBearing);  // Girar cuerpo hacia atacante
        robot.fire(3);  // Disparo potente de venganza
        
        // CARGA DIRECTA - No huir, atacar
        robot.ahead(100);
        
        System.out.println("💥 AGRESIVA: ¡Contraataque feroz!");
    }

    /*
    * Una logica similar a la del onHitByBullet en la cual obtendremos
    * el angulo donde el robot se golpeo con la pared mediante
    * hitWallBearing(), en este caso haremos que el robot gire para evitar
    * que nuevamente se golpee contra la pared. */
    @Override
    public void onHitWall() {
        // REBOTE AGRESIVO
        movingForward = !movingForward;
        if(movingForward) {
            robot.turnRight(90);
            robot.ahead(80);
        } else {
            robot.turnLeft(90);
            robot.back(60);
        }
        
        System.out.println("🧱 AGRESIVA: Rebote táctico");
    }

    @Override
    public void setRobot(NicoustinRobot robot) {
        this.robot = robot;
    }

    /*
    * Se usaria mas que nada para cuando el robot cumple las condiciones
    * especificas, por ejemplo tener poca vida u que hayan pocos robots
    * en el mapa.*/
    @Override
    public void analyzeStrategy() {
        double energy = robot.energy;
        int others = robot.others;
        
        if (energy <= 25 && others <= 3) {
            // Caso más extremo: poca vida y pocos enemigos
            robot.setEstrategia(new EstrategiaGenocida());
            System.out.println("🔥 MODO GENOCIDA ACTIVADO - Sin piedad!");
            return;
        }
        if (energy < 30 && others >= 4) {
            // Vida muy baja y muchos enemigos → desesperado
            robot.setEstrategia(new EstrategiaDesesperada());
            System.out.println("😰 Energía crítica - Modo supervivencia!");
            return;
        }
        if (energy <= 45) {
            // Vida relativamente baja → defensivo
            robot.setEstrategia(new EstrategiaDefensiva());
            System.out.println("🛡️ Energía baja - Cambiando a modo defensivo");
            return;
        }
    }

    /*Baile de la victoria*/
    @Override
    public void onWin(WinEvent e) {
        // Baile de la victoria agresivo
        for(int i = 0; i < 5; i++) {
            robot.turnGunRight(360);
            robot.ahead(20);
            robot.turnGunLeft(360);
            robot.back(20);
        }
    }

}
