@echo off
setlocal

:: Definir la versión de Java específica para la compilación
set "JAVA_HOME=C:\Program Files\Java\jdk-17"
set "PATH=%JAVA_HOME%\bin;%PATH%"


:: Definir la ruta base donde se encuentran los proyectos (puedes usar la variable de entorno BASEPATH si ya está definida)
if "%BASEPATH%"=="" (
    set "BASEPATH=C:\dev\codigo\suaweb2025"
)

 
:: -------------------------------
:: Ejecutar cada aplicación en una ventana CMD separada
:: -------------------------------

echo Iniciando mssuaweb-seguridad...
start cmd /k "cd /d %BASEPATH%\mssuaweb-seguridad && java -jar target\mssuaweb-seguridad-0.0.1-SNAPSHOT.jar"

echo Iniciando mssuaweb-catalogos...
start cmd /k "cd /d %BASEPATH%\mssuaweb-catalogos && java -jar target\mssuaweb-catalogos-0.0.1-SNAPSHOT.jar"

echo Iniciando mssuaweb-contadores...
start cmd /k "cd /d %BASEPATH%\mssuaweb-contadores && java -jar target\mssuaweb-contadores-0.0.1-SNAPSHOT.jar"

echo Iniciando mssuaweb-acuses...
start cmd /k "cd /d %BASEPATH%\mssuaweb-acuses && java -jar target\mssuaweb-acuses-0.0.1-SNAPSHOT.jar"

echo Iniciando mssuaweb-documentos...
start cmd /k "cd /d %BASEPATH%\mssuaweb-documentos && java -jar target\mssuaweb-documentos-0.0.1-SNAPSHOT.jar"


echo.
echo Las aplicaciones se han iniciado en ventanas separadas.
pause