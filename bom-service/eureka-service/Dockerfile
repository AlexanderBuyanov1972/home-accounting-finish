FROM openjdk:8u151-jdk-slim-stretch
MAINTAINER smilyk <smilyk1982@gmail.com>
ADD target/eureka-service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-Xmx144m", "-jar", "/app.jar"]
EXPOSE 8761