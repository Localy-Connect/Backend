# Runtime‐Image für Localy
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Kopiere das fertig gebaute JAR (mvn package vorher lokal laufen lassen)
COPY target/*.jar app.jar

# HTTP-Port deiner App
EXPOSE 8080

# Default-Startkommando
ENTRYPOINT ["java", "-jar", "app.jar"]
