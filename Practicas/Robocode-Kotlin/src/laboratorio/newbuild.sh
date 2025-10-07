#!/bin/bash
# === Compilar proyecto Robocode Kotlin ===

set -e  # Hace que el script falle si cualquier comando devuelve error

echo "🔧 Compilando proyecto Robocode Kotlin..."
echo

# Detectar el directorio del script y establecer rutas
BASE_DIR="$(cd "$(dirname "$0")" && pwd)"
PROJECT_DIR="$(cd "$BASE_DIR/../.." && pwd)"
SRC_DIR="$PROJECT_DIR/src"
LIBS_DIR="$PROJECT_DIR/libs"
OUT_JAR="$PROJECT_DIR/robots/RobotGod.jar"

echo "SRC_DIR: $SRC_DIR"
echo "LIBS_DIR: $LIBS_DIR"
echo

# Compilar los .kt
kotlinc "$SRC_DIR/laboratorio/NicoustinRobot.kt" "$SRC_DIR/estrategias/"*.kt \
  -cp "$LIBS_DIR/robocode.jar" \
  -include-runtime \
  -d "$OUT_JAR"

echo "---- SALIDA DEL COMPILADOR ----"
echo

# Si la compilación fue exitosa
if [ $? -eq 0 ]; then
    echo "✅ Compilación exitosa"

    # Copiar .class a robots/
    echo "📦 Copiando archivos a robots/..."
    mkdir -p "$PROJECT_DIR/robots/laboratorio"
    mkdir -p "$PROJECT_DIR/robots/estrategias"
    cp "$SRC_DIR/laboratorio/"*.class "$PROJECT_DIR/robots/laboratorio/" 2>/dev/null || true
    cp "$SRC_DIR/estrategias/"*.class "$PROJECT_DIR/robots/estrategias/" 2>/dev/null || true

    # Crear/actualizar el JAR
    echo "🗃️ Actualizando JAR..."
    cd "$PROJECT_DIR/robots"
    rm -f RobotGod.jar
    jar cf RobotGod.jar estrategias/*.class laboratorio/*.class laboratorio/*.properties

    if [ $? -eq 0 ]; then
        echo "🏆 ¡Build completado exitosamente!"
        echo "📋 Archivos en JAR:"
        jar tf RobotGod.jar | grep -E '\.(class|properties)$' | sort
    else
        echo "❌ Error creando JAR"
        exit 1
    fi
else
    echo "❌ Error de compilación"
    exit 1
fi
