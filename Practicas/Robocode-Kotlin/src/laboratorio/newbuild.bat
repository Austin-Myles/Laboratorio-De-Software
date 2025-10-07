@echo off
rem === Compilar proyecto Robocode Kotlin ===
setlocal enabledelayedexpansion

rem Directorios base
set "KOTLIN_HOME=C:\Program Files\kotlin-compiler-2.2.20\kotlinc"
set LIBS=%KOTLIN_HOME%\lib
set "BASE_DIR=%~dp0"
cd /d "%BASE_DIR%"
cd ..\..
set "PROJECT_DIR=%cd%"
set "SRC_DIR=%PROJECT_DIR%\src"
set "LIBS_DIR=%PROJECT_DIR%\libs"
set "OUT_JAR=%PROJECT_DIR%\robots\robotGod.jar"

echo 🔧 Compilando proyecto Robocode Kotlin...
echo.

echo SRC_DIR: %SRC_DIR%
echo LIBS_DIR: %LIBS_DIR%
echo.

call "%KOTLIN_HOME%\bin\kotlinc.bat" "%SRC_DIR%\laboratorio\NicoustinRobot.kt" "%SRC_DIR%\estrategias\*.kt" -cp "%LIBS%\kotlin-stdlib.jar;%LIBS_DIR%\robocode.jar" -include-runtime -d "%OUT_JAR%"

echo ---- SALIDA DEL COMPILADOR ----
echo.

if %errorlevel% neq 0 (
    echo ❌ Error al compilar el proyecto.
    pause
    exit /b %errorlevel%
)

echo ✅ Compilación exitosa
echo.

rem === Copiar archivos .class a robots/ ===
echo 📦 Copiando archivos a robots/...
if not exist "%PROJECT_DIR%\robots\laboratorio" mkdir "%PROJECT_DIR%\robots\laboratorio"
if not exist "%PROJECT_DIR%\robots\estrategias" mkdir "%PROJECT_DIR%\robots\estrategias"

copy "%SRC_DIR%\laboratorio\*.class" "%PROJECT_DIR%\robots\laboratorio\" >nul
copy "%SRC_DIR%\estrategias\*.class" "%PROJECT_DIR%\robots\estrategias\" >nul

rem === Crear/actualizar JAR ===
echo 🗃️ Actualizando JAR...
cd "%PROJECT_DIR%\robots"
if exist RobotGod.jar del RobotGod.jar

jar cf RobotGod.jar estrategias/*.class laboratorio/*.class laboratorio/*.properties

if %errorlevel% equ 0 (
    echo 🏆 ¡Build completado exitosamente!
    echo 📋 Archivos en JAR:
    jar tf RobotGod.jar | findstr /E ".class .properties"
) else (
    echo ❌ Error creando JAR
    exit /b 1
)

echo.
echo ✅ Compilación completada correctamente.
echo 📦 Jar generado en: %OUT_JAR%
pause
endlocal
