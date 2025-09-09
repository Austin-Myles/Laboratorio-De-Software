# NicoustinRobot - Paquete Independiente

Un robot inteligente para Robocode que implementa un sistema de estrategias dinámico que se adapta según las condiciones de batalla.

## 🚀 Características

- **Sistema de Estrategias Dinámico**: Cambia automáticamente entre estrategias según el número de enemigos
- **Patrón Strategy**: Arquitectura modular y extensible
- **Evaluación Automática**: Analiza la situación de batalla en tiempo real
- **Estrategias Incluidas**:
  - `EstrategiaWalls`: Supervivencia defensiva para inicio de batalla
  - `EstrategiaAntiWalls`: Caza ofensiva para final de batalla

## 📦 Contenido del Paquete

```
laboratorio/
├── NicoustinRobot.java          # Robot principal
├── NicoustinRobot.properties    # Configuración del robot
├── build.sh                     # Script de compilación
├── README.md                    # Este archivo
├── estrategias/                 # Estrategias de combate
│   ├── Estrategia.java
│   ├── EstrategiaWalls.java
│   ├── EstrategiaAntiWalls.java
│   └── FieldDetector.java
└── estrategas/                  # Selectores de estrategia
    ├── Estratega.java
    ├── EstrategaChill.java
    └── EstrategaSunTzu.java
```

## 🛠️ Instalación y Uso

### Prerequisitos

- Java JDK 8 o superior
- Instalación de Robocode
- Terminal/Línea de comandos

### Opción 1: Compilación Automática (Recomendada)

1. **Navega al directorio del paquete:**
   ```bash
   cd laboratorio/
   ```

2. **Ejecuta el script de compilación:**
   ```bash
   # Autodetección de robocode.jar
   ./build.sh
   
   # O especifica la ruta manualmente
   ./build.sh /path/to/robocode/libs
   ```

3. **Instala en Robocode:**
   - Copia `NicoustinRobot.jar` al directorio `robots/` de tu instalación de Robocode
   - O extrae el contenido del JAR directamente en `robots/`

### Scripts Incluidos

- `./build.sh` - Compila y crea el JAR
- `./clean.sh` - Limpia archivos compilados

### Opción 2: Compilación Manual

1. **Compila los archivos Java:**
   ```bash
   javac -cp "/path/to/robocode/libs/robocode.jar:." *.java estrategias/*.java estrategas/*.java
   ```

2. **Crea la estructura de directorios en robots/:**
   ```bash
   mkdir -p /path/to/robocode/robots/laboratorio
   mkdir -p /path/to/robocode/robots/estrategias
   mkdir -p /path/to/robocode/robots/estrategas
   ```

3. **Copia los archivos compilados:**
   ```bash
   cp *.class /path/to/robocode/robots/laboratorio/
   cp *.properties /path/to/robocode/robots/laboratorio/
   cp estrategias/*.class /path/to/robocode/robots/estrategias/
   cp estrategas/*.class /path/to/robocode/robots/estrategas/
   ```


## 🧠 Cómo Funciona

### Arquitectura

```
NicoustinRobot
    ↓
Estratega (EstrategaChill/EstrategaSunTzu)
    ↓
Estrategia (EstrategiaWalls/EstrategiaAntiWalls)
    ↓
Acciones específicas de combate
```

### Flujo de Decisión

1. **Evaluación**: El robot evalúa la situación actual (número de enemigos, posición, etc.)
2. **Selección**: El `Estratega` elige la estrategia más apropiada
3. **Ejecución**: La `Estrategia` seleccionada ejecuta las acciones de combate
4. **Adaptación**: El ciclo se repite continuamente para adaptarse a cambios

### Estrategias

- **EstrategiaWalls**: 
  - Usada al inicio cuando hay muchos enemigos
  - Se mantiene cerca de las paredes para protección
  - Enfoque defensivo y de supervivencia

- **EstrategiaAntiWalls**:
  - Usada al final cuando quedan pocos enemigos
  - Movimiento agresivo hacia el centro
  - Enfoque ofensivo de caza

## 🔧 Personalización

### Añadir Nueva Estrategia

1. Implementa la interfaz `Estrategia`:
   ```java
   public class MiNuevaEstrategia implements Estrategia {
       // Implementar métodos requeridos
   }
   ```

2. Modifica el `Estratega` para incluir tu nueva estrategia

3. Recompila usando `./build.sh`

### Modificar Comportamiento

- Edita los archivos en `estrategias/` para cambiar comportamientos específicos
- Modifica `estrategas/` para cambiar la lógica de selección de estrategias

