FROM eclipse-temurin:17-jdk
WORKDIR /rating-service

COPY rating-service/target/rating-service.jar rating-service.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "rating-service.jar"]