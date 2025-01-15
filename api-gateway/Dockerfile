FROM openjdk:23
WORKDIR /app
COPY target/api-gateway-0.0.1-SNAPSHOT.jar apigateway.jar
ENTRYPOINT ["java","-jar","apigateway.jar"]