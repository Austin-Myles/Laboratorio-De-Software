package estrategias;

import laboratorio.LaboRobot;
import robocode.ScannedRobotEvent;
import robocode.WinEvent;
import robocode.util.Utils;

public class EstrategiaDefensiva implements Estrategia{

    private LaboRobot robot;
    double anguloDisparo;
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
    public void runB(LaboRobot robot) {
        // Elegimos los colores
        this.robot.setColors(11,11 , 11);

        // Loopeamos su actividad común. (No se si tiene que estar en un loop o que, lo dejo asi)
        robot.ahead(50);  // siempre buscando al enemigo
        robot.turnRight(45);
    }

    /*Como se establecio anteriormente, en el caso de encontrarnos con
     * un robot en nuestro escaner, se disparará y se seguira adelante*/
    @Override
    public void onScannedRobot(ScannedRobotEvent e) {
        //Hay que arreglar las funciones porque no agarran bien...
        double anguloAbsoluto = this.robot.heading + e.getBearing();
        double anguloGiro = Utils.normalRelativeAngleDegrees(anguloAbsoluto - this.robot.gunHeading);
        this.robot.turnGunRight((int)(anguloGiro));
        this.robot.fire(1);
    }


    /*La estrategia de hacer donas o dar vueltas seguira siendo la misma
    * hasta que nuestra energia se encuentre por debajo del 15%, a esa energia
    * optaremos por una estrategia más desesperada*/
    @Override
    public void onHitByBullet() {
        if(robot.energy <= 15){
            //Hacemos el cambio de estrategia.
            analyzeStrategy();
        }

        int anguloAct = robot.heading;
        int anguloNuevo = (anguloAct + 180) % 360;
        robot.turnTo(anguloNuevo);
        robot.ahead(10);
    }

    /*Intentaremos evitar tocar las paredes, asi que dudo que esta función
    * posea mucho desarrollo.*/
    @Override
    public void onHitWall() {
        int anguloAct = this.robot.heading;
        int anguloNuevo = (anguloAct + 180) % 360;
        this.robot.back(20);
        this.robot.turnTo(anguloNuevo);
        this.robot.ahead(10);
    }

    @Override
    public void setRobot(LaboRobot robot) {
        this.robot = robot;
    }

    @Override
    public void analyzeStrategy() {
        if (this.robot.energy <= 25 && this.robot.others <= 3) {
            // Caso más extremo: poca vida y pocos enemigos
            this.robot.setEstrategia(new EstrategiaGenocida());
            System.out.println("Jean Claude Van Dam en... Matar o Morir.");
        } else if (this.robot.energy < 30 && this.robot.others >= 4) {
            // Vida muy baja y muchos enemigos → desesperado
            this.robot.setEstrategia(new EstrategiaDesesperada());
            System.out.println("Energía muy baja, cambiando a estrategia Desesperada");
        } else if (this.robot.energy >= 45) {
            // Vida relativamente baja → defensivo
            this.robot.setEstrategia(new EstrategiaAgresiva());
            System.out.println("Recargamos milagrosamente, al ataque!!");
        }
    }

    @Override
    public void onWin(WinEvent e) {
        this.robot.turnLeft(360);
        this.robot.turnGunRight(360);
        this.robot.turnGunLeft(360);
        this.robot.turnRight(360);
    }
}
