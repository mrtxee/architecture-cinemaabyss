# Build stage with Maven
FROM maven:3.9.11-eclipse-temurin AS builder

# Set working directory
WORKDIR /build

# Copy pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the project and create JAR
RUN mvn clean package -DskipTests

# Build stage
FROM openjdk:17-slim

# Set working directory
WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /build/target/CinemaAabyssProxy*.jar /app/CinemaAabyssProxy.jar

#COPY warmhouse-temperature-one.jar /app/warmhouse-temperature-one.jar

# Открываем порт, который использует приложение
EXPOSE 8000

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "CinemaAabyssProxy.jar"]

