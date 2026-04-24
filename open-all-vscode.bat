@echo off
setlocal

:: Si ya tienes definida la variable de entorno BASEPATH se usará, sino se asigna el valor por defecto.
if "%BASEPATH%"=="" (
    set "BASEPATH=C:\dev\codigo\suaweb2025"
)

echo Abriendo proyectos en Visual Studio Code...

echo Abriendo guisisdev_portal...
start code "%BASEPATH%\guisisdev_portal"

echo Abriendo mssuaweb-seguridad...
start code "%BASEPATH%\mssuaweb-seguridad"

echo Abriendo mssuaweb-contadores...
start code "%BASEPATH%\mssuaweb-contadores"

echo Abriendo mssuaweb-catalogos...
start code "%BASEPATH%\mssuaweb-catalogos"

echo.
echo Todos los proyectos se han abierto.
pause