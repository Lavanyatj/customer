FROM openjdk:8-jdk
EXPOSE 8082
ADD target/customer.jar customer.jar
ENTRYPOINT ["java","-jar","/customer.jar"]