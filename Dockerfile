# Stage 1: Build con Maven
FROM maven:3.8.5-eclipse-temurin-17 AS build

WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Runtime più leggero
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copia solo il JAR generato
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
