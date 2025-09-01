package estrategias;

import laboratorio.NicoustinRobot;
import robocode.WinEvent;

/**
 * Estrategia Anti-Walls: Counter específico para robots tipo Walls
 * Usa el patrón "Corner Ambush" - emboscadas en esquinas
 */
public class EstrategiaAntiWalls implements Estrategia {
    
    private NicoustinRobot robot;
    private int currentCorner = 0; // 0=arriba-izq, 1=arriba-der, 2=abajo-der, 3=abajo-izq
    private boolean inPosition = false;
    private int waitTurns = 0;
    private boolean targetDetected = false;
    
    @Override
    public void runB(NicoustinRobot robot) {
        this.robot = robot;
        
        // Colores distintivos para counter
        robot.setColors(6, 0, 14); // Rojo/Negro/Naranja - cazador
        
        System.out.println("🎯 ANTI-WALLS: Iniciando caza de robots perimetrales");
        
        // Detectar tamaño del campo para posicionamiento preciso
        FieldDetector.detectFieldSize(robot);
        System.out.println("🎯 ANTI-WALLS: Campo " + FieldDetector.getFieldWidth() + "x" + FieldDetector.getFieldHeight() + " detectado");
        
        while(true) {
            if (!inPosition) {
                // Ir a la esquina objetivo
                moveToCorner(currentCorner);
            } else {
                // Estamos en posición - vigilar y disparar
                cornerAmbush();
            }
        }
    }
    
    /**
     * Mueve el robot a la esquina especificada usando tamaños reales del campo
     */
    private void moveToCorner(int corner) {
        int fieldWidth = FieldDetector.getFieldWidth();
        int fieldHeight = FieldDetector.getFieldHeight();
        int cornerDistance = Math.max(fieldWidth, fieldHeight) / 2; // Distancia para llegar cerca de esquina
        
        switch(corner) {
            case 0: // Esquina superior izquierda
                robot.turnLeft((int)(315 - robot.heading)); // Noreste
                robot.ahead(cornerDistance);  // Distancia calculada
                robot.turnRight((int)(135 - robot.heading)); // Apuntar hacia perímetro
                break;
            case 1: // Esquina superior derecha  
                robot.turnLeft((int)(225 - robot.heading)); // Noroeste
                robot.ahead(cornerDistance);
                robot.turnRight((int)(225 - robot.heading)); // Apuntar hacia perímetro
                break;
            case 2: // Esquina inferior derecha
                robot.turnLeft((int)(135 - robot.heading)); // Suroeste
                robot.ahead(cornerDistance);
                robot.turnRight((int)(315 - robot.heading)); // Apuntar hacia perímetro
                break;
            case 3: // Esquina inferior izquierda
                robot.turnLeft((int)(45 - robot.heading));  // Sureste
                robot.ahead(cornerDistance);
                robot.turnRight((int)(45 - robot.heading));  // Apuntar hacia perímetro
                break;
        }
        
        inPosition = true;
        waitTurns = 0;
        System.out.println("🎯 ANTI-WALLS: En posición esquina " + corner + " - Esperando target");
    }
    
    /**
     * Lógica de emboscada desde la esquina
     */
    private void cornerAmbush() {
        waitTurns++;
        
        if (!targetDetected) {
            // Escanear área perimetral
            robot.turnGunLeft(10); // Escaneo lento y preciso
        }
        
        // Si llevamos mucho tiempo sin detectar target, cambiar esquina
        if (waitTurns > 50) {
            changeCorner();
        }
        
        // Pequeños ajustes de posición para mantener ventaja
        if (waitTurns % 20 == 0) {
            adjustPosition();
        }
    }
    
    /**
     * Cambiar a la siguiente esquina estratégicamente
     */
    private void changeCorner() {
        currentCorner = (currentCorner + 1) % 4;
        inPosition = false;
        targetDetected = false;
        System.out.println("🎯 ANTI-WALLS: Cambiando a esquina " + currentCorner);
    }
    
    /**
     * Ajustar posición para mantener ventaja táctica
     */
    private void adjustPosition() {
        // Movimiento mínimo para no perder posición ventajosa
        robot.ahead(20);
        robot.back(10);
    }
    
    @Override
    public void onScannedRobot() {
        double distance = robot.scannedDistance;
        double bearing = robot.scannedBearing;
        
        targetDetected = true;
        
        // Disparar inmediatamente - estamos en emboscada
        if (distance < 200) {
            // Disparo certero a corta distancia
            robot.turnGunTo(robot.scannedAngle);
            robot.fire(3); // Máxima potencia
            System.out.println("🎯 ANTI-WALLS: ¡Emboscada exitosa! Target a " + (int)distance + "px");
        } else if (distance < 400) {
            // Disparo de seguimiento
            robot.turnGunTo(robot.scannedAngle);
            robot.fire(2);
            System.out.println("🎯 ANTI-WALLS: Disparo de seguimiento a " + (int)distance + "px");
        }
        
        // Si el target está muy cerca, ajustar posición
        if (distance < 100) {
            // Retroceder manteniendo el ángulo de disparo
            robot.turnRight(robot.scannedBearing + 180); // Dar la espalda
            robot.ahead(50); // Retroceder
            robot.turnRight(robot.scannedBearing); // Volver a apuntar
        }
    }
    
    @Override
    public void onHitByBullet() {
        // Si nos están disparando, cambiar posición inmediatamente
        System.out.println("🎯 ANTI-WALLS: ¡Detectado! Cambiando posición");
        
        // Escape táctico perpendicular
        robot.turnRight(robot.hitByBulletBearing + 90);
        robot.ahead(100);
        
        // Cambiar a otra esquina
        changeCorner();
    }
    
    @Override
    public void onHitWall() {
        // Si chocamos con pared, ajustar posición
        robot.back(30);
        robot.turnRight(45);
        System.out.println("🎯 ANTI-WALLS: Ajuste de pared");
    }
    
    @Override
    public void setRobot(NicoustinRobot robot) {
        this.robot = robot;
    }
    
    @Override
    public String evalStrat() {
        // La evaluación la hace el robot, no la estrategia individual
        return null;
    }
    
    @Override
    public void onWin(WinEvent e) {
        System.out.println("🏆 ANTI-WALLS: ¡Victoria táctica! Counter exitoso");
        
        // Celebración tipo francotirador
        for(int i = 0; i < 4; i++) {
            robot.turnGunRight(90);
            robot.ahead(25);
        }
    }
}
