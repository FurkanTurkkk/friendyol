FROM openjdk:23
WORKDIR /app
COPY target/order-service-0.0.1-SNAPSHOT.jar orderservice.jar
ENTRYPOINT ["java","-jar","orderservice.jar"]