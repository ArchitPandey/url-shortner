FROM openjdk:17-jdk-alpine
LABEL authors="Archit"
WORKDIR /app
COPY target/url-shortner-0.0.1-SNAPSHOT.jar url-shortner-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=k8", "/app/url-shortner-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080