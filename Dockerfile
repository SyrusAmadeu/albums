FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY target/albums-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "albums-0.0.1-SNAPSHOT.jar"]