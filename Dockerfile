FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
ADD target/*.jar springbootdb.jar
ENTRYPOINT ["java","-jar","springbootdb.jar"]