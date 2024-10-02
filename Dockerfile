FROM openjdk:17
EXPOSE 8080
ADD target/spring-cicdaction-crud.jar spring-cicdaction-crud.jar
ENTRYPOINT ["java", "-jar", "/spring-cicdaction-crud.jar"]