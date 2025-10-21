# Robocode Kotlin - Migración del Proyecto Java

Este proyecto es una migración del robot NicoustinRobot de Java a Kotlin para el taller de Robocode.

## Estructura del Proyecto

```
src/
├── Main.kt                    # Punto de entrada principal
├── laboratorio/
│   ├── NicoustinRobot.kt     # Robot principal
│   ├── simplebuild.sh        # Script de compilación simple
│   └── newbuild.sh          # Script de compilación completo
├── estrategias/
│   ├── Estrategia.kt        # Interface de estrategias
│   ├── EstrategiaWalls.kt   # Estrategia defensiva perimetral
│   ├── EstrategiaAntiWalls.kt # Estrategia ofensiva de caza
│   ├── FieldDetector.kt     # Detector de tamaño del campo
│   ├── StrategyEvaluator.kt # Evaluador de estrategias
│   └── StrategyMapper.kt    # Mapeador de situaciones
└── estrategas/
    ├── Estratega.kt         # Interface del estratega
    └── EstrategaChill.kt    # Implementación del estratega
```

## Características

- **Sistema de estrategias dinámico**: Cambia automáticamente entre estrategias según el número de enemigos
- **EstrategiaWalls**: Patrulla perimetral para supervivencia (muchos enemigos)
- **EstrategiaAntiWalls**: Caza en esquinas para eliminar enemigos (pocos enemigos)
- **Detección automática del campo**: Adapta el comportamiento al tamaño del campo
- **Patrón Strategy**: Arquitectura limpia y extensible

## Compilación

### Opción 1: Script Simple
```bash
cd src/laboratorio
./simplebuild.sh
```

### Opción 2: Script Completo (Requiere kotlinc)
```bash
cd src/laboratorio
./newbuild.sh
```

## Uso en Robocode

1. Compilar el proyecto usando uno de los scripts
2. El JAR `RobocodeLabo.jar` se creará en la carpeta `robots/`
3. Cargar el robot en Robocode desde el JAR generado

## Propiedades de esta versión en Kotlin

- **Sintaxis más limpia**: Menos código boilerplate
- **Null safety**: Previene NullPointerException automáticamente
- **Interoperabilidad**: Funciona con librerías Java existentes
- **Misma funcionalidad**: Comportamiento idéntico al robot original

## Arquitectura

El robot usa el patrón Strategy con un Estratega que decide qué estrategia usar:

- **EstrategaChill**: Evalúa la situación y elige la estrategia apropiada
- **EstrategiaWalls**: Movimiento perimetral defensivo
- **EstrategiaAntiWalls**: Posicionamiento en esquinas para emboscadas

El cambio de estrategia ocurre automáticamente cuando el número de enemigos cambia de >3 a ≤3.

