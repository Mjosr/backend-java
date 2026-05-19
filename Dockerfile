# ==========================================
# Paso 1: Compilar la aplicación (Fase de Build)
# ==========================================
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# ==========================================
# Paso 2: Entorno de ejecución (Fase de Run)
# ==========================================
# Usamos la versión "slim" oficial (basada en Debian), que es ultra estable con Firebase
FROM eclipse-temurin:17-jdk-slim

# Copiar el archivo .jar generado en la fase anterior
COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar demo.jar

# Exponer el puerto estándar de Spring Boot
EXPOSE 8080

# Comando para arrancar restringiendo el uso de memoria RAM para que quepa en Render Free
ENTRYPOINT ["java", "-Xmx300m", "-Xss512k", "-jar", "demo.jar"]