![GitHub language count](https://img.shields.io/github/languages/count/souzafcharles/Microservice-Architecture-Java-Spring)
![GitHub top language](https://img.shields.io/github/languages/top/souzafcharles/Microservice-Architecture-Java-Spring)
![GitHub](https://img.shields.io/github/license/souzafcharles/Microservice-Architecture-Java-Spring)
![GitHub last commit](https://img.shields.io/github/last-commit/souzafcharles/Microservice-Architecture-Java-Spring)

# Course: Microservices with Spring Boot, Spring Cloud, and Netflix Eureka

***
▶️ Presented by Genilson Medeiros Martins | GM2 - Training and Consulting.
***

## System Overview:

<p align="justify">
This system implements a microservices architecture for managing bank accounts and transactions, as depicted in the provided diagram. It leverages the capabilities of the <code>Spring Boot</code> framework, incorporating key technologies such as <code>Spring Cloud</code> and <code>Netflix Eureka</code> to deliver a scalable and resilient solution.
</p>

<p align="justify">
The system employs a layered architecture, with the API Gateway providing an abstraction layer between clients and microservices. Service discovery and load balancing are crucial for ensuring scalability and high availability. The use of separate databases (<code>MySQL</code> and <code>MongoDB</code>) reflects the polyglot persistence approach, where each microservice can choose the most appropriate database technology.
</p>

***
## Project Microservice Architecture:

![Microservice Architecture](https://github.com/souzafcharles/Microservice-Architecture-Java-Spring/blob/main/account-service/src/main/resources/static/images/microservice-architecture.png)

The architecture comprises the following key components:

* [account-service:](https://github.com/souzafcharles/Microservice-Architecture-Java-Spring/tree/main/account-service)
    * Manages customer account data stored in a MySQL database;
    * Provides RESTful APIs for account creation, retrieval, and validation;
    * Operates with redundancy across ports 8000 and 8001.
* [transaction-service:](https://github.com/souzafcharles/Microservice-Architecture-Java-Spring/tree/main/transaction-service)
    * Handles bank transactions, storing transaction data in MongoDB;
    * Offers APIs for deposits, withdrawals, and inter-account transfers;
    * Utilises OpenFeign to communicate with the Account Service for account validation;
    * Operates with redundancy across ports 8080 and 8081.
* [discovery-service:](https://github.com/souzafcharles/Microservice-Architecture-Java-Spring/tree/main/api-gateway)
    * Uses Netflix Eureka to enable service registration and discovery;
    * Facilitates dynamic service location and load balancing;
    * Operates on port 8761.
* [api-gateway:](https://github.com/souzafcharles/Microservice-Architecture-Java-Spring/tree/main/api-gateway)
    * Acts as a single entry point for client requests;
    * Routes requests to the appropriate microservices;
    * Operates on port 8765.
* **load-balancing:**
    * Distributes incoming traffic across multiple instances of the Account and Transaction services to enhance
      performance and availability.
* **Client Apps:**
    * Represents client devices (e.g., desktops, mobile phones) that interact with the system via the API Gateway.

***

## Project Stack:

| Technology        | Version    | Description                                        |
|-------------------|------------|----------------------------------------------------|
| 📐 IntelliJ IDEA  | `2024.3`   | Integrated Development Environment (IDE)           |
| ☕ Java           | `21`       | Backend programming language                       |
| 🌱 Spring Boot    | `3.4.3`    | Framework for creating Spring applications         |
| 🐦 Maven          | `3.9.9`    | Build automation and dependency management tool    |
| 🐬 MySQL          | `9.2.0`    | Open-source relational database management system  |
| 🍃 MongoDB        | `8.0`      | NoSQL document-oriented database                   |
| ☁️ Spring Cloud   | `2024.0.0` | Framework for building cloud-native applications   |
| 🧭 Netflix Eureka | `3.0.0`    | Service registry and discovery server              |
| 🔗 OpenFeign      | `4.2.0`    | Declarative REST client                            |
| 🔄 Resilience4j   | `2.3.0`    | Fault tolerance library for microservices          |
| 👩‍🚀 Postman        | `11.19`    | API testing and development tool                   |

***

## Dependencies

| Dependency               | Category         | Description                                                                                   |
|--------------------------|------------------|-----------------------------------------------------------------------------------------------|
| 🌐 Spring Web            | Web              | Build web, including RESTful, applications using Spring MVC                                   |
| 💾 Spring Data JPA       | SQL              | Simplifies database interactions using JPA with Spring Data and Hibernate                     |
| 🐘 MySQL Driver          | SQL              | Provides connectivity between Java applications and MySQL databases                           |
| 🍃 Spring Data MongoDB   | NoSQL            | Provides integration with MongoDB, allowing data access in a reactive or blocking way         |
| 🌶️ Lombok                | Developer Tools  | A Java annotation library that minimises boilerplate code                                     |
| 🔗 OpenFeign             | Cloud            | Simplifies HTTP API client creation with declarative REST clients                             |
| 🧭 Netflix Eureka Server | Cloud            | Provides a service registry for microservices architecture using Eureka Server                |
| ⚖️ Load Balancer         | Cloud            | Provides client-side load balancing                                                           |
| 🚪 Gateway               | Cloud            | Provides a library for building API Gateways on top of Spring WebFlux                         |
| 🗺️ ModelMapper           | Utility          | A library that simplifies object mapping by automatically mapping properties between objects  |
| 🔄 Resilience4j          | Fault Tolerance  | Provides circuit breakers, rate limiters, retries, and bulkheads for microservices            |
| ▶ Actuator               | Monitoring       | Provides production-ready features for monitoring and managing applications                   |
| 🔍 AOP                   | Aspect-Oriented  | Enables aspect-oriented programming with Spring AOP and AspectJ                               |
| 🗝️ dotenv-java           | Configuration    | Loads environment variables from a `.env` file, aiding secure configuration management        |

***

## Contents:

### Module 1

- [x] 1.0 Introduction;
- [x] 1.1 Tools;
- [x] 1.2 Monolithic Architecture;
- [x] 1.3 Microservices Architecture.

### Module 2

- [x] 2.1 Monolithic Architecture;
- [x] 2.2 Microservice Architecture.

### Module 3

(Microservice 1 - Bank Account)

- [x] 3.1 IDE Configuration;
- [x] 3.2 Project Creation;
- [X] 3.3 Structuring and Entity;
- [X] 3.4 Customer Repository;
- [X] 3.5 Customer Service;
- [X] 3.6 Customer Controller;
- [X] 3.7 Standardising Responses;
- [X] 3.8 Automatic Account Number Generator;
- [X] 3.9 Retrieving All User Accounts;
- [X] 3.10 Account Validation;
- [X] 3.11 Custom Exception;
- [X] 3.12 Configuring Redundancy for the Microservice.

### Module 4

(Microservice 2 - Bank Transactions)

- [X] 4.1 Microservice Creation and Structuring;
- [X] 4.2 MongoDB Configuration and Entities;
- [X] 4.3 Repositories;
- [X] 4.4 Transaction Controller;
- [X] 4.5 Transaction Service;
- [X] 4.6 Using OpenFeign to Validate Customer Account;
- [X] 4.7 Custom Exception for Account Validation;
- [X] 4.8 Calculating Account Balance;
- [X] 4.9 Implementing Account Statement;
- [X] 4.10 Transfer Controller;
- [X] 4.11 Inter-Account Transfer;
- [X] 4.12 Transfer Statement Business Logic;
- [X] 4.13 Statement for Destination Account;
- [X] 4.14 Retrieving All Account Transfers.

### Module 5

- [X] 5.1 Introduction to Discovery Service;
- [X] 5.2 Implementing Discovery Service and Registering Microservices;
- [X] 5.3 Load Balancing.

### Module 6

- [X] 6.1 Introduction to API Gateway;
- [X] 6.2 API Gateway Project Creation and Configuration;
- [X] 6.3 Testing the API Gateway Service;
- [X] 6.4 Configuring Routes.

### Module 7

- [X] 7.1 Retry with Resilience4j;
- [X] 7.2 Retry Attempts;
- [X] 7.3 Fallback Method - Custom Exception;
- [X] 7.4 Wait Duration.

✅ [CERTIFICATE OF ACHIEVEMENT](https://www.udemy.com/certificate/UC-e307d4c6-ad49-42bd-bbb3-eae9e54b3f1d/)