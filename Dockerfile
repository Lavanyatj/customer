FROM eclipse-temurin:8-jdk
WORKDIR /customer

COPY target/*.jar customer.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/customer.jar"]