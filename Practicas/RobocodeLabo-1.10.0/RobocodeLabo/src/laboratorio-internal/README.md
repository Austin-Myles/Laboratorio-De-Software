# NicoustinRobot - Arquitectura de Clases Internas

Un robot inteligente para Robocode con **estrategias como clases internas** de los estrategas. Esta versión mantiene toda la funcionalidad del robot original pero con una arquitectura más compacta y encapsulada.

## 🚀 Características

- **Arquitectura de Clases Internas**: Las estrategias están definidas como clases internas de cada estratega
- **Sistema de Estrategias Dinámico**: Cambia automáticamente entre estrategias según las condiciones
- **Encapsulación Mejorada**: Cada estratega contiene sus propias implementaciones de estrategias
- **Dos Estrategas Especializados**:
  - `EstrategaChill`: Estratega balanceado (por defecto)
  - `EstrategaSunTzu`: Estratega táctico con lógica más sofisticada

## 🏗️ Arquitectura

### Diferencias con la versión original:

**Versión Original:**
```
NicoustinRobot
    ↓
Estratega → Estrategia (clases separadas)
    ↓
EstrategiaWalls / EstrategiaAntiWalls
```

**Versión de Clases Internas:**
```
NicoustinRobot
    ↓
Estratega
    ├── EstrategiaWalls (clase interna)
    └── EstrategiaAntiWalls (clase interna)
```

### Ventajas de esta arquitectura:

✅ **Encapsulación**: Cada estratega mantiene sus estrategias privadas  
✅ **Menos archivos**: Menos clases Java separadas  
✅ **Cohesión**: Estrategias relacionadas agrupadas en el mismo archivo  
✅ **Especialización**: Cada estratega puede tener implementaciones únicas  

## 📦 Contenido del Paquete

```
laboratorio-internal/
├── 📄 NicoustinRobot.java       # Robot principal
├── 📄 NicoustinRobot.properties # Configuración
├── 📖 README.md                 # Este archivo
├── 🛠️ build.sh                  # Script de compilación
├── 🧹 clean.sh                  # Script de limpieza
├── estrategias/                 # Solo archivos auxiliares
│   ├── Estrategia.java          # Interfaz base
│   └── FieldDetector.java       # Utilidad de detección
└── estrategas/                  # Estrategas con clases internas
    ├── Estratega.java           # Interfaz estratega
    ├── EstrategaChill.java      # Estratega balanceado
    └── EstrategaSunTzu.java     # Estratega táctico
```

## 🎯 Estrategas y sus Estrategias

### EstrategaChill (Balanceado)
- **Lógica**: Basada en número de enemigos (`robot.others`)
- **EstrategiaWalls**: Defensiva, patrulla perimetral estándar
- **EstrategiaAntiWalls**: Ofensiva, emboscadas en esquinas

### EstrategaSunTzu (Táctico)
- **Lógica**: Basada en energía (`robot.energy`)
- **EstrategiaWalls**: Defensiva táctica con patrones impredecibles
- **EstrategiaAntiWalls**: Ofensiva agresiva con niveles de agresión

## 🛠️ Instalación y Uso

### Compilación Automática

```bash
cd laboratorio-internal/
./build.sh                    # Compila automáticamente
# Copia NicoustinRobot.jar a tu instalación de Robocode
```

### Scripts Incluidos

- `./build.sh` - Compila y crea el JAR (incluye clases internas)
- `./clean.sh` - Limpia archivos compilados

## 🔄 Funcionamiento

### Evaluación de Estrategias

El robot evalúa cuándo cambiar estrategias en:
1. **Cada ciclo principal** (`run()`)
2. **Al detectar enemigos** (`onScannedRobot()`)
3. **Al recibir disparos** (`onHitByBullet()`)
4. **Al chocar con paredes** (`onHitWall()`)

### Criterios de Decisión

#### EstrategaChill:
```java
if (robot.others > 3) {
    return new EstrategiaWalls();    // Muchos enemigos → Defensiva
} else {
    return new EstrategiaAntiWalls(); // Pocos enemigos → Ofensiva
}
```

#### EstrategaSunTzu:
```java
if (robot.energy < 50) {
    return new EstrategiaAntiWalls(); // Poca energía → Agresiva
} else {
    return new EstrategiaWalls();     // Mucha energía → Defensiva
}
```

## 🆚 Comparación con Versión Original

| Aspecto | Versión Original | Versión Clases Internas |
|---------|-----------------|-------------------------|
| **Archivos Java** | 10 archivos | 4 archivos |
| **Estrategias** | Clases separadas | Clases internas |
| **Encapsulación** | Básica | Mejorada |
| **Especialización** | Limitada | Alta |
| **Mantenimiento** | Más archivos | Menos archivos |
| **Funcionalidad** | ✅ Completa | ✅ Completa |

## 🎮 Uso en Robocode

1. Compila con `./build.sh`
2. Copia `NicoustinRobot.jar` al directorio `robots/` de Robocode
3. En Robocode, busca `laboratorio.NicoustinRobot`
4. ¡Observa las estrategias especializadas en acción!

## 🔧 Personalización

### Cambiar Estratega por Defecto

Edita `NicoustinRobot.java`:
```java
// Cambiar de EstrategaChill a EstrategaSunTzu
this.estratega = new EstrategaSunTzu();
```

### Añadir Nuevo Estratega

1. Crea nuevo archivo en `estrategas/`
2. Implementa la interfaz `Estratega`
3. Define tus estrategias como clases internas `static`
4. Recompila con `./build.sh`

### Modificar Estrategias Existentes

Edita directamente las clases internas en:
- `EstrategaChill.java` → `EstrategiaWalls` / `EstrategiaAntiWalls`
- `EstrategaSunTzu.java` → `EstrategiaWalls` / `EstrategiaAntiWalls`

## 🏆 Ventajas de esta Arquitectura

1. **Cohesión**: Estrategias relacionadas agrupadas
2. **Especialización**: Cada estratega puede tener comportamientos únicos
3. **Menos archivos**: Arquitectura más limpia
4. **Encapsulación**: Estrategias privadas a cada estratega
5. **Mantenimiento**: Más fácil de mantener y extender

¡Disfruta del combate con arquitectura optimizada! 🤖⚔️