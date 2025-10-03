@echo off
rem Script de compilación automática para NicoustinRobot (Windows)
rem Uso: build.bat

@echo off
set KOTLIN_HOME=C:\Program Files\kotlin-compiler-2.2.20\kotlinc
set CLASSPATH=%KOTLIN_HOME%\lib\kotlin-stdlib.jar

%KOTLIN_HOME%\bin\kotlinc estrategias\*.kt -include-runtime -d Estrategias.jar

echo 🔨 Compilando NicoustinRobot...

rem Ir al directorio src (desde laboratorio)
cd ..

rem Compilar archivos Kotlin y Java juntos
echo 📝 Compilando archivos Kotlin y Java...

rem IMPORTANTE:
rem -cp incluye robocode.jar y el directorio actual (.)
rem -d indica dónde dejar los .class (build/ en este caso)

if not exist build mkdir build

kotlinc laboratorio/*.kt estrategias/*.kt laboratorio/*.java estrategias/*.java ^
  -cp "../libs/robocode.jar" ^
  -d build

if %errorlevel% equ 0 (
    echo ✅ Compilación exitosa

    rem Copiar archivos .class a robots/
    echo 📦 Copiando archivos a robots/...
    if not exist ..\robots\laboratorio mkdir ..\robots\laboratorio
    if not exist ..\robots\estrategias mkdir ..\robots\estrategias
    copy build\laboratorio\*.class ..\robots\laboratorio\
    copy build\estrategias\*.class ..\robots\estrategias\
    
    rem Crear/actualizar JAR
    echo 🗃️ Actualizando JAR...
    cd ..\robots
    if exist RobocodeLabo.jar del RobocodeLabo.jar
    jar cf RobocodeLabo.jar estrategias/*.class laboratorio/*.class laboratorio/*.properties
    
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
