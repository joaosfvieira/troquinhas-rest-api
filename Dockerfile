FROM maven:3.8.6-amazoncorretto-17 AS builder

WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean -e -B package

FROM openjdk:20-slim-buster

RUN mkdir target
COPY --from=builder /app/target/*.jar target/app.jar

EXPOSE 8080
CMD ["java","-jar","target/app.jar"]