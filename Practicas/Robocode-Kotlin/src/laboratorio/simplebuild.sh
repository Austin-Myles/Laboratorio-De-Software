#!/bin/bash
# === Script simple para crear JAR de Robocode Kotlin ===

echo "🔧 Creando JAR de Robocode Kotlin..."
echo

# Detectar directorios
BASE_DIR="$(cd "$(dirname "$0")" && pwd)"
PROJECT_DIR="$(cd "$BASE_DIR/../.." && pwd)"
ROBOTS_DIR="$PROJECT_DIR/robots"

echo "📁 Directorios:"
echo "  PROJECT_DIR: $PROJECT_DIR"
echo "  ROBOTS_DIR: $ROBOTS_DIR"
echo

# Crear directorios necesarios
mkdir -p "$ROBOTS_DIR/laboratorio"
mkdir -p "$ROBOTS_DIR/estrategias"
mkdir -p "$ROBOTS_DIR/estrategas"

# Copiar archivos .class existentes
echo "📦 Copiando archivos .class existentes..."
cp "$ROBOTS_DIR/estrategias/"*.class "$ROBOTS_DIR/estrategias/" 2>/dev/null || true
cp "$ROBOTS_DIR/laboratorio/"*.class "$ROBOTS_DIR/laboratorio/" 2>/dev/null || true

# Crear el JAR
echo "🗃️ Creando JAR..."
cd "$ROBOTS_DIR"
rm -f RobocodeLabo.jar

jar cf RobocodeLabo.jar \
    estrategias/*.class \
    laboratorio/*.class \
    laboratorio/*.properties

if [ $? -eq 0 ]; then
    echo "🏆 ¡JAR creado exitosamente!"
    echo "📋 Archivos en JAR:"
    jar tf RobocodeLabo.jar | grep -E '\.(class|properties)$' | sort
    echo
    echo "📊 Estadísticas del JAR:"
    echo "  Tamaño: $(du -h RobocodeLabo.jar | cut -f1)"
    echo "  Archivos: $(jar tf RobocodeLabo.jar | wc -l)"
    echo
    echo "✅ El robot está listo para usar en Robocode"
else
    echo "❌ Error creando JAR"
    exit 1
fi

