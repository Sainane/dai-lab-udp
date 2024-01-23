FROM openjdk:21
LABEL authors="sainane"

COPY /Auditor/target/Auditor-1.0-SNAPSHOT-jar-with-dependencies.jar /app.jar

EXPOSE 2205
EXPOSE 9904
ENTRYPOINT ["java", "-jar", "/app.jar"]