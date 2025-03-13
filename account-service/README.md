# Project Requirements and Configurations:

## Entity Diagram:

![Model Diagram](https://github.com/souzafcharles/Microservice-Architecture-Java-Spring/blob/main/account-service/src/main/resources/static/images/customer.png)
***

## Project Logic Layered Architecture:

![Layered Architecture](https://github.com/souzafcharles/Microservice-Architecture-Java-Spring/blob/main/account-service/src/main/resources/static/images/logic-layered-architecture.png)

***

## Requirements for Spring Initializr Setup:

- **Project Metadata:**
    - Use `Maven` as the build tool.
    - Set the Java version to `21`.
    - Use Spring Boot version `3.4.3`.
    - Artifact name: account-service.
    - Define the Artifact name as `account-service`.

- **Dependencies:**
    - Include the following dependencies:
        - **Spring Web**: To enable RESTful API development.
        - **Spring Data JPA**: For database interactions.
        - **MySQL Driver**: For connecting to the MySQL database.
        - **Lombok**: To reduce boilerplate code.
        - **Spring Cloud Netflix Eureka Server**: To enable service discovery.
        - **Spring Boot Actuator**: To monitor application health and metrics.
        - **Dotenv Java**: To load environment variables dynamically from a `.env` file for secure and flexible
          configuration.
        - **ModelMapper**: For simplifying the mapping process between objects, particularly useful for DTO to Entity
          conversions.
        - **Spring Cloud LoadBalancer**: To enable client-side load balancing for service-to-service communication in
          distributed systems.

---

## Requirements for `application.yml` Configuration File:

- **Application Configuration:**
    - Define the application name under `spring.application.name` as `account-service` for service identification within
      the system.

- **Database Configuration:**
    - Configure the `datasource` properties using placeholders for sensitive information:
        - `url`: Use `${DATASOURCE_URL}` to dynamically load the database URL.
        - `username`: Use `${DATASOURCE_USERNAME}` for the database username.
        - `password`: Use `${DATASOURCE_PASSWORD}` for the database password.
    - Set the database driver class name to `com.mysql.cj.jdbc.Driver`.

- **JPA and Hibernate Configuration:**
    - Enable automatic schema updates by setting `spring.jpa.hibernate.ddl-auto` to `update`.
    - Enable SQL logging for debugging by setting `spring.jpa.show-sql` to `true`.
    - Configure Hibernate properties:
        - Set the SQL dialect to `org.hibernate.dialect.MySQLDialect` for MySQL compatibility.
        - Enable formatted SQL output by setting `hibernate.format_sql` to `true`.
        - Ensure that SQL statements are displayed in logs by setting `hibernate.show_sql` to `true`.

- **Server Configuration:**
    - Set the server port to `8000` using `server.port`.

- **Eureka Client Configuration:**
    - Configure the Eureka client to register with the service registry:
        - Define the Eureka server URL under `server.eureka.client.service-url.defaultZone` as
          `http://localhost:8761/eureka`.

- **Security and Placeholder Usage:**
    - Use placeholders `${DATASOURCE_URL}`, `${DATASOURCE_USERNAME}`, and `${DATASOURCE_PASSWORD}` to externalise
      sensitive information, ensuring security and flexibility across environments.

---

## Creation of the `.env` File:

- At the root of the project, create a file named `.env` to declare the environment variables required for the
  `MySQL` database connection.

***

## Requirements for LoadEnvironment Class:

- **Class Purpose:**
    - Create the `LoadEnvironment` class to load environment variables from a `.env` file and set them as system
      properties.

- **Load Environment Method:**
    - **Method:** `loadEnv`
    - **Purpose:** Loads environment variables from a `.env` file and sets them as system properties.
    - **Implementation Details:**
        - Use the `Dotenv.configure().load()` method from the `io.github.cdimascio.dotenv` library to load the
          environment variables.
        - Iterate over the entries using
          `dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()))` to set each
          environment variable as a system property.

- **External Library:**
    - **Library:** `io.github.cdimascio.dotenv`
    - **Purpose:** Used to load environment variables from a `.env` file. Ensure this library is included as a
      dependency in your project's build configuration.

- **Purpose:**
    - Ensure that environment variables defined in a `.env` file are loaded and accessible as system properties
      throughout the application.

***

# Backend Requirements Specification:

## Requirements for `AccountServiceApplication` Class:

- **Spring Boot Application Configuration:**
    - Annotate the class with `@SpringBootApplication` to enable component scanning and auto-configuration.

- **Eureka Server Integration:**
    - Annotate the class with `@EnableEurekaServer` to enable the service to act as a Eureka service registry.

- **Main Method:**
    - Define the `main` method as the entry point of the application.
    - Call `LoadEnvironment.loadEnv()` to load environment variables before starting the Spring application context.
    - Use `SpringApplication.run(AccountServiceApplication.class, args)` to launch the application.

- **Dependency Management:**
    - Ensure that the `dotenv` library is included in the project's dependencies to enable environment variable loading.
    - Verify that the Eureka Server dependencies are correctly included in the project's build file.

## Requirements for `Customer` Entity Class:

- **Entity Mapping:**
    - Define the `Customer` class as an entity to represent a database table.
    - Annotate the class with `@Entity` to designate it as a persistent entity.
    - Use `@Table(name = "tb_customer")` to map the entity to the database table named `tb_customer`.

- **Attributes and Annotations:**
    - Declare attributes `id`, `accountNumber`, `cpf`, `fullName`, and `enable` to represent the respective database
      columns.
    - Annotate the `id` field with `@Id` and `@GeneratedValue(strategy = GenerationType.IDENTITY)` to specify it as the
      primary key with an automatically generated value.
    - Use `@Column(name = "account_number", nullable = false, unique = true)` on `accountNumber` to enforce uniqueness
      and non-null constraints.
    - Annotate `cpf` with `@Column(nullable = false, unique = true, length = 11)` to ensure uniqueness, non-null values,
      and a maximum length of 11 characters.
    - Apply `@Column(name = "full_name", nullable = false, length = 100)` on `fullName` to enforce a non-null constraint
      and a maximum length of 100 characters.
    - Use `@Column(nullable = false)` on `enable` to ensure that it cannot be null.

- **Accessors and Mutators:**
    - Implement `getters` and `setters` for all attributes to facilitate data manipulation.

- **Serializable Interface:**
    - Ensure the entity implements the `Serializable` interface to enable object serialisation for transmission or
      storage.

- **Database Constraints:**
    - Enforce unique and not blank constraints on the `accountNumber` and `cpf` columns.
    - Ensure the `fullName` column enforces a maximum length of 100 characters and is not blank.
    - Ensure the `enable` column enforces a non-null value.

- **Constructor:**
    - Implement a default constructor for the entity.

- **Equals and HashCode:**
    - Override the `equals()` method to compare instances based on the `id` attribute.
    - Override `hashCode()` to generate a consistent hash code using `Objects.hashCode(id)`.

---

## Requirements for `ResponseDTO` Class:

- **Attributes:**
    - Declare attributes `data`, `message`, and `port` to represent the fields of the DTO.
    - Annotate the `message` field with `@JsonInclude(JsonInclude.Include.NON_NULL)` to exclude it from serialisation
      when its value is null.

- **Serializable Interface:**
    - Ensure the class implements the `Serializable` interface to enable object serialisation for transmission or
      storage.

- **Builder Pattern:**
    - Use `@Builder` annotation to provide an easy way to construct `ResponseDTO` objects.

---

## Requirements for `CustomerDTO` Class:

- **Attributes:**
    - Declare attributes `cpf` and `fullName` to represent the fields of the DTO.

- **Serializable Interface:**
    - Ensure the class implements the `Serializable` interface to enable object serialisation for transmission or
      storage.

---

## Requirements for `CustomerResponseDTO` Class:

- **Attributes:**
    - Inherit attributes `cpf` and `fullName` from `CustomerDTO`.
    - Add an additional attribute `accountNumber` to represent the account number field.

- **Serializable Interface:**
    - Ensure the class implements the `Serializable` interface to enable object serialisation for transmission or
      storage.

- **Inheritance:**
    - Use `@EqualsAndHashCode(callSuper = true)` to ensure proper inheritance behaviour.

---

## Requirements for `CustomerRepository` Interface:

- **Repository Role:**
    - Define the `CustomerRepository` interface to act as a Spring Data JPA repository for managing `Customer` entities.

- **Inheritance:**
    - Extend `JpaRepository<Customer, Long>` to inherit CRUD operations and pagination functionality.

- **Custom Query Method:**
    - Implement a custom query method `Optional<Customer> findByAccountNumber(String accountNumber)` to retrieve a
      `Customer` entity based on its `accountNumber`.

- **Annotations:**
    - Annotate the interface with `@Repository` to designate it as a Spring component for persistence operations.

---

## Requirements for `CustomerService` Class:

- **Service Layer Definition:**
    - Annotate the class with `@Service` to designate it as a Spring Service component responsible for business logic.

- **Serializable Interface:**
    - Ensure the class implements the `Serializable` interface to support object serialisation.

- **Dependency Injection:**
    - Use `@Autowired` to inject the dependencies `CustomerRepository` and `ModelMapper` for data access and object
      mapping.

- **Transactional Management:**
    - Annotate methods with `@Transactional` to manage transaction boundaries:
        - Use `@Transactional` for write operations (`create`, `update`, `delete`) to ensure data integrity.
        - Use `@Transactional(readOnly = true)` for read operations (`readAll`, `readOne`, `isValid`) to optimise
          performance.

- **CRUD Operations:**
    - Implement methods for creating, reading, updating, and deleting customer data:
        - `create(CustomerDTO dto)` for adding a new customer entity and returning a `CustomerResponseDTO`.
        - `readAll()` to retrieve a list of all customers as `CustomerResponseDTO`.
        - `readOne(Long id)` to fetch a specific customer based on its ID.
        - `update(Long id, CustomerDTO dto)` to modify an existing customer's details.
        - `delete(Long id)` to remove a customer entity, with error handling for related data.

- **Validation Logic:**
    - Implement `isValid(String account)` to verify if the provided account number is valid and return the respective
      customer data.

- **Exception Handling:**
    - Throw `CustomerNotFoundException` when a customer is not found in `readOne`, `update`, or `delete`.
    - Throw `InvalidAccountException` for invalid account validation in `isValid`.
    - Handle `DataIntegrityViolationException` in `delete` to manage database constraints.

---

## Requirements for `BaseController` Class:

- **Controller Layer Definition:**
    - Define the class as a base controller for shared methods across other controllers.

- **Serializable Interface:**
    - Ensure the class implements the `Serializable` interface to support object serialisation.

- **Environment Access:**
    - Inject `Environment` using `@Autowired` to access application properties.

- **Reusable Response Methods:**
    - Implement protected methods for consistent HTTP responses:
        - `getResponseError(String message, HttpStatus status)` to return an error response.
        - `getResponseSuccess(Object data, HttpStatus status)` to return a success response with data.
        - `getResponseSuccess(Object data, String message, HttpStatus status)` to return a success response with data
          and a message.

---

## Requirements for `CustomerController` Class:

- **Controller Layer Definition:**
    - Annotate the class with `@RestController` to designate it as a RESTful controller.
    - Use `@RequestMapping(value = "/customers")` to define the base endpoint for the controller.

- **Cross-Origin Support:**
    - Annotate with `@CrossOrigin(origins = "*", allowedHeaders = "*")` to allow cross-origin requests.

- **Serializable Interface:**
    - Ensure the class implements the `Serializable` interface to support object serialisation.

- **Dependency Injection:**
    - Use `@Autowired` to inject the `CustomerService` dependency.

- **HTTP Endpoints:**
    - Implement endpoints for CRUD operations and validation:
        - `@PostMapping` for creating a new customer (`create` method).
        - `@GetMapping` to fetch all customers (`readAll` method).
        - `@GetMapping(value = "/{id}")` to fetch a specific customer by ID (`readOne` method).
        - `@PutMapping(value = "/{id}")` to update a customer's details (`update` method).
        - `@DeleteMapping(value = "/{id}")` to delete a customer by ID (`delete` method).
        - `@GetMapping("/validate/{account}")` to validate a customer's account (`isValid` method).

- **Response Handling:**
    - Extend the `BaseController` class to use reusable response methods for success and error handling.

---

## Requirements for `BeanConfig` Class:

- **Configuration Class:**
    - Annotate the class with `@Configuration` to indicate that it provides Spring configuration.

- **Bean Definitions:**
    - Define the following beans using the `@Bean` annotation:
        - `ModelMapper`: Return a new instance of `ModelMapper` to facilitate object mapping.
        - `Random`: Return a new instance of `Random` for random number generation.

---

## Requirements for `AccountNumberGenerator` Class:

- **Utility Class Definition:**
    - Define the `AccountNumberGenerator` class as a utility class for generating account numbers.

- **Attributes:**
    - Declare a `SecureRandom` instance (`random`) as a private static final field to generate secure random numbers.
    - Define `PREFIX` as a private static final string constant with the value `"8899"` for the fixed starting sequence
      of account numbers.
    - Declare `ACCOUNT_LENGTH` as a private static final integer constant with the value `6` to specify the length of
      the random numeric part of the account number.

- **Constructor:**
    - Implement a private constructor to prevent instantiation and enforce the utility nature of the class.
    - Throw an `IllegalStateException` with the message `"Utility class, cannot be instantiated."`.

- **Account Number Generation:**
    - Implement a public static method `generate()` to create a new account number:
        - Initialise a `StringBuilder` with the `PREFIX`.
        - Append six random numeric digits (generated using `random.nextInt(10)`) to the `StringBuilder`.
        - Return the completed account number as a string.

---

## Requirements for `CustomerMessages` Class:

- **Utility Class Definition:**
    - Define the `CustomerMessages` class as a utility class for storing predefined messages related to customer
      operations.

- **Serializable Interface:**
    - Ensure the class implements the `Serializable` interface to enable object serialisation.

- **Constructor:**
    - Implement a private constructor to prevent instantiation and enforce the utility nature of the class.
    - Throw an `IllegalStateException` with the message `"Utility class, cannot be instantiated."`.

- **Static Constants:**
    - Define public static final string constants for common messages:
        - `CUSTOMER_ACCOUNT_CREATED_SUCCESSFULLY`: `"Customer account created successfully!"`
        - `CUSTOMER_NOT_FOUND`: `"Customer not found with ID: "`
        - `CUSTOMER_ACCOUNT_UPDATE_SUCCESS`: `"Customer account updated successfully!"`
        - `CUSTOMER_ACCOUNT_DELETE_SUCCESS`: `"Customer account deleted successfully!"`
        - `CUSTOMER_DELETE_RELATED_DATA`: `"Cannot delete customer with ID %d due to related data!"`
        - `ACCOUNT_NOT_VALID`: `"This account %s is not valid!"`

- **Readability and Reusability:**
    - Ensure all constants are descriptive, concise, and reusable across the application.
    - Maintain immutability by using `final` for all fields.

---

## Requirements for `GlobalExceptionHandler` Class:

- **Exception Handling:**
    - Annotate the class with `@RestController` and `@ControllerAdvice` to handle exceptions globally in the
      application.

- **Environment Access:**
    - Inject the `Environment` class using constructor injection to access application properties.

- **Utility Method:**
    - Implement a private method `getPort()` to retrieve the server port from the environment properties.

- **Exception Handlers:**
    - Implement the following methods annotated with `@ExceptionHandler` to handle various exceptions:
        - `handleGenericException(Exception ex)`:
            - Handles all generic exceptions and returns an HTTP 500 (Internal Server Error) status.
            - Uses the `ResponseDTO` to include the error message and server port in the response body.
        - `handleCustomerNotFoundException(CustomerNotFoundException ex)`:
            - Handles `CustomerNotFoundException` and returns an HTTP 404 (Not Found) status.
            - Uses the `ResponseDTO` to include the exception message and server port in the response body.
        - `handleDataIntegrityViolationException(DataIntegrityViolationException ex)`:
            - Handles `DataIntegrityViolationException` and returns an HTTP 409 (Conflict) status.
            - Provides a default message indicating deletion is not possible due to related data.
        - `handleInvalidAccountException(InvalidAccountException ex)`:
            - Handles `InvalidAccountException` and returns an HTTP 404 (Not Found) status.
            - Uses the `ResponseDTO` to include the exception message and server port in the response body.

---

## Requirements for `CustomerNotFoundException` Class:

- **Custom Exception Definition:**
    - Extend the `RuntimeException` class to define a custom unchecked exception.
    - Implement a constructor that accepts a `String` message and passes it to the superclass.

---

## Requirements for `InvalidAccountException` Class:

- **Custom Exception Definition:**
    - Extend the `RuntimeException` class to define a custom unchecked exception.
    - Implement a constructor that accepts a `String` message and passes it to the superclass.