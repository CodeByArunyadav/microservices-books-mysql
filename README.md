# 📚 Books, Authors & Ratings Microservices

A production-style Microservices Architecture project built using Spring Boot, Spring Cloud, Eureka Service Discovery, API Gateway, JWT Authentication, OpenFeign, MySQL, Swagger/OpenAPI, Actuator Monitoring, Docker, and GitHub Actions CI/CD.

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green)
![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2023.x-blue)
![MySQL](https://img.shields.io/badge/MySQL-Database-blue)
![Docker](https://img.shields.io/badge/Docker-Containerized-blue)
![GitHub Actions](https://img.shields.io/badge/GitHub-Actions-black)

---
https://hub.docker.com/repositories/codebyarunyadav
https://www.hoxcloud.in/

# 🚀 Project Overview

This project demonstrates a complete Microservices Architecture using Spring Boot and Spring Cloud.

The system consists of:

### Business Services

* Book Service
* Author Service
* Rating Service
* JWT Authentication Service

### Infrastructure Services

* Eureka Server (Service Discovery)
* API Gateway (Centralized Routing)
* MySQL Databases
* Swagger/OpenAPI
* Spring Boot Actuator
* Docker
* GitHub Actions

---

# 🏗 Architecture

## High-Level Architecture

```text
Client
   │
   ▼
API Gateway
   │
   ├────────────► Auth Service
   │
   ├────────────► Book Service
   │                 │
   │                 ├────► Author Service
   │                 │
   │                 └────► Rating Service
   │
   ▼
Eureka Server

All Services → MySQL Database
```

### Service Communication

```text
Book Service
      │
      ├──── Feign Client ────► Author Service
      │
      └──── Feign Client ────► Rating Service
```

### Service Discovery

All services register automatically with Eureka.

```text
http://localhost:8761
```

---

# 🛠 Technology Stack

## Backend

* Java 17
* Spring Boot 3.x
* Spring Data JPA
* Spring Security
* JWT Authentication

## Microservices

* Spring Cloud Gateway
* Eureka Discovery Server
* OpenFeign

## Database

* MySQL

## Documentation

* Swagger/OpenAPI

## Monitoring

* Spring Boot Actuator

## DevOps

* Maven
* Docker
* Docker Hub
* GitHub Actions

---

# 📦 Microservices

## 📘 Book Service

Responsibilities:

* Manage books
* CRUD operations
* Aggregate Author and Rating information
* Communicate with other services using Feign

### APIs

```http
GET /books

GET /books/{id}

POST /books

PUT /books/{id}

DELETE /books/{id}
```

---

## 👨‍💼 Author Service

Responsibilities:

* Manage authors
* CRUD operations

### APIs

```http
GET /authors

GET /authors/{id}

POST /authors

PUT /authors/{id}

DELETE /authors/{id}
```

---

## ⭐ Rating Service

Responsibilities:

* Store ratings
* Manage reviews
* Return ratings for books

### APIs

```http
GET /ratings

GET /ratings/book/{id}

POST /ratings
```

---

## 🔐 JWT Authentication Service

Responsibilities:

* User Authentication
* JWT Generation
* JWT Validation
* Access Token Management

### APIs

```http
POST /auth/login

POST /auth/register

POST /auth/refresh
```
 Architecture Diagram  (updated) 

<img width="1536" height="1024" alt="7ce5d15a-0e39-4bca-8ae5-67996ebe9675" src="https://github.com/user-attachments/assets/32bbc072-501b-448a-b543-7a51797db383" />

---

# 🌍 Eureka Service Discovery

All microservices register themselves with Eureka.

### Dashboard

```text
http://localhost:8761
```

### Benefits

* Service Registration
* Service Discovery
* Dynamic Routing
* Load Balancing Support

---

# 🚪 API Gateway

Gateway acts as the single entry point.

### Gateway URL

```text
http://localhost:8080
```

### Routes

```text
/book-service/**
/author-service/**
/rating-service/**
/app/**
```

### Features

* Request Routing
* Centralized Access
* Security Integration
* Swagger Aggregation

---

# 🔐 Security Architecture

Authentication is implemented using JWT.

## Flow

```text
1. User Login
2. JWT Generated
3. Client Stores Token
4. Request Sent To Gateway
5. Authorization Header Forwarded
6. Target Service Validates JWT
7. Response Returned
```

## JWT Propagation

Feign Interceptor automatically forwards the token:

```java
@Bean
public RequestInterceptor requestInterceptor() {
    return requestTemplate -> {
        // Forward Authorization Header
    };
}
```

## Benefits

* Stateless Authentication
* No Session Storage
* Scalable
* Secure Service Communication

---

# 🔄 Inter-Service Communication

Implemented using OpenFeign.

### Example

```java
@FeignClient("AUTHOR-SERVICE")
public interface AuthorClient {

    @GetMapping("/authors/{id}")
    AuthorDTO getAuthor(@PathVariable Long id);
}
```

### Communication Flow

```text
Book Service
     │
     ├────► Author Service
     │
     └────► Rating Service
```

---

# 🗄 Database Architecture

Each microservice owns its own database.

### Databases

```text
book_db
author_db
rating_db
companydb
```

### Example Configuration

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/book_db
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update
```

---

# 📄 Swagger Documentation

## Gateway Swagger

```text
http://localhost:8080/swagger-ui.html
```

Access all microservices from a single Swagger UI.

### Services

```text
Book Service
Author Service
Rating Service
Authentication Service
```

---

# ❤️ Actuator Monitoring

### Endpoints

```text
/actuator
/actuator/health
/actuator/info
/actuator/metrics
```

### Example

```text
http://localhost:8080/book-service/actuator/health
```

---

# 🐳 Docker Support

Each microservice contains its own Dockerfile.

## Build Image

```bash
docker build -t book-service .
```

## Run Container

```bash
docker run -p 8082:8082 book-service
```

---

# 🚀 GitHub Actions CI/CD

Automated CI/CD pipeline using GitHub Actions.

Pipeline includes:

* Maven Build
* Unit Testing
* JAR Packaging
* Docker Image Build
* Docker Hub Push

### Trigger

```text
push
pull_request
```

---

# 📊 Sample Aggregated Response

```json
{
  "id": 1,
  "title": "Spring Boot Microservices",
  "description": "Complete Guide",
  "author": {
    "id": 1,
    "name": "Arun Yadav"
  },
  "ratings": [
    {
      "stars": 5,
      "review": "Excellent"
    }
  ]
}
```

---

# 🚨 Error Handling

Implemented:

* Global Exception Handler
* Validation Errors
* Resource Not Found
* Internal Server Errors
* Feign Communication Errors

---

# 📈 Future Enhancements

* Spring Cloud Config Server
* Redis Caching
* Kafka Event Streaming
* Circuit Breaker (Resilience4j)
* Kubernetes Deployment
* Prometheus Monitoring
* Grafana Dashboard

---

# 🎯 Learning Outcomes

This project demonstrates:

✅ Microservices Architecture

✅ Service Discovery with Eureka

✅ API Gateway Routing

✅ JWT Authentication

✅ OpenFeign Communication

✅ Swagger Aggregation

✅ Actuator Monitoring

✅ Docker Containerization

✅ GitHub Actions CI/CD

✅ Docker Hub Deployment

---

# 👨‍💻 Author

**Arun Yadav**

GitHub:
https://github.com/CodeByArunyadav

---

# ⭐ Support

If you found this project helpful, consider giving it a ⭐ on GitHub.
