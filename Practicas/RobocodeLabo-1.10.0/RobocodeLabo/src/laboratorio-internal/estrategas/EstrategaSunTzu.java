package estrategas;

import estrategias.Estrategia;
import estrategias.FieldDetector;
import laboratorio.NicoustinRobot;
import robocode.WinEvent;

public class EstrategaSunTzu implements Estratega {
    
    public Estrategia chooseStrategy(NicoustinRobot robot, String situationKey) {
        if(robot.energy < 50) {
            return new EstrategiaAntiWalls(); // Estrategia agresiva cuando está débil
        } else {
            return new EstrategiaWalls(); // Estrategia defensiva cuando está fuerte
        }
    }

    /**
     * Estrategia Walls como clase interna - versión Sun Tzu (más táctica)
     * Se mueve por el perímetro pero con más inteligencia defensiva
     */
    public static class EstrategiaWalls implements Estrategia {
        
        private NicoustinRobot robot;
        private boolean peek = false;
        private int moveAmount = 0;
        private int patrolDirection = 1; // 1 = clockwise, -1 = counter-clockwise
        private boolean underFire = false;
        
        @Override
        public void runB(NicoustinRobot robot) {
            this.robot = robot;
            
            // Colores Sun Tzu - más discretos
            robot.setColors(8, 8, 0); // Gris/Gris/Negro - camuflaje
            
            System.out.println("⚔️ SUN TZU WALLS: Iniciando defensa perimetral táctica");
            
            // Detectar tamaño del campo
            FieldDetector.detectFieldSize(robot);
            moveAmount = FieldDetector.getMaxFieldSize();
            
            System.out.println("⚔️ SUN TZU WALLS: Campo " + FieldDetector.getFieldWidth() + "x" + FieldDetector.getFieldHeight() + " analizado");
            
            // Posicionamiento inicial más inteligente
            robot.turnLeft((int)(robot.heading % 90));
            robot.ahead(moveAmount);
            
            peek = true;
            robot.turnGunRight(90);
            robot.turnRight(90 * patrolDirection);
            
            // Loop principal con táctica Sun Tzu
            while(true) {
                // Patrulla táctica con cambios de dirección
                tacticalPatrol();
                
                // Evaluar si cambiar dirección de patrulla
                if (underFire) {
                    patrolDirection *= -1; // Cambiar dirección si bajo fuego
                    underFire = false;
                    System.out.println("⚔️ SUN TZU WALLS: Cambiando patrón de patrulla");
                }
            }
        }
        
        private void tacticalPatrol() {
            peek = true;
            
            // Movimiento variable para ser impredecible
            int moveDistance = moveAmount + (int)(Math.random() * 20 - 10);
            robot.ahead(moveDistance);
            
            peek = false;
            robot.turnRight(90 * patrolDirection);
        }
        
        @Override
        public void onScannedRobot() {
            // Disparo más inteligente basado en distancia
            if (robot.scannedDistance < 100) {
                robot.fire(3); // Disparo fuerte a corta distancia
            } else if (robot.scannedDistance < 300) {
                robot.fire(2); // Disparo medio a distancia media
            } else {
                robot.fire(1); // Disparo conservador a larga distancia
            }
            
            System.out.println("⚔️ SUN TZU WALLS: Enemigo a distancia " + robot.scannedDistance + " - Fuego táctico");
        }
        
        @Override
        public void onHitByBullet() {
            underFire = true;
            
            // Maniobra evasiva Sun Tzu
            robot.back(30);
            robot.turnRight(45 * patrolDirection);
            robot.ahead(50);
            
            System.out.println("⚔️ SUN TZU WALLS: Bajo fuego - Ejecutando maniobra evasiva");
        }
        
        @Override
        public void onHitWall() {
            robot.back(20);
            if (!peek) {
                robot.turnRight(90 * patrolDirection);
            }
            System.out.println("⚔️ SUN TZU WALLS: Pared contactada - Ajustando formación");
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
            System.out.println("🏆 SUN TZU WALLS: ¡Victoria táctica lograda!");
            
            // Celebración militar - formación cuadrada
            for(int i = 0; i < 4; i++) {
                robot.ahead(40);
                robot.turnRight(90);
            }
        }

        @Override
        public String getSituationKey() {
            return "Sun Tzu Walls Táctica";
        }
    }

    /**
     * Estrategia Anti-Walls como clase interna - versión Sun Tzu (más agresiva)
     * "Conoce a tu enemigo y conócete a ti mismo"
     */
    public static class EstrategiaAntiWalls implements Estrategia {
        
        private NicoustinRobot robot;
        private boolean huntMode = false;
        private int lastEnemyBearing = 0;
        private double lastEnemyDistance = 0;
        private int aggressionLevel = 1; // 1-3, aumenta con baja energía
        
