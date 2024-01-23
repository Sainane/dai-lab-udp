FROM openjdk:latest
LABEL authors="sainane"

COPY /Musician/target/Musician-1.0-SNAPSHOT-jar-with-dependencies.jar /app.jar

EXPOSE 9904
ENTRYPOINT ["java", "-jar", "/app.jar"]