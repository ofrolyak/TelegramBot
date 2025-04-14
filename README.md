# 🤖 TelegramBot

**TelegramBot** is a modular, containerized system of services built with Docker Compose. Its core purpose is to provide a secure, extensible Telegram bot infrastructure backed by a robust set of APIs and a modern Java backend.

This project is ideal for developers looking to build a Telegram bot with enterprise-grade features like security, role-based access, data persistence, and full API documentation.

---

## 🧩 Core Modules

- **TelegramBotEngine** – A Java-based Spring Boot application that implements core Telegram Bot interactions such as receiving messages, sending replies, and handling commands.
- **CRUD API** – A RESTful API that supports **Create, Read, Update, and Delete** operations on business entities (like users, tasks, or messages).
- **Security Layer** – Integrated with **Keycloak** to handle authentication (verifying user identity) and authorization (verifying user permissions) using industry-standard protocols.

---

## ⚙️ Tech Stack & Features

### 🛠 Backend

- **Java 22** – Modern Java runtime with language enhancements and performance improvements.
- **Spring Boot** – A powerful framework to build production-grade Java applications quickly.
- **JPA (Java Persistence API) / Hibernate** – Frameworks for object-relational mapping (ORM), allowing seamless interaction with databases using Java objects.
- **DAO (Data Access Object)** – A design pattern for encapsulating all database interactions.
- **AOP (Aspect-Oriented Programming)** – A technique to separate cross-cutting concerns (like logging, security, or caching) from business logic.
- **Controller Advice** – Global exception handling for REST controllers in Spring.
- **Criteria API & Criteria Builder** – Type-safe, dynamic query building in JPA for complex filtering.
- **Caching** – Improves performance by storing frequently accessed data in memory.
- **Reflection API** – Enables inspecting and manipulating classes and methods at runtime, used for dynamic behaviors.

### 🗃 Database

- **PostgreSQL** – A powerful, open-source relational database system used in production.
- **Flyway** – A tool for versioning and automating database schema migrations.
- **H2** – A lightweight in-memory database used during testing for fast, isolated test runs.

### 🧪 Testing & Code Quality

- **JaCoCo** – A code coverage tool that tracks how much of your code is tested.
- **Static Code Analysis** – Uses Maven plugins to catch bugs, enforce coding standards, and detect potential issues automatically.

### 🌐 API & Documentation

- **Swagger UI / OpenAPI** – Automatically generated interactive documentation for your REST API. Allows developers to explore and test API endpoints in the browser.
- **Code Generator (Maven Plugin)** – Automatically generates client/server code from the OpenAPI definition, reducing boilerplate.

---

## 🔐 Keycloak Integration

**Keycloak** is an open-source identity and access management solution. It secures the application with:

- **Authentication** – Users must log in to access protected resources.
- **Authorization** – Users can access only the parts of the system allowed by their roles.

### 🔧 Access Details

- 🌍 **Admin Console**: [http://localhost:8091/](http://localhost:8091/)
- 🔑 **Admin Credentials**:
  ```text
  Username: admin
  Password: admin

### 👥 Preconfigured Realm Users
Two users are included by default:

ck example of how to insert a table into your README.md file:

| Username       | Password       | Role        | Description                              |
|----------------|----------------|-------------|------------------------------------------|
| `tatko_user`   | `tatko_user`   | `user_role` | Regular user access                      |
| `tatko_admin`  | `tatko_admin`  | `admin_role`| Admin-level access for bot management    |

These can be used to test both restricted and elevated permission levels.

---

## 🔗 API Access
All available APIs are documented and can be tested using the Swagger UI:

👉 http://localhost:8088/swagger-ui/index.html

Use your Keycloak credentials to authorize and test secured endpoints.

---

## 🚀 Deployment
All services are containerized and managed with Docker Compose, which simplifies:

Service orchestration

Dependency management

Local development setup

---

## 🧪 Quick Start

<pre>  # Clone the repo 
  git clone https://github.com/ofrolyak/TelegramBot.git 
  # Navigate into the project folder 
  cd TelegramBot 
  # Set value for TELEGRAM_BOT_NAME, TELEGRAM_BOT_OWNER, TELEGRAM_BOT_TOKEN in .env file
  # Start all services using Docker Compose 
  docker-compose up --build 
</pre>

Make sure Docker and Docker Compose are installed on your machine.

