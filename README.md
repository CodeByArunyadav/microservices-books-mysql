This project based on Spring Boot Microservices + Eureka + API Gateway + MySQL + Swagger + Actuator + Maven build with Github Action + Run dockerfile create images of all microservices and push it to Docker hub.
________________________________________
✅ MICROSERVICES PROJECT – PPT CONTENT (TEXT VERSION)
Project: Books, Authors, Ratings Microservices with Eureka + Gateway + MySQL + Swagger
________________________________________
Slide 1 – Title Slide
Microservices Architecture Project
Books | Authors | Ratings
Spring Boot 3.x | Eureka Server | API Gateway | MySQL | Actuator | Swagger
Presented by: Arun Yadav
________________________________________
Slide 2 – Agenda
1.	Project Overview
2.	Architecture Diagram
3.	Technology Stack
4.	Service Descriptions
5.	Eureka (Service Registry)
6.	API Gateway
7.	Inter-Service Communication
8.	MySQL Integration
9.	Swagger Configuration
10.	Actuator Monitoring
11.	Sample APIs
12.	Summary
________________________________________
Slide 3 – What Are Microservices?
•	Small, loosely coupled independent services
•	Each service handles one domain
•	Services communicate using REST
•	Allows scaling & deployment independently
•	Fault isolation with service registry
________________________________________
Slide 4 – Project Overview
We build 3 microservices:
1.	Book Service
2.	Author Service
3.	Rating Service
Supporting systems:
•	Eureka Server (Service Discovery)
•	API Gateway (Central entry point)
•	MySQL (DB for each service)
•	Swagger (API documentation)
•	Actuator (Monitoring/Health)
________________________________________
Slide 5 – Architecture Diagram (Text Description)
                +-----------------------+
                |      API Gateway      |
                |  http://localhost:8080|
                +----------+------------+
                           |
    --------------------------------------------------
    |                        |                       |
+-----------+         +-------------+         +--------------+
| Book Svc  | <-----> | Author Svc  | <-----> | Rating Svc   |
| 8081      |         | 8082        |         | 8083         |
+-----------+         +-------------+         +--------------+

             +-----------------------------+
             |      Eureka Server 8761     |
             +-----------------------------+

        Each service → MySQL database instance


 	
<img width="1536" height="1024" alt="ChatGPT Image Dec 9, 2025, 02_25_31 PM" src="https://github.com/user-attachments/assets/0306a600-8873-4e47-ac04-3c09dbe023f9" />

 Slide 6 – Technology Stack
•	Java 17+
•	Spring Boot 3.1+
•	Spring Cloud 2023.x
•	Spring Data JPA
•	Spring Cloud Gateway
•	Spring Cloud Eureka
•	MySQL
•	Swagger (springdoc-openapi)
•	Actuator
•	Maven
•	STS 4 / IntelliJ / VSCode
________________________________________
Slide 7 – Microservices
1. Book Service
•	Saves book details
•	Calls AuthorService & RatingService
•	Provides combined response (BookDTO)
2. Author Service
•	Manages author info
•	CRUD operations
•	Provides AuthorDTO
3. Rating Service
•	Stores ratings for books
•	Returns RatingDTO
________________________________________
Slide 8 – DTO Structure
AuthorDTO
class AuthorDTO {
   Long id;
   String name;
   String biography;
}
RatingDTO
class RatingDTO {
   Long id;
   Long bookId;
   Integer stars;
   String review;
}
________________________________________
Slide 9 – BookResponse DTO
class BookResponse {
   Long id;
   String title;
   String description;
   AuthorDTO author;
   RatingDTO rating;
}
________________________________________
Slide 10 – Eureka Server
•	Registers all microservices
•	Acts as a service registry
•	URL → http://localhost:8761
•	Shows live status: UP / DOWN
•	Microservices auto-register using @EnableEurekaClient
________________________________________
Slide 11 – Eureka Configuration
pom.xml
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
Main Class
@EnableEurekaServer
@SpringBootApplication
________________________________________
Slide 12 – Microservice Eureka Client Config
spring.application.name=book-service
server.port=8081

