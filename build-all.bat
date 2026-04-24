@echo off
setlocal

:: Definir la ruta base donde se encuentran los proyectos
set BASEPATH=C:\dev\codigo\suaweb2025

:: Definir la versión de Java específica para la compilación
set JAVA_HOME=C:\Program Files\Java\jdk-17
set PATH=%JAVA_HOME%\bin;%PATH%

echo Compilando mssuaweb-seguridad...
cd /d %BASEPATH%\mssuaweb-seguridad
call mvn clean package -DskipTests
if errorlevel 1 (
    echo Error al compilar mssuaweb-seguridad.
    pause
    exit /b 1
)



echo Compilando mssuaweb-catalogos...
cd /d %BASEPATH%\mssuaweb-catalogos
call mvn clean package -DskipTests
if errorlevel 1 (
    echo Error al compilar mssuaweb-catalogos.
    pause
    exit /b 1
)


echo Compilando mssuaweb-contadores...
cd /d %BASEPATH%\mssuaweb-contadores
call mvn clean package -DskipTests
if errorlevel 1 (
    echo Error al compilar mssuaweb-contadores.
    pause
    exit /b 1
)


echo Compilando mssuaweb-acuses...
cd /d %BASEPATH%\mssuaweb-acuses
call mvn clean package -DskipTests
if errorlevel 1 (
    echo Error al compilar mssuaweb-acuses.
    pause
    exit /b 1
)


echo Compilando mssuaweb-documentos...
cd /d %BASEPATH%\mssuaweb-documentos
call mvn clean package -DskipTests
if errorlevel 1 (
    echo Error al compilar mssuaweb-documentos.
    pause
    exit /b 1
)


echo.
echo Todas las aplicaciones se compilaron correctamente.
pause