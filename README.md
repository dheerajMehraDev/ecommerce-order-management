E-commerce Order Management System (Backend)

A scalable E-commerce backend application built using Java & Spring Boot, implementing secure REST APIs with role-based access, JWT authentication, and clean layered architecture.

This project focuses on real-world backend engineering practices including authentication, authorization, validation, DTO mapping, and global exception handling.

Tech Stack

Java 17

Spring Boot

Spring Security

JWT (JSON Web Token)

Spring Data JPA / Hibernate

MySQL / PostgreSQL

ModelMapper

Maven

Features
Authentication & Authorization

User registration & login

JWT-based authentication

Role-based access control (ADMIN, USER)

Secured endpoints using Spring Security

Core Modules (CRUD APIs)

User Module

Create, update, delete users

Assign roles

Product Module

Add/update/delete products

Fetch products

Order Module

Place orders

View user orders

Session Module

Track user sessions

Engineering Practices

MVC layered architecture

Controller → Service → Repository

DTO pattern with ModelMapper

Request validation using @Valid

Custom exceptions

Global exception handling with @ControllerAdvice

Clean package structure

controller/
service/
repository/
dto/
entity/
exception/
security/
config/


API Examples
Auth

POST /api/auth/register

POST /api/auth/login

Users

GET /api/users

PUT /api/users/{id}

Products

POST /api/products

GET /api/products

Orders

POST /api/orders

GET /api/orders/user/{userId}

(All secured using JWT)

How to Run Locally

Clone the repository

Configure database in application.yml

Run:

mvn spring-boot:run


Server starts at:

http://localhost:8080

Upcoming Enhancements (In Progress)

This project is actively evolving into a production-grade system with:

Docker & Docker Compose

AWS deployment (EC2 / RDS / S3)

Kafka for event-driven architecture

Redis for caching

Kubernetes (K8s)

Microservices architecture
