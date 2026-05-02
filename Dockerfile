# Etapa 1: Construcción (Build)
# Usamos una imagen con Maven y Java 21 para compilar el código
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app

# Copiamos el pom.xml y descargamos las dependencias primero (Aprovecha la caché de Docker)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiamos el código fuente y compilamos saltando los tests (para mayor velocidad)
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución (Runtime)
# Usamos una imagen mucho más ligera que solo tiene el JRE (Java Runtime Environment)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copiamos únicamente el archivo .jar generado en la etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Exponemos el puerto que vimos en tus logs
EXPOSE 8081

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]