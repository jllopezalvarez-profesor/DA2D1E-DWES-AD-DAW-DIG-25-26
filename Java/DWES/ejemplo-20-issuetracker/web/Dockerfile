# Etapa 1: Construccin (Build)
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copiar POMs
COPY pom.xml .
COPY api/pom.xml api/
COPY common/pom.xml common/
COPY security/pom.xml security/
COPY web/pom.xml web/

# Descargar dependencias
RUN mvn dependency:go-offline -B

# Copiar cdigo fuente (Web necesita common y security)
COPY common/src common/src
COPY security/src security/src
COPY web/src web/src

# Compilar y empaquetar mdulo WEB
RUN mvn clean package -DskipTests -pl web -am

# Etapa 2: Ejecucin (Run)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copiar el JAR
COPY --from=build /app/web/target/*.jar app.jar

# Puerto por defecto de Spring Boot (8080)
EXPOSE 8080

# Arrancar con perfil docker
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "app.jar"]
