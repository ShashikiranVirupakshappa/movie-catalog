#first line is base image which openjdk:17 which jdk version 17 is used
FROM openjdk:21

#second line tells which port to use for your app
EXPOSE 8081
ADD target/movie-catalog.jar movie-catalog.jar
ENTRYPOINT ["java", "-jar", "/movie-catalog.jar"]