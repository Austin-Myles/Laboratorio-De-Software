package estrategias;

import laboratorio.LaboRobot;

public class EstrategiaAgresiva implements Estrategia {


    /*La idea en este caso es que la estrategía sea Ofensiva pero tampoco
    teniendo el descuido de desperidiciar enegia de manera ineficiente dado
    que los disparos desperdician energia. Por lo demás

    Run contiene todo el comportamiento predeterminado.


    * */

    @Override
    public void run() {

    }

    /*
     * Al escanear un robot se apuntara y se quedará quieto trackeandolo
     * con la función hitRobotBearing(). acto seguido le disparará hasta
     * recibir daños
     * */
    @Override
    public void onScannedRobot() {
        fire(1);
    }

    /*
    * En el caso de ser lastimado podria optarse por realizar un desplazamiento
    * dependiendo de la dirección donde este ubicado el robot, esto se calcula
    * con la función hitByBulletBearing() y realizar un movimiento que no
    * sea en paralelo a donde fue golpeado
    *
    * En cada momento al ser golpeado si se reduce demasiado la vida despues
    * de un golpe, seria una buena idea analizar los HP del robot para
    * realizar un cambio de estrategia.*/
    @Override
    public void onHitByBullet() {
        back(10);
    }

    /*
    * Una logica similar a la del onHitByBullet en la cual obtendremos
    * el angulo donde el robot se golpeo con la pared mediante
    * hitWallBearing(), en este caso haremos que el robot gire para evitar
    * que nuevamente se golpee contra la pared. */
    @Override
    public void onHitWall() {
        back(20);
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

}
