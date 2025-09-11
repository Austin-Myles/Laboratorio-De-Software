package estrategas;

import estrategias.Estrategia;
import estrategias.FieldDetector;
import laboratorio.NicoustinRobot;
import robocode.WinEvent;

public class EstrategaChill implements Estratega {

    private NicoustinRobot robot;
    
    public Estrategia chooseStrategy(NicoustinRobot robot, String situationKey) {
        if (robot.others > 3) {
            return EstrategiaWalls.getInstance();
        } else {
            return EstrategiaAntiWalls.getInstance();
        }
    }

    /**
     * Estrategia Walls como clase interna
     * Se mueve por el perímetro del campo con el cañón hacia adentro
     * Implementa patrón Singleton
     */
    private class EstrategiaWalls implements Estrategia {
        
        // Singleton instance
        private static EstrategiaWalls instance;
        
        private NicoustinRobot robot;
        private boolean peek = false; // No girar si hay un robot ahí
        private int moveAmount = 0; // Cuánto moverse
        
        // Constructor privado para singleton
        private EstrategiaWalls() {
        }
        
        // Método getInstance para obtener la única instancia
        public static EstrategiaWalls getInstance() {
            if (instance == null) {
                instance = new EstrategiaWalls();
            }
            return instance;
        }
        
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

        @Override
        public String getSituationKey() {
            return "Estrategia Walls";
        }
    }

    /**
     * Estrategia Anti-Walls como clase interna
     * Counter específico para robots tipo Walls usando emboscadas en esquinas
     * Implementa patrón Singleton
     */
    public static class EstrategiaAntiWalls implements Estrategia {
        
        // Singleton instance
        private static EstrategiaAntiWalls instance;
        
        private NicoustinRobot robot;
        private int currentCorner = 0; // 0=arriba-izq, 1=arriba-der, 2=abajo-der, 3=abajo-izq
        private boolean inPosition = false;
        private int waitTurns = 0;
        private boolean targetDetected = false;
        
        // Constructor privado para singleton
        private EstrategiaAntiWalls() {
        }
        
        // Método getInstance para obtener la única instancia
        public static EstrategiaAntiWalls getInstance() {
            if (instance == null) {
                instance = new EstrategiaAntiWalls();
            }
            return instance;
        }
        
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
                case 0: // Arriba-Izquierda
                    robot.turnTo(315); // Noroeste
                    robot.ahead(cornerDistance);
                    break;
                case 1: // Arriba-Derecha
                    robot.turnTo(45); // Noreste
                    robot.ahead(cornerDistance);
                    break;
                case 2: // Abajo-Derecha
                    robot.turnTo(135); // Sureste
                    robot.ahead(cornerDistance);
                    break;
                case 3: // Abajo-Izquierda
                    robot.turnTo(225); // Suroeste
                    robot.ahead(cornerDistance);
                    break;
            }
            
            inPosition = true;
            waitTurns = 0;
            System.out.println("🎯 ANTI-WALLS: Posición de emboscada " + corner + " alcanzada");
        }
        
        /**
         * Lógica de emboscada en esquina
         */
        private void cornerAmbush() {
            // Girar lentamente para escanear el perímetro
            robot.turnGunRight(10);
            
            waitTurns++;
            
            // Si llevamos mucho tiempo sin detectar enemigos, cambiar de esquina
            if (waitTurns > 50 && !targetDetected) {
                System.out.println("🎯 ANTI-WALLS: Cambiando posición de emboscada");
                currentCorner = (currentCorner + 1) % 4;
                inPosition = false;
                targetDetected = false;
                waitTurns = 0;
            }
            
            // Movimiento defensivo ocasional para evitar ser blanco fácil
            if (waitTurns % 20 == 0) {
                robot.ahead(20);
                robot.back(20);
            }
        }
        
        @Override
        public void onScannedRobot() {
            targetDetected = true;
            
            // Disparo de precisión - más potente ya que esperamos al objetivo
            robot.fire(3);
            
            // Movimiento evasivo después del disparo
            if (robot.scannedDistance < 100) {
                // Enemigo muy cerca - retroceder
                robot.back(50);
                robot.turnRight(90);
            } else {
                // Enemigo lejano - perseguir ligeramente
                robot.ahead(30);
            }
            
            System.out.println("🎯 ANTI-WALLS: Objetivo detectado a distancia " + robot.scannedDistance + " - Disparando");
        }
        
        @Override
        public void onHitByBullet() {
            // Movimiento evasivo agresivo
            robot.turnRight(90);
            robot.ahead(100);
            robot.turnLeft(45);
            
            // Cambiar de esquina si nos están atacando mucho
            if (waitTurns > 10) {
                System.out.println("🎯 ANTI-WALLS: Bajo fuego - Reposicionando");
                currentCorner = (currentCorner + 2) % 4; // Esquina opuesta
                inPosition = false;
                waitTurns = 0;
            }
            
            System.out.println("🎯 ANTI-WALLS: Impacto recibido - Maniobra evasiva");
        }
        
        @Override
        public void onHitWall() {
            // Si tocamos pared, ajustar posición
            robot.back(30);
            robot.turnRight(45);
            robot.ahead(20);
            
            System.out.println("🎯 ANTI-WALLS: Contacto con pared - Reajustando emboscada");
        }
        
        @Override
        public void setRobot(NicoustinRobot robot) {
            this.robot = robot;
        }

        @Override
        public String evalStrat() {
            return null;
        }
        
        @Override
        public void onWin(WinEvent e) {
            System.out.println("🏆 ANTI-WALLS: ¡Caza exitosa completada!");
            
            // Celebración de cazador - círculos de victoria
            for(int i = 0; i < 8; i++) {
                robot.turnRight(45);
                robot.ahead(25);
            }
        }

        @Override
        public String getSituationKey() {
            return "Estrategia Anti-Walls";
        }
    }
}