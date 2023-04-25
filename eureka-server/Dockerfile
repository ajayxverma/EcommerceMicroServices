FROM openjdk

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} eurekaserver.jar

ENTRYPOINT ["java", "-jar", "/eurekaserver.jar"]

EXPOSE 8761