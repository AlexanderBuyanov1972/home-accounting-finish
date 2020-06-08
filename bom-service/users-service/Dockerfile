FROM openjdk:8u151-jdk-slim-stretch
MAINTAINER smilyk <smilyk1982@gmail.com>
# ENV SPRING_PROFILES_ACTIVE zone1
# ENV EUREKA_DEFAULT_ZONE http://localhost:8761/eureka/
ENV EUREKA_DEFAULT_ZONE http://test:${myEurekaPassword}@localhost:8010/eureka
ADD target/users-service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-Xmx160m", "-jar", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-Deureka.client.serviceUrl.defaultZone=${EUREKA_DEFAULT_ZONE}", "/app.jar"]
EXPOSE 8091