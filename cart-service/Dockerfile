FROM openjdk:23
WORKDIR /app
COPY target/cart-service-0.0.1-SNAPSHOT.jar cartservice.jar
ENTRYPOINT ["java","-jar","cartservice.jar"]