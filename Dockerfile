# Use a base image with Java 11
FROM openjdk:11-jre
# Copy the JAR file to the container
COPY target/spring-mysql-gymmanager.jar spring-mysql-gymmanager.jar
# Expose the port that your Spring Boot application listens on (default is 8080)
EXPOSE 8082
# Define the command to run your application
CMD ["java", "-jar", "spring-mysql-gymmanager.jar"]