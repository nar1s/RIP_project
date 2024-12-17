FROM maven:3.9.8-amazoncorretto-17 as build

COPY src src
COPY pom.xml pom.xml

RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:17-jdk-slim
RUN adduser --system taskly-backend-user && addgroup --system taskly-backend-group && adduser taskly-backend-user taskly-backend-group
USER taskly-backend-user

WORKDIR /app

COPY --from=build target/taskly-0.0.1-SNAPSHOT.jar ./taskly.jar
ENTRYPOINT ["java", "-jar", "./taskly.jar"]
