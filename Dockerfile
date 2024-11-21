# Usa una imagen de base con Java preinstalado
FROM amazoncorretto:17-alpine-jdk

# Copia el archivo JAR generado en el contenedor
COPY jar/api.whatsapp-0.0.1-SNAPSHOT.jar /api.jar

# Expone el puerto en el que se ejecutará tu aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/api.jar"]