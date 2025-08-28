package estrategias;

import laboratorio.LaboRobot;
import robocode.JuniorRobot;
import robocode.ScannedRobotEvent;
import robocode.WinEvent;
import robocode.util.Utils;

import java.awt.*;

public class EstrategiaAgresiva implements Estrategia{

    private LaboRobot robot;
    double anguloDisparo;

    /*La idea en este caso es que la estrategía sea ofensiva pero tampoco
    teniendo el descuido de desperidiciar enegia de manera ineficiente dado
    que los disparos desperdician energia. Por lo demás

    Run contiene todo el comportamiento predeterminado.


    * */
    public void run() {
    }

    @Override
    public void runB(LaboRobot robot) {
        // Elegimos los colores
        this.robot.setColors(22,1 , 11);

        // Loopeamos su actividad común.
        while (true){
            robot.turnGunRight(360);  // siempre buscando al enemigo
            robot.ahead(100);
            robot.turnRight(30);
        }
    }

    /*
     * Al escanear un robot se apuntará y se quedará quieto trackeandolo
     * con la función hitRobotBearing(). acto seguido le disparará hasta
     * recibir daños con 1 de energia.
     * */
    @Override
    public void onScannedRobot(ScannedRobotEvent e) {
        //Hay que arreglar las funciones porque no agarran bien...
        anguloDisparo = Utils.normalRelativeAngleDegrees(e.getBearing() + (this.robot.heading - this.robot.gunHeading));
        this.robot.turnGunRight((int)(anguloDisparo));
        this.robot.fire(1);
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
        if(this.robot.energy <= 45){
            //Hacemos el cambio de estrategia.
            analyzeStrategy();
        }

        int anguloGolpe = this.robot.hitByBulletBearing;
        this.robot.turnGunTo(anguloGolpe);
        this.robot.fire(1);
    }

    /*
    * Una logica similar a la del onHitByBullet en la cual obtendremos
    * el angulo donde el robot se golpeo con la pared mediante
    * hitWallBearing(), en este caso haremos que el robot gire para evitar
    * que nuevamente se golpee contra la pared. */
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

    /*
    * Se usaria mas que nada para cuando el robot cumple las condiciones
    * especificas, por ejemplo tener poca vida u que hayan pocos robots
    * en el mapa.*/
    @Override
    public void analyzeStrategy() {
        /*
        if (this.robot.energy <= 25 && this.robot.others <= 3) {
            // Caso más extremo: poca vida y pocos enemigos
            this.robot.setEstrategia(new EstrategiaGenocida());
            System.out.println("Jean Claude Van Dam en... Matar o Morir.");
        } else if (this.robot.energy < 30 && this.robot.others >= 4) {
            // Vida muy baja y muchos enemigos → desesperado
            this.robot.setEstrategia(new EstrategiaDesesperada());
            System.out.println("Energía muy baja, cambiando a estrategia Desesperada");
        } else if (this.robot.energy <= 45) {
            // Vida relativamente baja → defensivo
            this.robot.setEstrategia(new EstrategiaDefensiva());
            System.out.println("Energía baja, cambiando a estrategia Defensiva");
        }
        */
    }


    /*Baile de la victoria*/
    @Override
    public void onWin(WinEvent e) {
        this.robot.back(20);
        this.robot.turnGunRight(360);
        this.robot.ahead(20);
        this.robot.turnGunLeft(360);
    }

}
