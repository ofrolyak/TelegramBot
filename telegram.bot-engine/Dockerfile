FROM maven:3.6.0-jdk-11-slim AS build
WORKDIR /home/app/
COPY . .
# RUN mvn dependency:go-offline
RUN mvn -f /home/app/pom.xml clean package

FROM docker.artifactory.cfius.ericssondevops.com/ccnum/openjdk
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/app/target/*-SNAPSHOT.jar /app/spring-boot-application.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/spring-boot-application.jar"]
