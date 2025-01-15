FROM openjdk:23
WORKDIR /app
COPY target/shared-dtos-1.0-SNAPSHOT.jar shareddtos.jar
ENTRYPOINT ["java","-jar","shareddtos.jar"]