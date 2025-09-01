package estrategias;

import laboratorio.NicoustinRobot;

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
        double startHeading = robot.heading;
        int wallHitCount = 0;
        int totalMovement = 0;
        
        // Alinearse hacia un cardinal (Norte)
        robot.turnLeft((int)(robot.heading % 90));
        
        // Ir hacia una pared para establecer punto de referencia
        robot.ahead(1000); // onHitWall nos detendrá
        wallHitCount++;
        
        // Medir una dimensión (por ejemplo, ancho)
        robot.turnRight(90); // Girar 90° hacia la derecha
        
        // Contar movimiento hasta la siguiente pared
        int moveDistance = 0;
        while (wallHitCount < 2 && moveDistance < 1000) {
            try {
                robot.ahead(50); // Movimientos pequeños para medir
                moveDistance += 50;
                totalMovement += 50;
            } catch (Exception e) {
                // Probablemente hit wall
                break;
            }
        }
        
        // Estimar dimensiones basado en el movimiento
        // Esto es una aproximación - los valores exactos dependen del campo específico
        if (totalMovement < 300) {
            fieldWidth = 400; fieldHeight = 400; // Campo pequeño
        } else if (totalMovement < 500) {
            fieldWidth = 600; fieldHeight = 400; // Campo mediano
        } else if (totalMovement < 700) {
            fieldWidth = 800; fieldHeight = 600; // Campo grande
        } else {
            fieldWidth = 1000; fieldHeight = 800; // Campo muy grande
        }
        
        // Volver a posición inicial aproximada
        robot.turnLeft(180);
        robot.ahead(100);
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
