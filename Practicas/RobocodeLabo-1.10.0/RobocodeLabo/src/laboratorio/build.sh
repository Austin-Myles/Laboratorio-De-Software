#!/bin/bash

# Script de compilación automática para NicoustinRobot (Paquete Independiente)
# Uso: ./build.sh [ROBOCODE_LIBS_PATH]
# 
# Compila todos los archivos Java y crea el JAR listo para usar en Robocode
# Si no se especifica ROBOCODE_LIBS_PATH, busca robocode.jar en ubicaciones comunes

echo "🔨 Compilando NicoustinRobot (Paquete Independiente)..."

# Determinar la ruta de robocode.jar
ROBOCODE_JAR=""
if [ $# -eq 1 ]; then
    # Ruta proporcionada como argumento
    ROBOCODE_JAR="$1/robocode.jar"
elif [ -f "../../libs/robocode.jar" ]; then
    # Ruta relativa estándar
    ROBOCODE_JAR="../../libs/robocode.jar"
elif [ -f "../../../libs/robocode.jar" ]; then
    # Ruta alternativa
    ROBOCODE_JAR="../../../libs/robocode.jar"
else
    echo "❌ No se encontró robocode.jar"
    echo "💡 Uso: ./build.sh [RUTA_A_LIBS_ROBOCODE]"
    echo "   Ejemplo: ./build.sh /path/to/robocode/libs"
    exit 1
fi

echo "📍 Usando robocode.jar: $ROBOCODE_JAR"

# Verificar que existe robocode.jar
if [ ! -f "$ROBOCODE_JAR" ]; then
    echo "❌ No se encontró robocode.jar en: $ROBOCODE_JAR"
    exit 1
fi

# Compilar todos los archivos Java en el directorio actual
echo "📝 Compilando archivos Java..."
javac -cp "$ROBOCODE_JAR:." *.java estrategias/*.java estrategas/*.java

if [ $? -eq 0 ]; then
    echo "✅ Compilación exitosa"

    # Crear directorio de salida
    mkdir -p build/laboratorio
    mkdir -p build/estrategias  
    mkdir -p build/estrategas

    # Copiar archivos .class y .properties
    echo "📦 Organizando archivos compilados..."
    cp *.class build/laboratorio/ 2>/dev/null || true
    cp *.properties build/laboratorio/ 2>/dev/null || true
    cp estrategias/*.class build/estrategias/ 2>/dev/null || true
    cp estrategas/*.class build/estrategas/ 2>/dev/null || true

    # Crear JAR
    echo "🗃️ Creando JAR..."
    cd build
    rm -f ../NicoustinRobot.jar
    jar cf ../NicoustinRobot.jar laboratorio/*.class laboratorio/*.properties estrategias/*.class estrategas/*.class

    if [ $? -eq 0 ]; then
        cd ..
        echo "🏆 ¡Build completado exitosamente!"
        echo "📋 Archivos en JAR:"
        jar tf NicoustinRobot.jar | sort
        echo ""
        echo "✨ Para usar en Robocode:"
        echo "   1. Copia NicoustinRobot.jar al directorio robots/ de tu instalación de Robocode"
        echo "   2. O extrae el contenido del JAR directamente en robots/"
        echo ""
        echo "📄 Archivos generados:"
        echo "   - NicoustinRobot.jar (listo para usar)"
        echo "   - build/ (archivos compilados organizados)"
    else
        echo "❌ Error creando JAR"
        exit 1
    fi
else
    echo "❌ Error de compilación"
    echo "💡 Asegúrate de que robocode.jar esté accesible"
    exit 1
fi
