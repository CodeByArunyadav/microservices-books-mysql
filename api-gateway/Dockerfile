FROM eclipse-temurin:17-jdk
WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
#FROM eclipse-temurin:17-jdk
#WORKDIR /api-gateway

#COPY api-gateway/target/api-gateway.jar api-gateway.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "api-gateway.jar"]