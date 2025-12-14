FROM openjdk:17-jdk-slim AS build


WORKDIR /app
COPY pom.xml .
COPY src /app/src

FROM openjdk:17-jre-slim

ARG JAR_FILE=target/*.jar


COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]