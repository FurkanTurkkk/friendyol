FROM openjdk:23
WORKDIR /app
COPY target/eureka-server-0.0.1-SNAPSHOT.jar eurekaserver.jar
ENTRYPOINT ["java","-jar","eurekaserver.jar"]