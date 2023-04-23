FROM openjdk

ARG FILE_PATH=target/*.jar

COPY ${FILE_PATH} orderservices.jar

ENTRYPOINT ["java", "-jar", "orderservices.jar"]

EXPOSE 8081