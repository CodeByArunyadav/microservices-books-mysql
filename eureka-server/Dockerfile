FROM eclipse-temurin:17-jdk
WORKDIR /eureka-server

COPY eureka-server/target/eureka-server.jar eureka-server.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "eureka-server.jar"]