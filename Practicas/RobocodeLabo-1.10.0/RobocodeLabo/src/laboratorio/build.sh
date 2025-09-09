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

    # Crear directorios de destino
    mkdir ../robots/laboratorio
    mkdir ../robots/estrategias
    mkdir ../robots/estrategas


    # Copiar archivos .class a robots/
    echo "📦 Copiando archivos a robots/..."
    cp laboratorio/*.class ../robots/laboratorio/
    cp estrategas/*.class ../robots/estrategas/
    cp estrategias/*.class ../robots/estrategias/

    # Crear/actualizar JAR
    echo "🗃️ Actualizando JAR..."
    cd ../robots
    rm -f RobocodeLabo.jar
    jar cf RobocodeLabo.jar estrategias/*.class laboratorio/*.class laboratorio/*.properties estrategas/*.class

    if [ $? -eq 0 ]; then
        echo "🏆 ¡Build completado exitosamente!"
        echo "📋 Archivos en JAR:"
        jar tf RobocodeLabo.jar | grep -E '\.(class|properties)$' | sort
    else
        echo "❌ Error creando JAR"
        exit 1
    fi
else
    echo "❌ Error de compilación"
    exit 1
fi