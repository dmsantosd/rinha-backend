FROM openjdk:21
COPY build/libs/rinha-backend-0.0.1-SNAPSHOT.jar /usr/src/app/rinha-backend.jar
WORKDIR /usr/src/app
CMD ["java", "-jar", "rinha-backend.jar"]