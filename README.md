# 🛒 Ecommerce Microservices

A production-ready E-commerce Backend built using Spring Boot Microservices Architecture. The system leverages API Gateway, Eureka Service Discovery, and Apache Kafka to enable scalable, resilient, and event-driven communication between distributed services. Designed following cloud-native principles, the application demonstrates real-world microservice patterns such as service registration, centralized routing, asynchronous messaging, and independent service deployment.

## 🚀 Key Features

- Product Management Service
- Inventory Management Service
- Order Management Service
- API Gateway
- Eureka Service Discovery
- Inter-Service Communication
- Apache Kafka Event Streaming
- Asynchronous Order Processing
- RESTful APIs
- Database Per Service Pattern
- Scalable Microservices Architecture
- Fault-Tolerant Distributed Design

## 🛠️ Tech Stack

- Java 21
- Spring Boot
- Spring Cloud
- Spring Data JPA
- Spring Cloud Gateway
- Eureka Server
- Apache Kafka
- MySQL
- Maven
- Docker

## 🔄 Event-Driven Architecture

The application uses Apache Kafka for asynchronous communication between services.

```text
Client
   │
   ▼
API Gateway
   │
   ▼
Order Service
   │
 Publish Event
   ▼
Kafka Topic
   │
   ├── Inventory Service
   └── Notification Service
