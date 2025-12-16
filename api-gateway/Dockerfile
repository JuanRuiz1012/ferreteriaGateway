FROM eclipse-temurin:17-jre

WORKDIR /app

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

COPY src/main/resources/application.yml /app/config/application.yml

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar","--spring.config.location=file:/app/config/application.yml"]