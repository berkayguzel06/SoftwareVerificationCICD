FROM openjdk:17-jdk-alpine

# Copy the JAR file from the green_code folder
WORKDIR /app
COPY target/*.jar app.jar

# Expose the default Spring Boot port (8080)
EXPOSE 8080

# Start the application using the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]