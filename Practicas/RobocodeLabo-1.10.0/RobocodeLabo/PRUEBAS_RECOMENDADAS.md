# 🎯 GUÍA DE PRUEBAS PARA NICOUSTINROBOT

## 📋 PRUEBAS BÁSICAS (Ejecutar en orden)

### 1. **PRUEBA DE CONCEPTO** 
```
NicoustinRobot vs SittingDuck
Objetivo: Verificar que las estrategias funcionan básicamente
Expectativa: Victoria fácil en < 30 segundos
```

### 2. **PRUEBA DE MOVILIDAD**
```
NicoustinRobot vs SpinBot  
Objetivo: Verificar movimiento errático vs patrón circular
Expectativa: Victoria por mayor precisión y evasión
```

### 3. **PRUEBA DE PRECISIÓN**
```
NicoustinRobot vs TrackFire
Objetivo: Verificar que nuestro escaneo continuo es efectivo
Expectativa: Victoria por movimiento vs robot estático
```

### 4. **PRUEBA DE EVASIÓN**
```
NicoustinRobot vs Tracker
Objetivo: Verificar evasión contra perseguidor inteligente  
Expectativa: Batalla competitiva, depende de la estrategia activa
```

### 5. **PRUEBA DE COMBATE**
```
NicoustinRobot vs Corners
Objetivo: Verificar búsqueda y ataque contra robot defensivo
Expectativa: Victoria por movilidad superior
```

## 🏆 PRUEBAS AVANZADAS

### 6. **BATTLE ROYALE**
```
NicoustinRobot vs SpinBot vs TrackFire vs Corners
Objetivo: Supervivencia en batalla múltiple
Expectativa: Top 2 mínimo, preferiblemente ganar
```

### 7. **PRUEBA DE RESISTENCIA**
```
NicoustinRobot vs 5 robots sample aleatorios
Objetivo: Supervivencia contra múltiples enemigos
Expectativa: Sobrevivir hasta los últimos 3
```

### 8. **PRUEBA DE ESTRATEGIAS**
```
Configurar batallas largas (20+ rounds) y observar:
- ¿Cambia de estrategia correctamente?
- ¿Los mensajes de consola aparecen?
- ¿Adapta el comportamiento según energía?
```

## 📊 MÉTRICAS A OBSERVAR

### ✅ INDICADORES DE ÉXITO:
- **Supervivencia**: > 60% de las rondas
- **Score**: Consistentemente en top 2
- **Movimiento**: Nunca se queda quieto > 2 segundos
- **Escaneo**: Detecta enemigos rápidamente
- **Cambio de estrategia**: Mensajes en consola

### ❌ INDICADORES DE PROBLEMAS:
- Robot se queda inmóvil
- No detecta enemigos cercanos
- No cambia de estrategia cuando debería
- Muere rápidamente en múltiples batallas

## 🔧 TROUBLESHOOTING

Si el robot tiene problemas:

1. **Verificar consola** para mensajes de error
2. **Recompilar** si hay cambios
3. **Probar estrategia individual** editando constructor por defecto
4. **Verificar Development Options** en Robocode

## 🎯 CONFIGURACIÓN RECOMENDADA

- **Battlefield**: 800x600
- **Rounds**: 10 para pruebas, 20+ para análisis
- **Gun cooling**: 0.1 (estándar)
- **Inactivity time**: 450 (estándar)
