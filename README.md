# AI-Assisted E-Commerce Engine

A modular e-commerce backend built with Java and Spring Boot, providing RESTful APIs for products, users, and orders, with AI-powered natural-language product search and dynamic recommendations using Spring AI and OpenAI.

## 🚀 Overview

The AI-Assisted E-Commerce Engine is a backend REST API designed to provide core e-commerce functionality together with AI-assisted product discovery.

The application follows a layered architecture with separate controllers, services, repositories, DTOs, models, configuration, and exception handling.

The AI module allows users to submit natural-language product queries and receive AI-assisted product search results and recommendations.

## ✨ Features

### E-Commerce APIs

- Product CRUD operations
- User CRUD operations
- Order creation and management
- Retrieve orders by user
- Update order status
- Product search by keyword
- Product filtering by category

### 🤖 AI-Powered Features

- Natural-language product search
- AI-assisted product recommendations
- Spring AI integration
- OpenAI ChatClient integration
- Dynamic AI-generated responses

### 🗄️ Database & Persistence

- PostgreSQL relational database
- Spring Data JPA
- Hibernate ORM
- Entity-to-table mapping
- Repository-based data access
- Automatic schema updates during development

### 🧪 API Testing

REST endpoints can be tested using Postman.

Supported HTTP operations include:

- GET
- POST
- PUT
- DELETE

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Java 17 | Programming language |
| Spring Boot 3.4.2 | Backend framework |
| Spring Web | RESTful APIs |
| Spring Data JPA | Data persistence |
| Hibernate | ORM |
| PostgreSQL | Relational database |
| Spring AI 1.0.0 | AI integration |
| OpenAI | AI-powered search and recommendations |
| Maven | Build and dependency management |
| Lombok | Boilerplate reduction |
| Postman | API testing |

## 🏗️ Project Architecture

```text
src/
└── main/
    ├── java/
    │   └── com/
    │       └── ecommerce/
    │           └── engine/
    │               ├── config/
    │               ├── controller/
    │               ├── dto/
    │               ├── exception/
    │               ├── model/
    │               ├── repository/
    │               ├── service/
    │               └── AiEcommerceEngineApplication.java
    │
    └── resources/
        └── application.properties
