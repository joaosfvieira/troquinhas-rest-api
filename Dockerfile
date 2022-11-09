FROM gradle:jdk17-alpine AS builder

WORKDIR /app
COPY . .
RUN gradle build

FROM openjdk:20-slim-buster

RUN mkdir target
COPY --from=builder /app/build/libs/*.jar target/app.jar

EXPOSE 8080
CMD ["java","-jar","target/app.jar"]
