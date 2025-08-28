package estrategias;

import laboratorio.LaboRobot;
import robocode.ScannedRobotEvent;
import robocode.WinEvent;
import robocode.util.Utils;

import java.awt.*;

public class EstrategiaAgresiva extends JuniorRobot implements Estrategia{

    double anguloDisparo;

    /*La idea en este caso es que la estrategía sea ofensiva pero tampoco
    teniendo el descuido de desperidiciar enegia de manera ineficiente dado
    que los disparos desperdician energia. Por lo demás

    Run contiene todo el comportamiento predeterminado.


    * */

    @Override
    public void run() {
        // Elegimos los colores
        setBodyColor(new Color(279, 82, 45));
        setGunColor(new Color(150, 145, 165));
        setRadarColor(new Color(200, 200, 70));
        setScanColor(Color.white);
        setBulletColor(Color.red);

        // Loopeamos
        while (true){
            break;
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
        anguloDisparo = Utils.normalRelativeAngleDegrees(e.getBearing() + (robot.heading() - robot.getRadarHeading()));
        robot.turnGunRight(anguloDisparo);
        robot.fire(1);
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
            //Hacemos el cambio de estrategia.
            analyzeStrategy();
        }

        int anguloGolpe = robot.hitByBulletBearing;
        robot.turnGunTo(anguloGolpe);
        robot.fire(1);
    }

    /*
    * Una logica similar a la del onHitByBullet en la cual obtendremos
    * el angulo donde el robot se golpeo con la pared mediante
    * hitWallBearing(), en este caso haremos que el robot gire para evitar
    * que nuevamente se golpee contra la pared. */
    @Override
    public void onHitWall() {
        int anguloAct = robot.heading;
        int anguloNuevo = (anguloAct + 180) % 360;

        robot.back(20);
        robot.turnTo(anguloNuevo);
        robot.ahead(10);
    }

    @Override
    public void setRobot(LaboRobot robot) {

    }

    /*
    * Se usaria mas que nada para cuando el robot cumple las condiciones
    * especificas, por ejemplo tener poca vida u que hayan pocos robots
    * en el mapa.*/
    @Override
    public void analyzeStrategy() {

    }

    /*Baile de la victoria*/
    @Override
    public void onWin(WinEvent e) {

    }

}
