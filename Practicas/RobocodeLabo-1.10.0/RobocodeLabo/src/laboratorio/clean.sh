#!/bin/bash

# Script de limpieza para NicoustinRobot
# Elimina archivos compilados y temporales

echo "🧹 Limpiando archivos compilados..."

# Eliminar archivos .class
find . -name "*.class" -type f -delete

# Eliminar directorio build
rm -rf build/

# Eliminar JAR
rm -f NicoustinRobot.jar

echo "✅ Limpieza completada"
echo "💡 Ejecuta ./build.sh para recompilar"
