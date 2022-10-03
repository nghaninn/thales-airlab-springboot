FROM gradle:jdk11 as gradleimage
COPY . /home/gradle/source
WORKDIR /home/gradle/source
RUN ./gradlew build -x test

FROM openjdk:11-jre-slim
EXPOSE ${PORT}
COPY --from=gradleimage /home/gradle/source/build/libs/*-plain.jar /app/app.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "app.jar"]