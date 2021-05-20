FROM openjdk:11-jre
USER root
WORKDIR /app
COPY target/*.jar app.jar

# secret comes from docker run
ENTRYPOINT ["java", "-jar", "-Djasypt.encryptor.password=${SECRET_PASSWORD}", "/app/app.jar"]