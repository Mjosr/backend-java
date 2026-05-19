# ==========================================
# Paso 1: Compilar la aplicación (Fase de Build)
# ==========================================
FROM maven:3.8.5-openjdk-17 AS build

# Configurar variables de entorno para obligar a Maven a consumir el mínimo de RAM
ENV MAVEN_OPTS="-Xmx256m -XX:+UseSerialGC"

COPY . .

# Compilar usando un solo hilo, sin tests y en modo silencioso para ahorrar memoria
RUN mvn clean package -DskipTests -T 1 -q

# ==========================================
# Paso 2: Entorno de ejecución (Fase de Run)
# ==========================================
FROM amazoncorretto:17-alpine-jdk

COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar demo.jar

EXPOSE 8080

# Limitar la RAM de ejecución a 256MB para dejarle el resto al sistema operativo del contenedor
ENTRYPOINT ["java", "-Xmx256m", "-XX:+UseSerialGC", "-Xss512k", "-jar", "demo.jar"]