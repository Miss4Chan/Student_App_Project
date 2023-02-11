FROM openjdk:17

ARG JAR_FILE=target/*.jar

RUN mkdir /opt/help-microservice

COPY ${JAR_FILE} /opt/help-microservice/app.jar

ENTRYPOINT ["java","-jar","/opt/help-microservice/app.jar"]
