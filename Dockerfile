# Usar una imagen base con Java 17
FROM openjdk:17-slim

#Directorio dónde se colocará la aplicación en el contendor
WORKDIR /app

#Copiar el archivo jar del proyectyo al directorio /app en el contenedor
COPY target/Authentication-0.0.1-SNAPSHOT.jar /app/authentication.jar

#Exponer el puerto que usa la aplicación
EXPOSE 3000

#Comando para ejectuar la aplicación
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "authentication.jar"]