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
ROBOTS_DIR="$PROJECT_DIR/robots"
OUT_JAR="$ROBOTS_DIR/RobocodeLabo.jar"

echo "📁 Directorios:"
echo "  BASE_DIR: $BASE_DIR"
echo "  PROJECT_DIR: $PROJECT_DIR"
echo "  SRC_DIR: $SRC_DIR"
echo "  LIBS_DIR: $LIBS_DIR"
echo "  ROBOTS_DIR: $ROBOTS_DIR"
echo

# Verificar que existen las dependencias necesarias
if [ ! -f "$LIBS_DIR/robocode.jar" ]; then
    echo "❌ Error: No se encontró robocode.jar en $LIBS_DIR"
    exit 1
fi

if [ ! -f "$LIBS_DIR/kotlin-stdlib-2.0.20.jar" ]; then
    echo "❌ Error: No se encontró kotlin-stdlib en $LIBS_DIR"
    exit 1
fi

# Crear directorios de salida si no existen
mkdir -p "$ROBOTS_DIR/laboratorio"
mkdir -p "$ROBOTS_DIR/estrategias"
mkdir -p "$ROBOTS_DIR/estrategas"

# Buscar el compilador de Kotlin
KOTLINC=""
if command -v kotlinc >/dev/null 2>&1; then
    KOTLINC="kotlinc"
elif [ -f "$LIBS_DIR/kotlin-compiler.jar" ]; then
    KOTLINC="java -jar $LIBS_DIR/kotlin-compiler.jar"
else
    echo "⚠️ No se encontró kotlinc, usando javac para compilar clases Java..."
    # Fallback: compilar solo las clases Java si existen
    if [ -f "$SRC_DIR/Main.java" ]; then
        echo "🔨 Compilando archivos Java como fallback..."
        javac -cp "$LIBS_DIR/robocode.jar:$SRC_DIR" \
              "$SRC_DIR/laboratorio/NicoustinRobot.java" \
              "$SRC_DIR/estrategias/"*.java \
              "$SRC_DIR/estrategas/"*.java \
              "$SRC_DIR/Main.java" \
              -d "$SRC_DIR"

        if [ $? -ne 0 ]; then
            echo "❌ Error de compilación Java"
            exit 1
        fi

        echo "✅ Compilación Java exitosa"
    else
        echo "❌ Error: No se encontró compilador de Kotlin ni archivos Java"
        exit 1
    fi
fi

# Si encontramos kotlinc, compilar Kotlin
if [ -n "$KOTLINC" ] && [ "$KOTLINC" != "" ]; then
    echo "🔨 Compilando archivos Kotlin con $KOTLINC..."
    $KOTLINC "$SRC_DIR/laboratorio/NicoustinRobot.kt" \
             "$SRC_DIR/estrategias/"*.kt \
             "$SRC_DIR/estrategas/"*.kt \
             "$SRC_DIR/Main.kt" \
             -cp "$LIBS_DIR/robocode.jar" \
             -d "$SRC_DIR"

    if [ $? -ne 0 ]; then
        echo "❌ Error de compilación Kotlin"
        exit 1
    fi

    echo "✅ Compilación Kotlin exitosa"
fi

# Copiar archivos .class a robots/
echo "📦 Copiando archivos a robots/..."
cp "$SRC_DIR/laboratorio/"*.class "$ROBOTS_DIR/laboratorio/" 2>/dev/null || true
cp "$SRC_DIR/estrategias/"*.class "$ROBOTS_DIR/estrategias/" 2>/dev/null || true
cp "$SRC_DIR/estrategas/"*.class "$ROBOTS_DIR/estrategas/" 2>/dev/null || true

# Crear/actualizar el JAR
echo "🗃️ Creando JAR..."
cd "$ROBOTS_DIR"
rm -f RobocodeLabo.jar

jar cf RobocodeLabo.jar \
    estrategias/*.class \
    laboratorio/*.class \
    estrategas/*.class \
    laboratorio/*.properties

if [ $? -eq 0 ]; then
    echo "🏆 ¡Build completado exitosamente!"
    echo "📋 Archivos en JAR:"
    jar tf RobocodeLabo.jar | grep -E '\.(class|properties)$' | sort
    echo
    echo "📊 Estadísticas del JAR:"
    echo "  Tamaño: $(du -h RobocodeLabo.jar | cut -f1)"
    echo "  Archivos: $(jar tf RobocodeLabo.jar | wc -l)"
else
    echo "❌ Error creando JAR"
    exit 1
fi
