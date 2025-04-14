TelegramBot

TelegramBot is a modular, containerized system of services built with Docker Compose. Its core purpose is to provide a secure, extensible Telegram bot infrastructure backed by a robust set of APIs and a modern Java backend.

🧩 Core Modules
TelegramBotEngine: A Spring Boot application that implements classic Telegram Bot functionality.

CRUD API: Provides RESTful endpoints for managing business entities, documented and accessible via Swagger UI.

Security: Integrated with Keycloak (powered by Bitnami) for authentication and authorization.

⚙️ Technologies & Features
Java Platform: JDK 22

Persistence: JPA, Hibernate, DAO pattern

Database: PostgreSQL (with Flyway for migrations) and H2 (for testing)

API Design: Swagger/OpenAPI with code generation (Maven plugin)

Security: Role-based access with Keycloak

Spring Features:

AOP (Aspect-Oriented Programming)

Controller Advice & Global Exception Handling

Criteria API and Criteria Builder

Caching support

Code Quality & Testing:

Static code analysis (via Maven plugin)

Test coverage reports powered by JaCoCo

Reflection API for dynamic behavior and inspection

🔐 Keycloak Access
Keycloak is used for securing the application and managing users/roles.

🌐 Admin Console: http://localhost:8091/

🔑 Default Admin Credentials:

Username: admin

Password: admin

🔸 Preconfigured Realm
The default Keycloak realm includes the following users:

tatko_user / tatko_user – assigned the user_role role

tatko_admin / tatko_admin – assigned the admin_role role

These roles can be used to test and demonstrate role-based access across secured endpoints.

🔗 API Access
You can explore and test all available CRUD APIs via Swagger UI:

👉 http://localhost:8088/swagger-ui/index.html

🚀 Deployment
All services are containerized and managed via Docker Compose, making local development and deployment simple and reproducible.