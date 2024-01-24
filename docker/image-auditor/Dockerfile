FROM openjdk:21
LABEL authors="sainane"

COPY /Auditor/target/Auditor-1.0-SNAPSHOT-jar-with-dependencies.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]