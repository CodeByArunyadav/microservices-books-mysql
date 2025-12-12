FROM eclipse-temurin:17-jdk
WORKDIR /author-service

COPY author-service/target/author-service.jar author-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "author-service.jar"]