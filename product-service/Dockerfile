FROM openjdk:23
WORKDIR /app
COPY target/product-service-0.0.1-SNAPSHOT.jar productservice.jar
ENTRYPOINT ["java","-jar","productservice.jar"]