        @Override
        public void runB(NicoustinRobot robot) {
            this.robot = robot;
            
            // Colores agresivos Sun Tzu
            robot.setColors(6, 6, 6); // Rojo intenso - máxima agresión
            
            System.out.println("⚔️ SUN TZU ANTI-WALLS: Iniciando caza táctica");
            
            FieldDetector.detectFieldSize(robot);
            
            // Calcular nivel de agresión basado en energía
            calculateAggressionLevel();
            
            System.out.println("⚔️ SUN TZU ANTI-WALLS: Nivel de agresión " + aggressionLevel + "/3");
            
            while(true) {
                if (huntMode && lastEnemyDistance > 0) {
                    // Perseguir al último enemigo detectado
                    huntLastKnownPosition();
                } else {
                    // Patrulla agresiva buscando enemigos
                    aggressivePatrol();
                }
                
                // Recalcular agresión periódicamente
                calculateAggressionLevel();
            }
        }
        
        private void calculateAggressionLevel() {
            if (robot.energy < 20) {
                aggressionLevel = 3; // Máxima agresión - nada que perder
            } else if (robot.energy < 50) {
                aggressionLevel = 2; // Alta agresión
            } else {
                aggressionLevel = 1; // Agresión moderada
            }
        }
        
        private void huntLastKnownPosition() {
            // Moverse hacia la última posición conocida del enemigo
            robot.turnTo(lastEnemyBearing);
            
            int huntDistance = (int)(lastEnemyDistance * 0.7); // Acercarse pero no demasiado
            robot.ahead(huntDistance * aggressionLevel);
            
            // Escaneo circular rápido
            for(int i = 0; i < 8; i++) {
                robot.turnGunRight(45);
                // Si encontramos algo, onScannedRobot se activará
            }
            
            huntMode = false; // Reset hunt mode
        }
        
        private void aggressivePatrol() {
            // Patrulla en espiral hacia el centro
            int spiralDistance = 100 / aggressionLevel; // Más agresivo = espiral más cerrada
            
            robot.ahead(spiralDistance);
            robot.turnRight(90 - (aggressionLevel * 10)); // Giro más cerrado con más agresión
            
            // Escaneo continuo
            robot.turnGunRight(30 * aggressionLevel);
        }
        
        @Override
        public void onScannedRobot() {
            huntMode = true;
            lastEnemyBearing = robot.scannedBearing;
            lastEnemyDistance = robot.scannedDistance;
            
            // Fuego basado en nivel de agresión y distancia
            int firepower = calculateFirepower();
            robot.fire(firepower);
            
            // Movimiento post-disparo basado en agresión
            if (aggressionLevel >= 2) {
                // Alta agresión - avanzar hacia el enemigo
                robot.turnTo(robot.scannedBearing);
                robot.ahead(50 * aggressionLevel);
            } else {
                // Agresión moderada - mantener distancia
                if (robot.scannedDistance < 150) {
                    robot.back(30);
                } else {
                    robot.ahead(20);
                }
            }
            
            System.out.println("⚔️ SUN TZU ANTI-WALLS: Enemigo detectado - Agresión nivel " + aggressionLevel + " - Fuego " + firepower);
        }
        
        private int calculateFirepower() {
            if (aggressionLevel == 3) {
                return 3; // Máxima potencia cuando desesperado
            } else if (robot.scannedDistance < 100) {
                return 3; // Fuego máximo a corta distancia
            } else if (robot.scannedDistance < 300) {
                return 2; // Fuego medio a distancia media
            } else {
                return 1; // Fuego conservador a larga distancia
            }
        }
        
        @Override
        public void onHitByBullet() {
            // Respuesta basada en nivel de agresión
            if (aggressionLevel >= 2) {
                // Alta agresión - contraatacar
                robot.turnTo(robot.scannedBearing);
                robot.ahead(100);
                robot.fire(3);
            } else {
                // Agresión moderada - evadir
                robot.turnRight(90);
                robot.ahead(100);
            }
            
            System.out.println("⚔️ SUN TZU ANTI-WALLS: Impacto recibido - Respuesta agresión nivel " + aggressionLevel);
        }
        
        @Override
        public void onHitWall() {
            // Usar la pared como punto de apoyo táctico
            robot.back(50);
            robot.turnRight(135); // Giro táctico
            robot.ahead(75);
            
            System.out.println("⚔️ SUN TZU ANTI-WALLS: Pared alcanzada - Reposición táctica");
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
            System.out.println("🏆 SUN TZU ANTI-WALLS: ¡El enemigo ha sido derrotado!");
            
            // Celebración de conquistador
            for(int i = 0; i < 3; i++) {
                robot.turnRight(120);
                robot.ahead(60);
            }
        }

        @Override
        public String getSituationKey() {
            return "Sun Tzu Anti-Walls Agresiva";
        }
    }
}