package estrategias;

import laboratorio.NicoustinRobot;
import robocode.WinEvent;

public class EstrategiaDefensiva implements Estrategia{

    private NicoustinRobot robot;
    private int moveDirection = 1;
    private boolean circling = true;
    /*La idea en este caso es que la estrategía sea defensiva enfocandonos
    más en la movilidad que en el ataque. La idea es dar vueltas disparando
    solo cuando en nuestro sensor detectemos enemigos, pero no perseguiremos.
    Disparos de "advertencia" con poco gasto energetico.

    Run contiene todo el comportamiento predeterminado.
    * */
    public void run() {
        this.runB(this.robot);
    }

    @Override
    public void runB(NicoustinRobot robot) {
        this.robot = robot;
        robot.setColors(2, 14, 4); // Azul, blanco, verde
        
        System.out.println("🛡️ DEFENSIVA: Movimiento circular y evasión");
        
        // MOVIMIENTO DEFENSIVO - Inspirado en SpinBot pero más conservador
        while(true) {
            if(circling) {
                // Movimiento circular conservador
                robot.ahead(60);
                robot.turnRight(30 * moveDirection);
            } else {
                // Movimiento errático cuando hay peligro
                robot.turnAheadLeft(40, 45);
            }
            
            // ESCANEO DEFENSIVO
            robot.turnGunRight(90);
        }
    }

    /*Como se establecio anteriormente, en el caso de encontrarnos con
     * un robot en nuestro escaner, se disparará y se seguira adelante*/
    @Override
    public void onScannedRobot() {
        // DISPARO CONSERVADOR
        robot.turnGunTo(robot.scannedAngle);
        
        // Firepower conservador pero efectivo
        double firepower = 1.0;
        if(robot.scannedDistance < 100 && robot.energy > 30) {
            firepower = 2.0;  // Un poco más de poder si está cerca
        }
        robot.fire(firepower);
        
        // EVASIÓN INTELIGENTE
        if(robot.scannedDistance < 150) {
            // Enemigo cerca - activar movimiento evasivo
            circling = false;
            robot.turnBackLeft(80, 90 - robot.scannedBearing);
        } else {
            // Enemigo lejos - mantener movimiento circular
            circling = true;
        }
        
        System.out.println("🛡️ DEFENSIVA: Enemigo detectado, evadiendo");
    }


    /*La estrategia de hacer donas o dar vueltas seguira siendo la misma
    * hasta que nuestra energia se encuentre por debajo del 15%, a esa energia
    * optaremos por una estrategia más desesperada*/
    @Override
    public void onHitByBullet() {
        if(robot.energy <= 15){
            analyzeStrategy();
        }

        // EVASIÓN MÁXIMA
        circling = false;  // Activar modo evasivo
        moveDirection *= -1;  // Cambiar dirección
        
        // Movimiento evasivo perpendicular
        robot.turnAheadLeft(100, 90 - robot.hitByBulletBearing);
        
        System.out.println("💥 DEFENSIVA: ¡Impacto! Evasión máxima");
    }

    /*Intentaremos evitar tocar las paredes, asi que dudo que esta función
    * posea mucho desarrollo.*/
    @Override
    public void onHitWall() {
        // REBOTE DEFENSIVO
        moveDirection *= -1;
        robot.back(40);
        robot.turnRight(135 * moveDirection);  // Giro diagonal evasivo
        robot.ahead(30);
        
        System.out.println("🧱 DEFENSIVA: Rebote evasivo");
    }

    @Override
    public void setRobot(NicoustinRobot robot) {
        this.robot = robot;
    }

    @Override
    public void analyzeStrategy() {
        if (robot.energy <= 25 && robot.others <= 3) {
            // Caso más extremo: poca vida y pocos enemigos
            robot.setEstrategia(new EstrategiaGenocida());
            System.out.println("🔥 ÚLTIMA OPORTUNIDAD - Modo genocida!");
        } else if (robot.energy < 30 && robot.others >= 4) {
            // Vida muy baja y muchos enemigos → desesperado
            robot.setEstrategia(new EstrategiaDesesperada());
            System.out.println("😰 Situación crítica - Modo desesperado!");
        } else if (robot.energy >= 60) {
            // Buena energía → volvemos al ataque
            robot.setEstrategia(new EstrategiaAgresiva());
            System.out.println("⚡ Energía recuperada - ¡AL ATAQUE!");
        }
    }

    @Override
    public void onWin(WinEvent e) {
        // Victoria defensiva elegante
        for(int i = 0; i < 3; i++) {
            robot.turnLeft(120);
            robot.turnGunRight(180);
            robot.turnRight(120);
            robot.turnGunLeft(180);
        }
    }
}
