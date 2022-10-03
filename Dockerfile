FROM openjdk:8-jdk-alpine AS build
# ARG JAR_FILE=*.jar
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]