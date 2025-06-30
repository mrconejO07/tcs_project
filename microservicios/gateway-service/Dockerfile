FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/*.jar app.jar

# Comando que ejecuta tu app
ENTRYPOINT ["java", "-jar", "app.jar"]
