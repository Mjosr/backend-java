# ==========================================
# Paso 1: Compilar la aplicación (Fase de Build)
# ==========================================
FROM maven:3.8.5-openjdk-17 AS build

# Copiar el código fuente y el pom.xml al contenedor
COPY . .

# Compilar el proyecto omitiendo los tests para acelerar el despliegue
RUN mvn clean package -DskipTests

# ==========================================
# Paso 2: Entorno de ejecución (Fase de Run) - ¡Aquí corregimos el error!
# ==========================================
FROM eclipse-temurin:17-jdk-alpine

# Copiar el archivo .jar generado en la fase anterior
COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar demo.jar

# Exponer el puerto estándar que usa Spring Boot
EXPOSE 8080

# Comando para arrancar el backend en Render
ENTRYPOINT ["java", "-jar", "demo.jar"]