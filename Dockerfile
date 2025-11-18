# Etapa de construcción
FROM openjdk:8 AS builder
WORKDIR /app

# Instalar ANT
RUN apt-get update && apt-get install -y ant

# Copiar el proyecto completo
COPY . .

# Ejecutar ANT para generar el JAR
RUN ant clean jar

# Etapa final (runtime)
FROM openjdk:8-jre
WORKDIR /app

# Copiar el jar generado
COPY --from=builder /app/dist/*.jar app.jar

CMD ["java", "-jar", "app.jar"]
