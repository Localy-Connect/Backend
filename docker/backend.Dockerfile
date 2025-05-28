# Build-Stage
FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Runtime-Stage
FROM openjdk:17-jdk-slim
WORKDIR /app

COPY --from=builder /app/target/localy-service.jar ./localy-service.jar

EXPOSE 8080

# Enable JDWP Debug
ENV JAVA_TOOL_OPTIONS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"

ENTRYPOINT ["java", "-jar", "localy-service.jar"]
