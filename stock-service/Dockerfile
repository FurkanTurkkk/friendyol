FROM openjdk:23
WORKDIR /app
COPY target/stock-service-0.0.1-SNAPSHOT.jar stockservice.jar
ENTRYPOINT ["java","-jar","stockservice.jar"]