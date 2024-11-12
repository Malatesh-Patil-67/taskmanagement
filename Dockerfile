# Stage 1: Build the application
FROM gradle:7.5.1-jdk17 AS build

WORKDIR /app

# Copy Gradle wrapper and build files
COPY gradle/wrapper gradle/wrapper
COPY gradle.properties gradle.properties
COPY build.gradle build.gradle
COPY src src

# Build the Spring Boot application
RUN gradle build --no-daemon

# Stage 2: Run the application
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the jar file from the builder stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose the port the app runs
EXPOSE 8080

# Run the Spring Boot application when the container starts
CMD ["java", "-jar", "app.jar"]

# COMMAND for - Building the docker image
# docker build -t my-springboot-app .
# COMMAND for - Running the docker image
# docker run -p 8080:8080 my-springboot-app
