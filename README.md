# Task Management Application - Backend

## Table of Contents

1. [Overview](#overview)
2. [Features](#features)
3. [Tech Stack](#tech-stack)
4. [Getting Started](#getting-started)
   - [Prerequisites](#prerequisites)
   - [Installation](#installation)
   - [Running Locally](#running-locally)
5. [API Documentation](#api-documentation)
6. [Testing](#testing)
7. [Dockerization](#dockerization)
8. [Future Enhancements](#future-enhancements)


## Overview
This repository contains the backend of the Task Management Application. Built using Spring Boot, it provides RESTful APIs for managing tasks such as creating, reading, updating, and deleting tasks. The backend integrates with a MySQL database for persistent storage and includes robust error handling mechanisms.

## Features
CRUD Operations: Full Create, Read, Update, and Delete support for tasks.
RESTful APIs: Simplified integration with documented APIs using Swagger/OpenAPI.
Database Integration: Persistent task storage using MySQL.
Error Handling: Graceful handling of invalid inputs and missing resources.

## Tech Stack
**Spring Boot**: Backend framework for developing RESTful APIs.  
**MySQL**: Relational database for data persistence.  
**Swagger/OpenAPI**: API documentation for easy developer integration.  
**JUnit**: Framework for unit and integration tests.    
**Gradle**: Build tool for managing dependencies and automating tasks.    
**ThunderClient**: Lightweight REST client (VS Code extension) for manual API testing and debugging.  

## Getting Started
### Prerequisites
Java: Version 17 or above.  
Gradle: Installed globally or use the wrapper included in the project.  
MySQL: Ensure the MySQL service is running, or use a Dockerized MySQL container.  
Docker: For containerized deployment.  
### Installation

#### Clone the repository:

git clone https://github.com/Malatesh-Patil-67/taskmanagement.git  
cd taskmanagement

#### Set up the MySQL database:
    - Create a database named `celonis`:
    
    CREATE DATABASE celonis
    
    - Ensure your MySQL credentials match those in the `application.properties` file.

#### Install dependencies and build the project:
    
    ./gradlew build
    

### Running Locally

1. Update the `application.properties` file (found in `src/main/resources`) to match your local database credentials:
   ```
        server.port=8081  
        spring.datasource.url=jdbc:mysql://localhost:3306/celonis  
        spring.datasource.username= “YOUR-MYSQL-USERNAME”  
        spring.datasource.password=“YOUR-MYSQL-PASSWORD”  
        spring.jpa.hibernate.ddl-auto=update  
        spring.jpa.show-sql=true  
      
3. Start the application:
    
    ./gradlew bootRun
    
4. Access the application:
    - Base URL: `http://localhost:8081`
    - Swagger UI: `http://localhost:8081/swagger-ui.html`

---

###  API Documentation

The APIs are documented using Swagger. To view the Swagger UI, start the backend application and navigate to:

- `http://localhost:8081/swagger-ui.html`

### Example API

GET /tasks

- **Description**: Fetch all tasks.
- **Success Response**:
  ```
  [
    {
      "id": 1,
      "title": "Task 1",
      "description": "Description for Task 1",
      "status": "Pending",
      "createdAt": "2024-11-17",
      "updatedAt": "2024-11-17"
    }
  ]

--For the full API reference, visit the Swagger UI.

---

###  Testing

The backend is fully covered with unit and integration tests using **JUnit**.

### Test Coverage

- **Controller Tests**: Ensure API endpoints behave as expected (e.g., `TaskControllerTest`).
- **Service Tests**: Validate the business logic (e.g., `TaskServiceTest`).

### Running Tests

-- To execute all tests, run:

    ./gradlew test

---

###  Dockerization

The backend can be containerized using Docker.

### Build Docker Image
  
      docker build -t taskmanagement .  

###  Run Docker Container

    docker run -d -p 8081:8080 --name taskmanagement

Ensure MySQL is running and accessible from the container.

---

###  Future Enhancements

- **Pagination**: Implement pagination for the `GET /tasks` endpoint when the dataset grows large.
- **Authentication**: Secure endpoints with role-based access control.
- **Caching**: Implement caching mechanisms to optimize frequently accessed data.