eureka.client.register-with-eureka=true
eureka.client.fetch-registry=true
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
________________________________________
Slide 13 – API Gateway
•	All requests routed through gateway
•	URL examples:
o	/books/** → Book Service
o	/authors/** → Author Service
o	/ratings/** → Rating Service
•	Handles:
o	request routing
o	load balancing
o	filters
o	security (future)
________________________________________
Slide 14 – Gateway Route Config
spring.cloud.gateway.routes[0].id=book-service
spring.cloud.gateway.routes[0].uri=lb://book-service
spring.cloud.gateway.routes[0].predicates[0]=Path=/books/**
________________________________________
Slide 15 – MySQL Integration
Each service has its own DB:
•	book_db
•	author_db
•	rating_db
Example configuration:
spring.datasource.url=jdbc:mysql://localhost:3306/book_db
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
________________________________________
Slide 16 – Sample Entity (BookService)
@Entity
class Book {
  @Id @GeneratedValue
  Long id;
  String title;
  Long authorId;
}
________________________________________
Slide 17 – Inter-Service Communication
BookService → calls AuthorService & RatingService
using WebClient:
@Autowired
private WebClient.Builder webClient;

AuthorDTO author = webClient.build()
   .get().uri("http://author-service/authors/" + authorId)
   .retrieve().bodyToMono(AuthorDTO.class).block();
________________________________________
Slide 18 – Swagger Documentation
Dependency:
<dependency>
  <groupId>org.springdoc</groupId>
  <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
  <version>2.3.0</version>
</dependency>
Access URL:
http://localhost:8081/swagger-ui.html
________________________________________
Slide 19 – Swagger Config
(Since Spring Boot 3, no config required)
Optional:
@OpenAPIDefinition(
   info = @Info(title = "Book Service API", version = "1.0")
)
________________________________________
Slide 20 – Actuator
Provides health & metrics endpoints.
Add dependency:
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
________________________________________
Slide 21 – Actuator Config
management.endpoints.web.exposure.include=*
management.endpoint.health.show-details=always
URL:
http://localhost:8081/actuator
http://localhost:8081/actuator/health
________________________________________
Slide 22 – Sample APIs
Book Service
•	GET /books
•	GET /books/{id}
•	POST /books
Author Service
•	GET /authors
•	POST /authors
Rating Service
•	GET /ratings/book/{id}
•	POST /ratings
________________________________________
Slide 23 – Combined Book Response
API:
GET /books/{id}
Response:
{
  "title": "Spring Boot",
  "author": {…},
  "rating": {…}
}

________________________________________
Slide 24 – screenshot of Response

![Capture](https://github.com/user-attachments/assets/c922b710-065b-41c3-8d79-ac7ad9ef2508)


![Swagger1](https://github.com/user-attachments/assets/bb6900b0-16a5-456d-8c61-3599fc69c1c8)

![Capture2](https://github.com/user-attachments/assets/b35799d3-17d0-4f9e-9c81-d5038de9639e)

![Capture3](https://github.com/user-attachments/assets/2afc8704-6e28-445a-ac90-45e54f07d3fa)

![Capture5](https://github.com/user-attachments/assets/ee5a0cb6-b9a2-45f8-b092-c6aa65c100a9)

![Capture6](https://github.com/user-attachments/assets/46a81952-b203-4df7-94c8-bc684a20b23c)

![Capture7](https://github.com/user-attachments/assets/5f8fdf4a-81c2-48ff-bed5-286bb044d16f)





________________________________________
Slide 24 – Error Handling
•	Custom exception handler
•	404 Not Found
•	500 Internal Server Error
•	Communication failure fallback
________________________________________
Slide 25 – Security (Future Scope)
•	JWT Authentication
•	Role-based access
•	Gateway-level security
________________________________________
Slide 26 – Conclusion
•	Fully functional microservices system
•	Scalable, modular architecture
•	Easy to document (Swagger)
•	Easy to monitor (Actuator)
•	Production-ready design
________________________________________
Slide 27 – Thank You
 
________________________________________
 
Would you like those?

