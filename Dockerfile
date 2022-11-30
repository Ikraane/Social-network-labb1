FROM openjdk:17-jdk-alpine
COPY target/spring-container.jar spring-container.jar
ENTRYPOINT ["java", "-jar", "spring-container.jar"]