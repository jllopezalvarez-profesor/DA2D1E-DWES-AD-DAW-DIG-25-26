# Etapa 1: Construccin (Build)
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copiar el POM raz y los de los mdulos para descargar dependencias
COPY pom.xml .
COPY api/pom.xml api/
COPY common/pom.xml common/
COPY security/pom.xml security/
COPY web/pom.xml web/

# Descargar dependencias (esto se cachea si los POMs no cambian)
RUN mvn dependency:go-offline -B

# Copiar el cdigo fuente necesario
COPY common/src common/src
COPY security/src security/src
COPY api/src api/src

# Compilar y empaquetar solo el mdulo API (y sus dependencias internas)
RUN mvn clean package -DskipTests -pl api -am

# Etapa 2: Ejecucin (Run)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copiar el JAR generado desde la etapa de build
COPY --from=build /app/api/target/*.jar app.jar

# Exponer el puerto que configuramos en application.properties (8000)
EXPOSE 8000

# Comando para arrancar la aplicacin usando el perfil 'docker'
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "app.jar"]
