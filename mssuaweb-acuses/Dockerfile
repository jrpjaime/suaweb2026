# Usar una imagen base con Java 17
FROM openjdk:17-slim

# Directorio donde se colocará la aplicación en el contenedor
WORKDIR /app

# Copiar el archivo jar del proyecto al directorio /app en el contenedor
COPY target/mssuaweb-acuses-0.0.1-SNAPSHOT.jar /app/mssuaweb-acuses.jar

# Exponer el puerto que usa la aplicación
EXPOSE 4112

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "/app/mssuaweb-acuses.jar"]
