FROM openjdk:23
WORKDIR /app
COPY target/auth-service-0.0.1-SNAPSHOT.jar authservice.jar
ENTRYPOINT ["java","-jar","authservice.jar"]