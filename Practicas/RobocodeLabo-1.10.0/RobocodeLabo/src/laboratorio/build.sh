#!/bin/bash

# Script de compilación automática para NicoustinRobot
# Uso: ./build.sh
# 
# Compila todos los archivos Java y actualiza el JAR en robots/

echo "🔨 Compilando NicoustinRobot..."

# Ir al directorio src (desde laboratorio)
cd ..

# Compilar todos los archivos Java
echo "📝 Compilando archivos Java..."
javac -cp "../libs/robocode.jar:." laboratorio/*.java estrategias/*.java estrategas/*.java

if [ $? -eq 0 ]; then
    echo "✅ Compilación exitosa"

    # Crear directorio de destino principal
    mkdir -p ../robots/robotGod/laboratorio
    mkdir -p ../robots/robotGod/estrategias
    mkdir -p ../robots/robotGod/estrategas

    # Copiar archivos .class a robots/robotGod
    echo "📦 Copiando archivos a robots/robotGod..."
    cp laboratorio/*.class ../robots/robotGod/laboratorio/
    cp estrategias/*.class ../robots/robotGod/estrategias/
    cp estrategas/*.class ../robots/robotGod/estrategas/
    cp laboratorio/*.properties ../robots/robotGod/laboratorio/ 2>/dev/null || true

    # Crear/actualizar JAR
    echo "🗃️ Actualizando JAR..."
    cd ../robots
    rm -f robotGod.jar
    jar cf robotGod.jar robotGod/laboratorio/*.class robotGod/laboratorio/*.properties robotGod/estrategias/*.class robotGod/estrategas/*.class

    if [ $? -eq 0 ]; then
        echo "🏆 ¡Build completado exitosamente!"
        echo "📋 Archivos en JAR:"
        jar tf robotGod.jar | grep -E '\.(class|properties)$' | sort
    else
        echo "❌ Error creando JAR"
        exit 1
    fi
else
    echo "❌ Error de compilación"
    exit 1
fi
