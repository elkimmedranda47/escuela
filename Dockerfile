# Usa una imagen base con Maven para construir el proyecto
FROM maven:3.9.5-eclipse-temurin-21 AS build
WORKDIR /app

# Copia solo los archivos necesarios para construir el proyecto
COPY pom.xml .
COPY src ./src

# Construye el proyecto y genera el archivo .jar
RUN mvn clean package -DskipTests

# Usa una imagen base más liviana con OpenJDK para correr la aplicación
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copia el archivo .jar generado desde la etapa de construcción
COPY --from=build /app/target/prueba_v002-0.0.1-SNAPSHOT.jar app.jar

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]