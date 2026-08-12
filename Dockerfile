# Stage 1: Build the application using Maven
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# This packages your app into a .jar file, skipping tests for the build phase
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-jre
WORKDIR /app
# Copy the built .jar file from Stage 1
COPY --from=build /app/target/*.jar app.jar
# Start the application
ENTRYPOINT ["java", "-jar", "app.jar"]