FROM openjdk:11
ARG SERVICE_NAME=auth-service-
ARG JAR_FILE=build/libs/${SERVICE_NAME}0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/app.jar"]