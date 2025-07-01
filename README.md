# medisync - sample microservices-based healthcare system


medisync is a sample backend system for managing healthcare data, built in Java with Spring. It includes separate microservices with their own MySQL databases, and a module that translates natural language requests into API calls using an LLM.

---

## Services

- `Auth Service`: User registration, login, password reset
- `Patient Service`: Patient profile management
- `Doctor Service`: Doctor availability and profile
- `Appointment Service`: Scheduling and managing appointments
- `AI Assistant Service`: Natural language processing and REST API mapping
- **Each service has its own database**


---

## System Architecture Overview

The system is structured in the following layers:

1. **Client Applications**
    - Web Application
    - Mobile Application

2. **Service Discovery**
    - **Eureka Server**: Central registry for all microservices

3. **API Gateway**
    - Handles:
        - JWT Token validation
        - Routing
        - Rate Limiting
        - Load Balancing
        - Service Discovery integration



5. **External Storage**
    - AWS S3 (planned) for storing medical documents and images

6. **Database Layer**
    - Individual databases per service: `medisync_auth_db`, `medisync_doctor_db`, `medisync_patient_db`, `medisync_appointments_db`

---

## 🗺️ System Architecture

Below is the system architecture diagram of medisync:

![Architecture Diagram](architecture-diagram.png)

---

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Cloud Netflix Eureka**
- **Spring Cloud Gateway**
- **Spring Data JPA**
- **MySQL** 
- **Maven**
- 
---

## 🏗️ Current Microservices

### 1. Eureka Server
- **Purpose**: Service discovery and registration
- **Port**: 8761 (default)
- **Features**: 
  - Central registry for all microservices
  - Health monitoring
  - Load balancing support

### 2. API Gateway
- **Purpose**: Single entry point for all client requests
- **Features**:
  - Request routing to appropriate microservices
  - JWT token validation
  - Rate limiting
  - Load balancing
  - Service discovery integration

### 3. Auth Service
- **Purpose**: User authentication and authorization
- **Features**:
  - User registration and login
  - JWT token generation and validation
  - Password management
  - Role-based access control

### 4. Doctor Service
- **Purpose**: Doctor profile and management
- **Features**:
  - Doctor profile management
  - Availability scheduling
  - Specialization management

### 5. Patient Service
- **Purpose**: Patient profile and management
- **Features**:
  - Patient profile management
  - Medical history tracking
  - Personal information management

### 6. AI Assistant Service
- **Purpose**: Simplifies user interaction through natural language processing
- **Features**:
  - Accepts natural language requests (e.g., "get all users")
  - Maps requests to appropriate REST API endpoints using an LLM
  - Sends HTTP requests to internal microservices
  - Formats and returns structured responses to users

---

## 📅 To Do (Planned Features)

- Add Medical Records Service (EHR/EMR)
- Add Appointment Service
- Add Notification Service
- Integrate Message Broker (e.g., Kafka or RabbitMQ)
- CI/CD Pipeline
- Docker & Kubernetes support
- Monitoring and Logging (ELK Stack)
- Database migration to PostgreSQL

---

## 🚀 Getting Started

### Prerequisites
- Java 17
- Maven 3.6+
- MySQL 8.0+
- IntelliJ IDEA (recommended)

### Running the Services

1. **Start Eureka Server**
   ```bash
   cd eureka-server
   mvn spring-boot:run
   ```

2. **Start API Gateway**
   ```bash
   cd api-gateway
   mvn spring-boot:run
   ```

3. **Start Microservices**
   ```bash
   # Auth Service
   cd auth-service
   mvn spring-boot:run
   
   # Doctor Service
   cd doctor-service
   mvn spring-boot:run
   
   # Patient Service
   cd patient-service
   mvn spring-boot:run
   
   # AI Assistant Service
   cd ai-assistant-service
   mvn spring-boot:run
   ```

### Service URLs
- Eureka Dashboard: `http://localhost:8761`
- API Gateway: `http://localhost:8080`
- Auth Service: `http://localhost:8081`
- Doctor Service: `http://localhost:8082`
- Patient Service: `http://localhost:8083`
- AI Assistant Service: `http://localhost:8084`

---

Enjoy, and feel free to contribute!
