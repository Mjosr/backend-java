# ==========================================
# Paso 1: Compilar la aplicación (Fase de Build)
# ==========================================
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# ==========================================
# Paso 2: Entorno de ejecución (Fase de Run)
# ==========================================
# Usamos Amazon Corretto 17 Slim, que está perfectamente indexada en Docker Hub
FROM amazoncorretto:17-alpine-jdk

# Copiar el archivo .jar generado en la fase anterior
COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar demo.jar

# Exponer el puerto estándar de Spring Boot
EXPOSE 8080

# Forzar el límite de memoria para evitar el Status 139 en Render Free
ENTRYPOINT ["java", "-Xmx300m", "-Xss512k", "-jar", "demo.jar"]