FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/IA_JAVA-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
