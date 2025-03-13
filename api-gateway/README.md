# Project Requirements and Configurations:

## Requirements for Spring Initializr Setup:

- **Project Metadata:**
    - Use `Maven` as the build tool.
    - Set the Java version to `21`.
    - Use Spring Boot version `3.4.3`.
    - Define the Artifact name as `api-gateway`.

- **Dependencies:**
    - Include the following dependencies:
        - **Spring Cloud Starter Gateway**: For setting up a Gateway service.
        - **Spring Cloud Starter Netflix Eureka Client**: To enable the application to register with the Eureka Server.

---

## Requirements for `application.yml` Configuration File:

- **Server Configuration:**
    - Set the server port to `8765` using `server.port`.

- **Eureka Client Configuration:**
    - Configure the Eureka client to register with the service registry:
        - Define the Eureka server URL under `eureka.client.service-url.defaultZone` as `http://localhost:8761/eureka`.

- **Application Configuration:**
    - Define the application name under `spring.application.name` as `api-gateway`.

---

## Requirements for `ApiGatewayApplication` Class:

- **Class Definition:**
    - Annotate the class with `@SpringBootApplication` to indicate it as the main entry point for the Spring Boot
      application.

- **Main Method:**
    - Implement the `public static void main(String[] args)` method as the application's entry point;
    - Use `SpringApplication.run(ApiGatewayApplication.class, args)` to launch the Spring Boot application.

---

## Requirements for `GatewayConfiguration` Class:

- **Class Definition:**
    - Annotate the class with `@Configuration` to define it as a configuration class.

- **Route Configuration:**
    - Define static service URIs:
        - `ACCOUNT_SERVICE` as `lb://account-service`.
        - `TRANSACTION_SERVICE` as `lb://transaction-service`.
    - Create routing rules using a `RouteLocator` bean:
        - Route `/customers/**` to `ACCOUNT_SERVICE`.
        - Route `/transactions/**` to `TRANSACTION_SERVICE`.
        - Route `/transfers/**` to `TRANSACTION_SERVICE`.