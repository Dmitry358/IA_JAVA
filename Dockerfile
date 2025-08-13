# Usa immagine ufficiale Java come base
FROM eclipse-temurin:17-jdk

# Imposta directory di lavoro
WORKDIR /app

# Copia file Maven
COPY . .

# Costruisci il JAR con Maven
RUN ./mvnw clean package -DskipTests

# Espone porta 8080
EXPOSE 8080

# Comando per avviare l'app
CMD ["java", "-jar", "target/webapp-1.0.jar"]
