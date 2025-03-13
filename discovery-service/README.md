# Project Requirements and Configurations:

## Requirements for Spring Initializr Setup:

- **Project Metadata:**
    - Use `Maven` as the build tool.
    - Set the Java version to `21`.
    - Use Spring Boot version `3.4.3`.
    - Define the Artifact name as `discovery-service`.

- **Dependencies:**
    - Include the following dependencies:
        - **Spring Cloud Netflix Eureka Server**: For enabling the application to act as a Eureka Server.

---

## Requirements for `application.yml` Configuration File:

- **Server Configuration:**
    - Set the server port to `8761` using `server.port`.

- **Application Configuration:**
    - Define the application name under `spring.application.name` as `discovery-service`.

- **Eureka Client Configuration:**
    - Configure the Eureka client with the following settings:
        - `register-with-eureka`: Set to `false` to prevent the application from registering with Eureka;
        - `fetch-registry`: Set to `false` to disable fetching the Eureka registry.

---

## Requirements for `DiscoveryServiceApplication` Class:

- **Class Definition:**
    - Annotate the class with `@SpringBootApplication` to indicate it as the main entry point for the Spring Boot
      application;
    - Annotate the class with `@EnableEurekaServer` to enable the application to function as a Eureka Server for service
      discovery.

- **Main Method:**
    - Implement the `public static void main(String[] args)` method as the application's entry point;
    - Use `SpringApplication.run(DiscoveryServiceApplication.class, args)` to launch the Spring Boot application.
