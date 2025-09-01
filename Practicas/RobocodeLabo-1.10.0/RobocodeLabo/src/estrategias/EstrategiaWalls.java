package estrategias;

import laboratorio.NicoustinRobot;
import robocode.WinEvent;

/**
 * Estrategia idéntica a sample.Walls
 * Se mueve por el perímetro del campo con el cañón hacia adentro
 */
public class EstrategiaWalls implements Estrategia {
    
    private NicoustinRobot robot;
    private boolean peek = false; // No girar si hay un robot ahí
    private int moveAmount = 0; // Cuánto moverse
    
    @Override
    public void runB(NicoustinRobot robot) {
        this.robot = robot;
        
        // Colores EXACTOS como Walls original
        robot.setColors(0, 0, 14); // black, black, orange
        
        System.out.println("🧱 WALLS: Iniciando patrulla perimetral");
        
        // Detectar tamaño del campo primero
        FieldDetector.detectFieldSize(robot);
        
        // Inicialización EXACTA como Walls original
        // Ahora usamos el tamaño real del campo detectado
        moveAmount = FieldDetector.getMaxFieldSize(); // Tamaño real del campo
        peek = false;
        
        System.out.println("🧱 WALLS: Campo detectado " + FieldDetector.getFieldWidth() + "x" + FieldDetector.getFieldHeight());
        
        // Posicionamiento inicial EXACTO como Walls
        // turnLeft(getHeading() % 90)
        robot.turnLeft((int)(robot.heading % 90));
        
        // Moverse hasta encontrar la pared
        robot.ahead(moveAmount); // Esto debería causar onHitWall
        
        // Configuración inicial del cañón EXACTA
        peek = true;
        robot.turnGunRight(90);
        robot.turnRight(90);
        
        // Loop principal EXACTO como Walls original
        while(true) {
            // Mirar antes de movernos cuando ahead() termine
            peek = true;
            // Moverse por la pared - usar tamaño real del campo
            robot.ahead(moveAmount); // Tamaño exacto del campo
            // No mirar ahora
            peek = false;
            // Girar a la siguiente pared
            robot.turnRight(90);
        }
    }
    
    @Override
    public void onScannedRobot() {
        // Comportamiento EXACTO de Walls original
        robot.fire(2);
        
        // Nota: scan() se llama automáticamente cuando el robot se mueve
        // En el original se llama manualmente aquí si peek=true
        // para generar otro scan event si hay robot en la siguiente pared
        // En JuniorRobot el escaneo es automático, pero mantenemos la lógica
        if (peek) {
            // En JuniorRobot no podemos llamar scan() manualmente
            // pero el comportamiento se mantiene automáticamente
        }
        
        System.out.println("🧱 WALLS: Enemigo en perímetro - Disparando (peek: " + peek + ")");
    }
    
    @Override
    public void onHitByBullet() {
        // Walls original no tiene lógica específica para onHitByBullet
        // Mantiene su patrón de movimiento
        System.out.println("🧱 WALLS: Impacto recibido - Manteniendo patrulla");
    }
    
    @Override
    public void onHitWall() {
        // Comportamiento Walls: ajustarse cuando toca pared
        // Retroceder ligeramente y continuar patrón
        robot.back(20);
        
        // Asegurar que seguimos el patrón perimetral
        if (!peek) {
            // Si no estamos en modo peek, girar a la siguiente pared
            robot.turnRight(90);
        }
        
        System.out.println("🧱 WALLS: Contacto con pared - Ajustando posición perimetral");
    }
    
    // Método que simula onHitRobot de Walls original
    private void onHitRobotLogic() {
        // Lógica EXACTA de Walls original
        
        // Si está enfrente nuestro, retroceder un poco
        if (robot.scannedBearing > -90 && robot.scannedBearing < 90) {
            robot.back(100);
        } 
        // Si está detrás nuestro, avanzar un poco
        else {
            robot.ahead(100);
        }
        
        System.out.println("🧱 WALLS: Colisión con robot - Ajustando posición");
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
        System.out.println("🏆 WALLS: ¡Victoria perimetral!");
        
        // Celebración tipo Walls - movimiento cuadrado
        for(int i = 0; i < 4; i++) {
            robot.ahead(50);
            robot.turnRight(90);
        }
    }
}