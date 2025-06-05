# medisync - Microservices-Based Healthcare Management System

**medisync** is a backend healthcare management system built using a
modern microservices architecture. The project is being
developed in **Java** with **Spring Framework**, and each microservice is responsible
for a separate domain, backed by its own dedicated MySQL database.

---

## 🚧 Development Status

⚠️ This project is currently under active development. The following services are implemented so far:

- `auth-service` – User authentication and authorization
- `doctor-service` – Doctor profile and management
- `patient-service` – Patient profile and management

---

## 🧱 System Architecture Overview

The system is structured in the following layers:

1. **Client Applications**
    - Web Application
    - Mobile Application

2. **API Gateway**
    - Handles:
        - JWT Token validation
        - Routing
        - Rate Limiting
        - Load Balancing

3. **Microservices**
    - `Auth Service`: User registration, login, password reset
    - `Patient Service`: Patient profile management
    - `Doctor Service`: Doctor availability and profile
    - `Medical Records Service` (coming soon): EHR/EMR management
    - **Each service has its own PostgreSQL database**

4. **External Storage**
    - AWS S3 (planned) for storing medical documents and images

5. **Database Layer**
    - `medisync_auth_db`, `medisync_doctor_db`, `medisync_patient_db`

---

## 🗺️ System Architecture

Below is the system architecture diagram of medisync:

![Architecture Diagram](architecture-diagram.png)


---

## 🛠️ Tech Stack

- Java 17
- Spring Framework
- MySQL
- Microservices Architecture
- REST APIs
- JWT Authentication
- Maven
- IntelliJ IDEA

---

## 📅 To Do (Planned Features)

- Add Medical Records Service (EHR/EMR)
- Add Notification Service
- Integrate Message Broker (e.g., Kafka or RabbitMQ)
- CI/CD Pipeline
- Docker & Kubernetes support

---

## 👨‍💻 Developer Note

> This is a personal learning project aimed at building backend experience using real-world architectural patterns.

---

## 📌 Notes

- Each microservice uses its own database.
- Inter-service communication is currently via REST, with messaging support planned.
- Architecture is modular and extensible.



