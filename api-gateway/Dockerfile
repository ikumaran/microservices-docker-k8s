FROM openjdk:11-jre
USER root
WORKDIR /app
COPY target/*.jar app.jar

# secret_password comes from container environment variable
ENTRYPOINT ["java", "-jar", "-Djasypt.encryptor.password=${SECRET_PASSWORD}", "/app/app.jar"]