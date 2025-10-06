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

echo ✅ Compilación completada correctamente.
echo 📦 Jar generado en: %OUT_JAR%
pause
endlocal
