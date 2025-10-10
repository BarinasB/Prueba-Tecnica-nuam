# ---- Etapa 1: Build ----
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copiamos el pom.xml y descargamos dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiamos el código fuente y construimos el proyecto
COPY src ./src
RUN mvn clean package -DskipTests

# ---- Etapa 2: Runtime ----
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copiamos el .jar generado desde la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto 8080
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
