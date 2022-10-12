#FROM openjdk:11-jdk-slim
#WORKDIR /server-app
#COPY target/vins-backend-0.0.1-SNAPSHOT.war .
#CMD java -jar ./vins-backend-0.0.1-SNAPSHOT.war

FROM openjdk:11
EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]