FROM eclipse-temurin:17-jdk
WORKDIR /book-service

COPY book-service/target/book-service.jar book-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "book-service.jar"]