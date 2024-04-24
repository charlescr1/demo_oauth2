FROM openjdk:11.0-jdk-slim-stretch

RUN mkdir /usr/src/myapp
COPY demooauth-0.0.1-SNAPSHOT.jar /usr/src/myapp
COPY application.properties /usr/src/myapp
COPY application-development.properties /usr/src/myapp
WORKDIR /usr/src/myapp
EXPOSE 8081
CMD ["java", "-jar", "demooauth-0.0.1-SNAPSHOT.jar"]
