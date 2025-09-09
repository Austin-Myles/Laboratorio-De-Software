@echo off
rem Script de compilación automática para NicoustinRobot (Windows)
rem Uso: build.bat

echo 🔨 Compilando NicoustinRobot...

rem Ir al directorio src (desde laboratorio)
cd ..

rem Compilar todos los archivos Java
echo 📝 Compilando archivos Java...
javac -cp "..\libs\robocode.jar;." laboratorio\*.java estrategias\*.java estrategas\*.java

if %errorlevel% equ 0 (
    echo ✅ Compilación exitosa

    rem Crear directorios de destino dentro de robotGod
    if not exist ..\robots\robotGod\laboratorio mkdir ..\robots\robotGod\laboratorio
    if not exist ..\robots\robotGod\estrategias mkdir ..\robots\robotGod\estrategias
    if not exist ..\robots\robotGod\estrategas mkdir ..\robots\robotGod\estrategas

    rem Copiar archivos .class a robots/robotGod
    echo 📦 Copiando archivos a robots/robotGod...
    copy laboratorio\*.class ..\robots\robotGod\laboratorio\
    copy estrategias\*.class ..\robots\robotGod\estrategias\
    copy estrategas\*.class ..\robots\robotGod\estrategas\
    copy laboratorio\*.properties ..\robots\robotGod\laboratorio\ >nul 2>&1

    rem Crear/actualizar JAR
    echo 🗃️ Actualizando JAR...
    cd ..\robots
    if exist robotGod.jar del robotGod.jar
    jar cf robotGod.jar robotGod\laboratorio\*.class robotGod\laboratorio\*.properties robotGod\estrategias\*.class robotGod\estrategas\*.class

    if %errorlevel% equ 0 (
        echo 🏆 ¡Build completado exitosamente!
        echo 📋 Archivos en JAR:
        jar tf robotGod.jar | findstr /E ".class .properties"
    ) else (
        echo ❌ Error creando JAR
        exit /b 1
    )
) else (
    echo ❌ Error de compilación
    exit /b 1
)
