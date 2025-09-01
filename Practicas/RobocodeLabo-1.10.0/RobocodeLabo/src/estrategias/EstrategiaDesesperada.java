package estrategias;

import laboratorio.NicoustinRobot;
import robocode.WinEvent;

public class EstrategiaDesesperada implements Estrategia{

    private NicoustinRobot robot;
    /*
    La idea en este caso es que la estrategía sea lo más cobarde, haciendo que
    el robot solo trace el perímetro del área, en el caso de que trackee un
    robot frente suyo solo se dará la vuelta.

    * */
    public void run() {
        this.runB(this.robot);
    }

    @Override
    public void runB(NicoustinRobot robot) {
        this.robot = robot;
        robot.setColors(10, 0, 6); // Gris, negro, rojo
        
        System.out.println("😰 DESESPERADA: Supervivencia a toda costa");
        
        // MOVIMIENTO ERRÁTICO EXTREMO - Inspirado en Crazy
        int direction = 1;
        while (true){
            // Movimiento caótico para ser imposible de seguir
            robot.turnAheadRight(80, 60 * direction);
            robot.turnBackLeft(50, 45 * direction);
            robot.turnAheadLeft(70, 90 * direction);
            
            // Cambiar dirección constantemente
            direction *= -1;
            
            // Escaneo mínimo - solo para supervivencia
            robot.turnGunRight(120);
        }
    }

    /*Al detectar un robot simplemente dara un giro de 180° y seguira
     * trazando el perimetro del cuadrado.*/
    @Override
    public void onScannedRobot() {
        // DISPARO MÍNIMO solo si es absolutamente necesario
        if(robot.energy > 8 && robot.scannedDistance < 100) {
            robot.turnGunTo(robot.scannedAngle);
            robot.fire(0.5);  // Mínima energía
        }
        
        // HUIDA INMEDIATA Y CAÓTICA
        robot.turnTo((robot.scannedBearing + 180 + (int)(Math.random() * 60)) % 360);
        robot.ahead(120);  // Huir rápido
        
        System.out.println("😰 DESESPERADA: ¡HUYENDO!");
    }

    /*Seguiremos escapando.*/
    @Override
    public void onHitByBullet() {
        if(robot.others < 4) {
            this.analyzeStrategy();
        }
        
        // Movimiento errático de supervivencia
        robot.turnAheadLeft(80, 135 - robot.hitByBulletBearing);
    }

    /*Mepa que este esta al pedo*/
    @Override
    public void onHitWall() {
        // Rebote desesperado - movimiento impredecible
        robot.back(35);
        robot.turnTo((robot.heading + 200) % 360);  // giro irregular
        robot.ahead(25);
    }


    @Override
    public void setRobot(NicoustinRobot robot) {
        this.robot = robot;
    }

    @Override
    public void analyzeStrategy() {
        if (robot.energy <= 15 || robot.others <= 2) {
            // Caso más extremo: última esperanza
            robot.setEstrategia(new EstrategiaGenocida());
            System.out.println("🔥 TODO O NADA - ÚLTIMA BALA!");
        } else if (robot.energy >= 40) {
            // Milagrosamente recuperamos energía
            robot.setEstrategia(new EstrategiaDefensiva());
            System.out.println("😌 Respiramos un poco - Modo defensivo");
        }
    }

    @Override
    public void onWin(WinEvent e) {
        // Celebración de superviviente milagroso
        for(int i = 0; i < 10; i++) {
            robot.turnGunLeft(36);
            robot.turnRight(36);
        }
        System.out.println("🎉 ¡SOBREVIVÍ CONTRA TODAS LAS PROBABILIDADES!");
    }
}
