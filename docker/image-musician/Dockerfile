FROM openjdk:21
LABEL authors="sainane"

COPY /Musician/target/Musician-1.0-SNAPSHOT-jar-with-dependencies.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]