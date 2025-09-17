# Build stage with Maven
FROM maven:3.9.11-eclipse-temurin AS builder

WORKDIR /build

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:17-slim

WORKDIR /app

COPY --from=builder /build/target/cinema-abyss-events*.jar /app/cinema-abyss-events.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "cinema-abyss-events.jar"]

