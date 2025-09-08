@echo off
rem Script de compilación automática para NicoustinRobot (Windows)
rem Uso: build.bat

echo 🔨 Compilando NicoustinRobot...

rem Ir al directorio src (desde laboratorio)
cd ..

rem Compilar todos los archivos Java
echo 📝 Compilando archivos Java...
javac -cp "../libs/robocode.jar;." laboratorio/*.java estrategas/*.java estrategias/*.java estrategas/*.java

if %errorlevel% equ 0 (
    echo ✅ Compilación exitosa

    rem Crear directorios de destino
    mkdir ..\robots\laboratorio
    mkdir ..\robots\estrategias
    mkdir ..\robots\estrategas

    
    rem Copiar archivos .class a robots/
    echo 📦 Copiando archivos a robots/...
    copy laboratorio\*.class ..\robots\laboratorio\
    copy estrategias\*.class ..\robots\estrategias\
    copy estrategas\*.class ..\robots\estrategas\
    
    rem Crear/actualizar JAR
    echo 🗃️ Actualizando JAR...
    cd ..\robots
    if exist RobocodeLabo.jar del RobocodeLabo.jar
    jar cf RobocodeLabo.jar estrategias/*.class laboratorio/*.class laboratorio/*.properties estrategas/*.class
    
    if %errorlevel% equ 0 (
        echo 🏆 ¡Build completado exitosamente!
        echo 📋 Archivos en JAR:
        jar tf RobocodeLabo.jar | findstr /E ".class .properties"
    ) else (
        echo ❌ Error creando JAR
        exit /b 1
    )
) else (
    echo ❌ Error de compilación
    exit /b 1
)
