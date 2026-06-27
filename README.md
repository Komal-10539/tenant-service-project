# 🏠 Tenant Service Backend

A production-oriented backend application for a **Tenant Management System** built with **Java, Spring Boot, Spring Security, JWT Authentication, Hibernate, and MySQL**. The application provides secure REST APIs for managing tenants, rent, complaints, notices, and dashboard information.

---

## 🚀 Project Overview

Tenant Service Backend is designed to simplify hostel/PG accommodation management by providing secure and scalable REST APIs. The application supports authentication, role-based access, complaint management, rent tracking, dashboard APIs, and notice management.

The project follows a layered architecture with clean separation of concerns using Controller, Service, Repository, DTO, Entity, and Configuration layers.

---

## ✨ Key Features

- 🔐 JWT-based Authentication & Authorization
- 👥 Role-Based Access Control
- 🏠 Tenant Dashboard APIs
- 📢 Notice Management
- 📝 Complaint Management
- 💰 Rent History & Payment Details
- 🍽️ Menu of the Day API
- 📊 Dashboard Summary APIs
- 🗄️ MySQL Database Integration
- 📑 DTO-based API Responses
- 🛡️ Global Exception Handling
- 📋 Centralized Logging using Spring AOP
- 🔄 RESTful API Design

---

## 🛠️ Tech Stack

| Technology | Version |
|------------|----------|
| Java | 17 |
| Spring Boot | 3.x |
| Spring Security | Latest |
| Spring Data JPA | Latest |
| Hibernate | Latest |
| JWT | JSON Web Token |
| Maven | Build Tool |
| MySQL | Database |
| Lombok | Code Simplification |

---

## 📂 Project Structure

```
src
 └── main
      ├── java
      │     ├── config
      │     ├── controller
      │     ├── dto
      │     ├── entity
      │     ├── repository
      │     ├── security
      │     ├── service
      │     ├── aspect
      │     ├── exception
      │     └── utility
      │
      └── resources
            ├── application.properties
            └── static
```

---

## 🔐 Authentication

The application uses **JWT Authentication**.

Workflow:

1. User logs in.
2. JWT token is generated.
3. Client sends the token in every request.
4. Spring Security validates the token.
5. Authorized APIs are accessed securely.

---

## 📌 Modules

### Authentication
- User Login
- JWT Token Generation
- Password Encryption
- Secure API Access

### Tenant Dashboard
- Tenant Details
- Accommodation Information
- Room Information
- Rent Summary
- Recent Complaints
- Notices
- Today's Menu

### Complaint Module
- Register Complaint
- View Complaint History
- Complaint Status Tracking

### Rent Module
- Rent Details
- Payment History
- Due Date Information

### Notice Module
- Latest Notices
- Announcement APIs

---

## 🏗️ Architecture

The project follows a layered architecture.

```
Controller
     │
     ▼
Service
     │
     ▼
Repository
     │
     ▼
MySQL Database
```

---

## 📡 REST API

Example Endpoints

```
POST    /auth/login

GET     /api/v1/tenant/dashboard

GET     /api/v1/tenant/rent-history

POST    /api/v1/tenant/complaints

GET     /api/v1/tenant/notices
```

---

## ▶️ Getting Started

### Clone Repository

```bash
git clone https://github.com/Komal-10539/tenant-service-project.git
```

### Configure Database

Update:

```
application.properties
```

with your own MySQL credentials.

### Build Project

```bash
mvn clean install
```

### Run Application

```bash
mvn spring-boot:run
```

---

## 📈 Future Enhancements

- Docker Support
- Redis Caching
- Email Notifications
- Swagger Documentation
- Unit Testing
- CI/CD Pipeline
- Cloud Deployment

---

## 👩‍💻 About Me

**Komal Yadav**

Java Backend Developer

Skills:
- Java
- Spring Boot
- Spring Security
- Hibernate
- JPA
- MySQL
- REST APIs
- JWT Authentication
- Git & GitHub

---

## ⭐ Note

This repository showcases the **backend development work** completed as part of a Tenant Management System project. The backend implementation, REST APIs, business logic, authentication, and database integration were developed using Spring Boot and related technologies.
