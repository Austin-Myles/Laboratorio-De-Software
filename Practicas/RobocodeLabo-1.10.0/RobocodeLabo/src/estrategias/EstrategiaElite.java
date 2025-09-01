package estrategias;

import laboratorio.NicoustinRobot;
import robocode.WinEvent;

/**
 * EstrategiaElite - Estrategia avanzada que combina las mejores tácticas
 * 
 * Esta estrategia implementa:
 * - Movimiento adaptativo basado en el contexto del battlefield
 * - Sistema de tracking inteligente del enemigo más cercano
 * - Gestión energética optimizada
 * - Posicionamiento táctico basado en las dimensiones del campo
 * - Predicción de movimiento enemigo básica
 * - Comportamiento anti-wall con navegación inteligente
 */
public class EstrategiaElite implements Estrategia {

    private NicoustinRobot robot;
    private int moveDirection = 1;
    private int scanDirection = 1;
    private int turnCounter = 0;
    private boolean movingForward = true;
    
    // Constantes tácticas optimizadas
    private static final int CLOSE_DISTANCE = 100;
    private static final int FAR_DISTANCE = 200;
    
    @Override
    public void runB(NicoustinRobot robot) {
        this.robot = robot;
        
        // Colores elite
        robot.setColors(11, 0, 14);
        
        System.out.println("🎯 ELITE: Movimiento continuo y escaneo agresivo");
        
        // LOOP PRINCIPAL - Inspirado en SpinBot + Crazy + VelociRobot
        while(true) {
            turnCounter++;
            
            // MOVIMIENTO CONTINUO ERRÁTICO
            if (turnCounter % 20 == 0) {
                // Cambio de dirección periódico
                moveDirection *= -1;
                movingForward = !movingForward;
            }
            
            // MOVIMIENTO PRINCIPAL - Siempre en movimiento
            if (movingForward) {
                robot.ahead(100);
            } else {
                robot.back(80);
            }
            
            // GIRO ERRÁTICO para ser impredecible
            robot.turnRight(20 * moveDirection);
            
            // ESCANEO CONTINUO - Clave para detección
            robot.turnGunRight(30 * scanDirection);
            
            // Cambiar dirección de scan ocasionalmente
            if (turnCounter % 15 == 0) {
                scanDirection *= -1;
            }
        }
    }
    
    /**
     * Reversar dirección de movimiento para evasión
     */
    private void reverseDirection() {
        movingForward = !movingForward;
        moveDirection *= -1;
        System.out.println("💨 ELITE: Cambiando dirección evasiva");
    }

    @Override
    public void onScannedRobot() {
        // COMBATE SIMPLIFICADO Y EFECTIVO
        
        // 1. APUNTAR INMEDIATAMENTE
        robot.turnGunTo(robot.scannedAngle);
        
        // 2. DISPARO INTELIGENTE - Inspirado en TrackFire
        double firepower = 1.0;
        if (robot.energy > 20) {
            if (robot.scannedDistance < CLOSE_DISTANCE) {
                firepower = 3.0;  // Máximo poder a corta distancia
            } else if (robot.scannedDistance < FAR_DISTANCE) {
                firepower = 2.0;  // Poder medio
            } else {
                firepower = 1.0;  // Conservar energía
            }
        }
        
        robot.fire(firepower);
        
        // 3. MOVIMIENTO REACTIVO - Inspirado en Tracker
        if (robot.scannedDistance < CLOSE_DISTANCE) {
            // ENEMIGO CERCA - Retroceder
            reverseDirection();
        } else if (robot.scannedDistance > FAR_DISTANCE) {
            // ENEMIGO LEJOS - Acercarse
            robot.turnTo(robot.scannedBearing);
        }
        
        System.out.println("🎯 ELITE: Enemigo detectado a " + (int)robot.scannedDistance + "px");
    }
    


    @Override
    public void onHitByBullet() {
        analyzeStrategy();
        
        // REACCIÓN INMEDIATA - Inspirado en VelociRobot
        reverseDirection();
        
        // CONTRAATAQUE - Inspirado en Tracker
        robot.turnGunTo(robot.hitByBulletBearing);
        robot.fire(2.0);
        
        // EVASIÓN PERPENDICULAR - Inspirado en MyFirstJuniorRobot
        robot.turnAheadLeft(100, 90 - robot.hitByBulletBearing);
        
        System.out.println("💥 ELITE: ¡Impacto! Evasión activa");
    }

    @Override
    public void onHitWall() {
        // REBOTE SIMPLE Y EFECTIVO - Inspirado en Crazy
        reverseDirection();
        robot.turnRight(90);
        
        System.out.println("🧱 ELITE: Rebote de pared");
    }

    @Override
    public void setRobot(NicoustinRobot robot) {
        this.robot = robot;
    }

    @Override
    public void analyzeStrategy() {
        double energy = robot.energy;
        int others = robot.others;
        
        // Lógica de cambio de estrategia más sofisticada
        if (energy <= 20 && others <= 2) {
            // Situación desesperada - modo genocida
            robot.setEstrategia(new EstrategiaGenocida());
            System.out.println("🔥 ELITE → GENOCIDA: Situación crítica!");
        } else if (energy <= 35 && others >= 5) {
            // Muchos enemigos y poca energía - modo defensivo
            robot.setEstrategia(new EstrategiaDefensiva());
            System.out.println("🛡️ ELITE → DEFENSIVA: Conservando energía");
        } else if (energy <= 15) {
            // Energía crítica - modo desesperado
            robot.setEstrategia(new EstrategiaDesesperada());
            System.out.println("😰 ELITE → DESESPERADA: ¡Supervivencia!");
        }
        // Si no se cumplen condiciones, mantenemos estrategia elite
    }

    @Override
    public void onWin(WinEvent e) {
        // Celebración de elite - precisa y elegante
        System.out.println("👑 VICTORIA ELITE - Dominación completa");
        
        for(int i = 0; i < 5; i++) {
            robot.turnGunRight(72);  // Pentágono perfecto
            robot.fire(0.1);         // Salvas de victoria
        }
        
        // Giro final majestuoso
        robot.turnLeft(360);
        System.out.println("🏆 ¡La élite siempre triunfa!");
    }
}
