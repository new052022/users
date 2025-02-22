FROM openjdk:21-jdk-slim

WORKDIR /app

COPY build/libs/users-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9010

CMD ["java", "-jar", "app.jar"]