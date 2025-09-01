package estrategias;

import laboratorio.NicoustinRobot;
import robocode.WinEvent;

public class EstrategiaGenocida implements Estrategia{

    private NicoustinRobot robot;
    /*
    * Ultimo caso, simplemente acabaremos con todo lo que existe en el mapa
    * no queremos dejar nada con vida, solo optaremos por esta estrategia
    * cuando no queden menos de 3 robots, sin piedad, que corra sangre.
    */
    public void run() {
        // Método legacy - no usado
    }

    @Override
    public void runB(NicoustinRobot robot) {
        this.robot = robot;
        robot.setColors(6, 6, 0); // Rojo total, negro
        
        System.out.println("🔥 GENOCIDA: ¡SIN PIEDAD, SIN RETIRADA!");
        
        // CAZA ACTIVA EXTREMA - Más agresivo que agresiva
        while(true) {
            // Movimiento de cazador
            robot.ahead(200);  // Máxima velocidad
            robot.turnRight(60);  // Giros amplios para cubrir terreno
            
            // Escaneo continuo de 360°
            robot.turnGunRight(360);
        }
    }

    /*Atacamos sin piedad y no le sacamos el ojo de encima*/
    @Override
    public void onScannedRobot() {
        // EXTERMINIO INMEDIATO
        robot.turnGunTo(robot.scannedAngle);
        robot.turnTo(robot.scannedBearing);  // Cuerpo y cañón hacia enemigo
        
        // FUEGO MÁXIMO SIEMPRE
        robot.fire(3.0);  // Siempre máxima potencia, sin conservar
        
        // CARGA KAMIKAZE
        robot.ahead((int)robot.scannedDistance);  // Ir directamente al enemigo
        
        System.out.println("🔥 GENOCIDA: ¡EXTERMINIO EN PROGRESO!");
    }

    /*Solo movemos el cuerpo teniendo cuidado de no comernos la pared*/
    @Override
    public void onHitByBullet() {
        // CONTRAATAQUE INMEDIATO
        robot.turnGunTo(robot.hitByBulletBearing);
        robot.turnTo(robot.hitByBulletBearing);
        
        // Disparo de venganza
        robot.fire(3);
        
        // Carga directa hacia el atacante
        robot.ahead(50);
    }

    /*Nuevamente evitaremos seguir golpeandonos las paredes eligiendo
    * un angulo para que el robot se desplace adecuadamente*/
    @Override
    public void onHitWall() {
        // Rebote agresivo para continuar la caza
        robot.back(30);
        robot.turnTo((robot.heading + 90) % 360);  // giro rápido
        robot.ahead(40);
    }

    @Override
    public void setRobot(NicoustinRobot robot) {
        this.robot = robot;
    }

    @Override
    public void analyzeStrategy() {
        // NO HAY VUELTA ATRÁS - luchamos hasta el final
        System.out.println("🔥 MODO GENOCIDA: SIN RETIRADA, SIN RENDICIÓN!");
    }

    @Override
    public void onWin(WinEvent e) {
        // Victoria total - salvas de celebración
        System.out.println("🔥🏆 GENOCIDIO COMPLETADO - DOMINACIÓN TOTAL!");
        
        for(int i = 0; i < 8; i++) {
            robot.turnGunRight(45);
            robot.fire(0.1);  // salvas de victoria
        }
        
        // Danza de guerra final
        robot.turnLeft(360);
        robot.turnRight(360);
    }
}
