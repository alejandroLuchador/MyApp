FROM openjdk:8-jdk-alpine
# Not using explicit name of the JAR as it will change as version changes.
# Always expecting one JAR in that directory
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
