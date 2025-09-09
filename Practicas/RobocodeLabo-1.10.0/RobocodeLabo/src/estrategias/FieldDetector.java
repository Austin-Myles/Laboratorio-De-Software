package RobotGod.estrategias;

import RobotGod.laboratorio.NicoustinRobot;

/**
 * Detector de tamaño del campo de batalla para JuniorRobot
 * Usa exploración por rebote + fallback a tamaños estándar
 */
public class FieldDetector {
    
    private static int fieldWidth = 0;
    private static int fieldHeight = 0;
    private static boolean fieldMeasured = false;
    
    // Tamaños estándar de Robocode como fallback
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;
    
    /**
     * Detecta el tamaño del campo usando exploración por rebote
     */
    public static void detectFieldSize(NicoustinRobot robot) {
        if (fieldMeasured) return;
        
        System.out.println("🔍 DETECTOR: Iniciando detección de campo...");
        
        try {
            // Método de exploración por rebote
            measureByBouncing(robot);
            fieldMeasured = true;
            System.out.println("🔍 DETECTOR: Campo detectado - " + fieldWidth + "x" + fieldHeight);
        } catch (Exception e) {
            // Fallback a tamaños estándar
            fieldWidth = DEFAULT_WIDTH;
            fieldHeight = DEFAULT_HEIGHT;
            fieldMeasured = true;
            System.out.println("🔍 DETECTOR: Usando tamaño estándar - " + fieldWidth + "x" + fieldHeight);
        }
    }
    
    /**
     * Mide el campo usando rebotes contra paredes
     */
    private static void measureByBouncing(NicoustinRobot robot) {
        double measuredWidth = 0;
        double measuredHeight = 0;

        // Alinearse hacia el Norte
        robot.turnLeft((int)(robot.heading % 90));

        // 1️⃣ Medir altura
        robot.ahead(5000); // hasta pared Norte
        robot.turnRight(180);
        robot.ahead(5000); // hasta pared Sur
        measuredHeight = robot.robotY; // posición Y actual ≈ altura total
        robot.turnRight(90);

        // 2️⃣ Medir ancho
        robot.ahead(5000); // hasta pared Este
        robot.turnRight(180);
        robot.ahead(5000); // hasta pared Oeste
        measuredWidth = robot.robotX; // posición X actual ≈ ancho total
        robot.turnRight(90);

        // Normalización (redondear al múltiplo de 100 más cercano)
        fieldWidth = (int) (Math.round(measuredWidth / 100.0) * 100);
        fieldHeight = (int) (Math.round(measuredHeight / 100.0) * 100);

        // Fallback si da un valor poco realista
        if (fieldWidth < 300 || fieldHeight < 300) {
            fieldWidth = DEFAULT_WIDTH;
            fieldHeight = DEFAULT_HEIGHT;
        }
    }
    
    /**
     * Método alternativo usando aproximación inteligente
     */
    public static void smartDetection(NicoustinRobot robot) {
        if (fieldMeasured) return;
        
        // Usar características del robot para estimar
        // JuniorRobot se coloca en posiciones aleatorias al inicio
        // Podemos usar esto como pista
        
        // Movimiento de prueba en múltiples direcciones
        int testDistance = 200;
        int walls_hit = 0;
        
        // Probar Norte
        robot.turnLeft((int)robot.heading); // Ir al Norte
        try {
            robot.ahead(testDistance);
        } catch (Exception e) { walls_hit++; }
        
        // Probar Este  
        robot.turnRight(90);
        try {
            robot.ahead(testDistance);
        } catch (Exception e) { walls_hit++; }
        
        // Estimar tamaño basado en cuántas paredes encontramos
        if (walls_hit >= 2) {
            // Campo pequeño - estamos cerca de una esquina
            fieldWidth = 400; fieldHeight = 400;
        } else if (walls_hit == 1) {
            // Campo mediano
            fieldWidth = 600; fieldHeight = 400;
        } else {
            // Campo grande
            fieldWidth = 800; fieldHeight = 600;
        }
        
        fieldMeasured = true;
        System.out.println("🔍 DETECTOR: Estimación inteligente - " + fieldWidth + "x" + fieldHeight);
    }
    
    /**
     * Getters para las dimensiones detectadas
     */
    public static int getFieldWidth() {
        if (!fieldMeasured) {
            return DEFAULT_WIDTH; // Fallback
        }
        return fieldWidth;
    }
    
    public static int getFieldHeight() {
        if (!fieldMeasured) {
            return DEFAULT_HEIGHT; // Fallback
        }
        return fieldHeight;
    }
    
    public static int getMaxFieldSize() {
        return Math.max(getFieldWidth(), getFieldHeight());
    }
    
    public static boolean isFieldDetected() {
        return fieldMeasured;
    }
    
    /**
     * Reset para testing (opcional)
     */
    public static void reset() {
        fieldMeasured = false;
        fieldWidth = 0;
        fieldHeight = 0;
    }
}
