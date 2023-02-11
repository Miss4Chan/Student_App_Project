FROM openjdk:17

ARG JAR_FILE=target/*.jar

RUN mkdir /opt/location-microservice

COPY ${JAR_FILE} /opt/location-microservice/app.jar

ENTRYPOINT ["java","-jar","/opt/location-microservice/app.jar"]
