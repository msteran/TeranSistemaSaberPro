# Etapa de construcción
FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests

# Etapa de runtime
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /build/target/Teran_SistemaExamenesSaberPro.jar app.jar
CMD ["java", "-jar", "app.jar"]
