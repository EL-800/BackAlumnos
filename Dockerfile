FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/BackAlumnos-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} back_alumnos.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "back_alumnos.jar"]