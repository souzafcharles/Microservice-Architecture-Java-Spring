# Project Requirements and Configurations:

## Entity Diagram:

![Model Diagram](https://github.com/souzafcharles/Microservice-Architecture-Java-Spring/blob/main/transaction-service/src/main/resources/static/images/transaction.png)
***

## Project Logic Layered Architecture:

![Layered Architecture](https://github.com/souzafcharles/Microservice-Architecture-Java-Spring/blob/main/transaction-service/src/main/resources/static/images/logic-layered-architecture.png)

***

## Requirements for Spring Initializr Setup:

- **Project Metadata:**
    - Use `Maven` as the build tool.
    - Set the Java version to `21`.
    - Use Spring Boot version `3.4.3`.
    - Define the Artifact name as `transaction-service`.

- **Dependencies:**
    - Include the following dependencies:
        - **Spring Boot Starter Data MongoDB**: For MongoDB database interactions.
        - **Spring Web**: To enable RESTful API development.
        - **Spring Boot Actuator**: To monitor application health and metrics.
        - **Spring Boot Starter AOP**: For aspect-oriented programming.
        - **Spring Cloud Netflix Eureka Server**: To enable service discovery.
        - **Spring Cloud OpenFeign**: For declarative REST clients.
        - **Spring Cloud LoadBalancer**: To enable client-side load balancing for service-to-service communication in
          distributed systems.
        - **Resilience4j**: To implement fault-tolerance mechanisms such as retries and circuit breakers.
        - **Dotenv Java**: To dynamically load environment variables from a `.env` file for secure and flexible
          configuration.
        - **ModelMapper**: For simplifying the mapping process between objects, particularly useful for DTO to Entity
          conversions.
        - **Lombok**: To reduce boilerplate code.

---

## Requirements for `application.yml` Configuration File:

- **Application Configuration:**
    - Define the application name under `spring.application.name` as `transaction-service` for service identification
      within the system.

- **MongoDB Configuration:**
    - Configure the MongoDB connection using placeholders for sensitive information:
        - `uri`: Use `${MONGODB_URI}` to dynamically load the MongoDB connection URI.

- **Server Configuration:**
    - Set the server port to `8080` using `server.port`.

- **Eureka Client Configuration:**
    - Configure the Eureka client to register with the service registry:
        - Define the Eureka server URL under `server.eureka.client.service-url.defaultZone` as
          `http://localhost:8761/eureka`.

- **Resilience4j Configuration:**
    - Configure retry settings for fault tolerance:
        - Default settings:
            - `max-attempts`: Set to `4`.
        - Account-specific settings:
            - `max-attempts`: Set to `5`.
            - `wait-duration`: Set to `5s` (5 seconds).

- **Security and Placeholder Usage:**
    - Use `${MONGODB_URI}` to externalise sensitive MongoDB connection details for improved security and flexibility
      across environments.

---

## Creation of the `.env` File:

- At the root of the project, create a file named `.env` to declare the environment variables required for the MongoDB
  connection.

---

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

## Requirements for `TransactionServiceApplication` Class:

- **Class Definition:**
    - Annotate the class with `@SpringBootApplication` to indicate it as the main entry point for the Spring Boot
      application;
    - Annotate with `@EnableEurekaServer` to enable the application to function as a Eureka Server for service
      discovery;
    - Annotate with `@EnableFeignClients` to enable Feign-based HTTP communication with external services.

- **Environment Loading:**
    - Include a static call to `LoadEnvironment.loadEnv()` in the `main` method to load custom environment settings at
      the application startup.

- **Main Method:**
    - Implement the `public static void main(String[] args)` method as the application's entry point;
    - Use `SpringApplication.run(TransactionServiceApplication.class, args)` to launch the Spring Boot application.

---

## Requirements for `Transaction` Entity Class:

- **Entity Mapping:**
    - Define the `Transaction` class as a MongoDB document to represent a database collection;
    - Annotate the class with `@Document(collection = "transaction")` to map the entity to the database collection named
      `transaction`.

- **Attributes and Annotations:**
    - Declare attributes `id`, `accountNumber`, `date`, `operation`, and `value` to represent the respective database
      fields;
    - Annotate the `id` field with `@Id` to specify it as the primary key;
    - Annotate the `accountNumber` field with `@Field("account_number")` to map it to the respective database field with
      a custom name;
    - Use `@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")` on the
      `date` field to enforce a specific date format for serialisation and deserialisation.

- **Accessors and Mutators:**
    - Use the `@Data` annotation from Lombok to automatically generate `getters` and `setters` for all attributes,
      facilitating data manipulation.

- **Serializable Interface:**
    - Ensure the entity implements the `Serializable` interface to enable object serialisation for transmission or
      storage.

- **Constructor:**
    - Use the `@RequiredArgsConstructor` annotation to automatically generate a constructor with required fields.

- **Equals and HashCode:**
    - Ensure consistent object comparison and hash code generation by relying on the `@Data` annotation, which
      automatically implements `equals()` and `hashCode()` methods.

---

## Requirements for `Transfer` Entity Class:

- **Entity Mapping:**
    - Define the `Transfer` class as a MongoDB document to represent a database collection;
    - Annotate the class with `@Document(collection = "transfer")` to map the entity to the database collection named
      `transfer`.

- **Attributes and Annotations:**
    - Declare attributes `id`, `accountFrom`, `accountTo`, `dateTime`, `value`, and `operation` to represent the
      respective database fields;
    - Annotate the `id` field with `@Id` to specify it as the primary key;
    - Annotate the `operation` field with `@Field(name = "operation")` to map it to the respective database field with a
      custom name;
    - Apply `@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")` on
      the `dateTime` field to enforce a specific date format for serialisation and deserialisation;
    - Annotate the fields `accountFrom`, `accountTo`, `dateTime`, `value`, and `operation` with `@NonNull` to ensure
      non-null values where required.

- **Accessors and Mutators:**
    - Use the `@Data` annotation from Lombok to automatically generate `getters` and `setters` for all attributes,
      facilitating data manipulation.

- **Serializable Interface:**
    - Ensure the entity implements the `Serializable` interface to enable object serialisation for transmission or
      storage.

- **Constructors:**
    - Use the `@RequiredArgsConstructor` annotation to automatically generate a constructor with required fields;
    - Use the `@NoArgsConstructor` annotation to generate a default, parameterless constructor.

- **Equals and HashCode:**
    - Ensure consistent object comparison and hash code generation by relying on the `@Data` annotation, which
      automatically implements `equals()` and `hashCode()` methods.

---

## Requirements for `Operation` Enum:

- **Class Definition:**
    - Define the `Operation` class as an enumeration to represent a fixed set of operations in the system.

- **Implementation:**
    - Implement the `Serializable` interface to enable serialisation for transmission or storage.

- **Enum Values:**
    - Declare the following constants to represent various types of operations:
        - `Withdrawal`
        - `Deposit`
        - `Transfer_Sent`
        - `Transfer_Received`
        - `Pix_Sent`
        - `Pix_Received`

- **Usage:**
    - Use the `Operation` enum in the system's DTOs and entities to standardise and restrict the set of operation types.

---

## Requirements for `ResponseDTO` Class:

- **Attributes and Annotations:**
    - Declare attributes `data`, `message`, and `port` to represent the fields in the DTO;
    - Annotate `data` and `message` with `@JsonInclude(JsonInclude.Include.NON_NULL)` to ensure they are only included
      in the serialised output when not null.

- **Accessors and Mutators:**
    - Use the `@Data` annotation from Lombok to automatically generate `getters` and `setters` for all attributes.

- **Serializable Interface:**
    - Ensure the class implements the `Serializable` interface to enable object serialisation for transmission or
      storage.

- **Constructors:**
    - Use the `@NoArgsConstructor` and `@AllArgsConstructor` annotations to generate default and parameterised
      constructors respectively.

- **Builder Pattern:**
    - Use the `@Builder` annotation to enable fluent construction of `ResponseDTO` objects.

---

## Requirements for `AccountStatementDTO` Class:

- **Attributes and Annotations:**
    - Declare attributes `accountNumber`, `transactions`, and `balance` to represent the fields in the DTO.

- **Accessors and Mutators:**
    - Use the `@Data` annotation from Lombok to automatically generate `getters` and `setters` for all attributes.

- **Serializable Interface:**
    - Ensure the class implements the `Serializable` interface to enable object serialisation for transmission or
      storage.

- **Custom Methods:**
    - Implement the `calculateBalance()` method to compute the `balance` field based on the list of transactions.

---

## Requirements for `TransactionDTO` Class:

- **Attributes and Annotations:**
    - Declare attributes `dateTime`, `operation`, and `value` to represent the fields in the DTO.

- **Accessors and Mutators:**
    - Use the `@Data` annotation from Lombok to automatically generate `getters` and `setters` for all attributes.

- **Serializable Interface:**
    - Ensure the class implements the `Serializable` interface to enable object serialisation for transmission or
      storage.

---

## Requirements for `TransactionRequestDTO` Class:

- **Attributes and Annotations:**
    - Declare attributes `accountNumber`, `operation`, and `value` to represent the fields in the DTO.

- **Accessors and Mutators:**
    - Use the `@Data` annotation from Lombok to automatically generate `getters` and `setters` for all attributes.

- **Serializable Interface:**
    - Ensure the class implements the `Serializable` interface to enable object serialisation for transmission or
      storage.

---

## Requirements for `TransferRequestDTO` Class:

- **Attributes and Annotations:**
    - Declare attributes `accountFrom`, `accountTo`, `dateTime`, and `value` to represent the fields in the DTO.

- **Accessors and Mutators:**
    - Use the `@Data` annotation from Lombok to automatically generate `getters` and `setters` for all attributes.

- **Serializable Interface:**
    - Ensure the class implements the `Serializable` interface to enable object serialisation for transmission or
      storage.

- **Constructors:**
    - Use the `@NoArgsConstructor` and `@AllArgsConstructor` annotations to generate default and parameterised
      constructors respectively.

---

## Requirements for `TransferResponseDTO` Class:

- **Inheritance:**
    - Extend the `TransactionDTO` class to inherit its attributes and methods.

- **Attributes and Annotations:**
    - Declare additional attributes `accountFrom` and `accountTo` to represent the respective fields in the DTO.

- **Accessors and Mutators:**
    - Use the `@Data` annotation from Lombok to automatically generate `getters` and `setters` for all attributes.

- **Serializable Interface:**
    - Ensure the class implements the `Serializable` interface to enable object serialisation for transmission or
      storage.

- **Equals and HashCode:**
    - Use the `@EqualsAndHashCode(callSuper = true)` annotation to include inherited fields from the superclass in
      `equals()` and `hashCode()` implementations.

---

## Requirements for `TransactionRepository` Interface:

- **Repository Interface:**
    - Define the `TransactionRepository` interface to manage `Transaction` entities in the MongoDB database;
    - Extend `MongoRepository<Transaction, String>` to leverage Spring Data MongoDB's CRUD operations.

- **Custom Query Methods:**
    - Implement the method `List<Transaction> getTransactionsByAccountNumber(String accountFrom)` to retrieve all
      transactions associated with a specific account number.

- **Annotations:**
    - Annotate the interface with `@Repository` to indicate it as a Spring component for persistence operations.

---

## Requirements for `TransferRepository` Interface:

- **Repository Interface:**
    - Define the `TransferRepository` interface to manage `Transfer` entities in the MongoDB database;
    - Extend `MongoRepository<Transfer, String>` to leverage Spring Data MongoDB's CRUD operations.

- **Custom Query Methods:**
    - Implement the method `List<Transfer> getTransfersByAccountFrom(String accountNumber)` to retrieve transfers based
      on the account number, supporting both `accountFrom` and `accountTo` fields;
    - Use the `@Query` annotation to specify the query `{$or: [{'accountFrom': ?0}, {'accountTo': ?0}]}` for fetching
      relevant transfers.

- **Annotations:**
    - Annotate the interface with `@Repository` to indicate it as a Spring component for persistence operations.

---

## Requirements for `AccountValidationService` Class:

- **Class Definition and Scope:**
    - Annotate the class with `@Service` to indicate it as a Spring Service component;
    - Annotate with `@Scope("singleton")` to enforce a singleton scope for the service.

- **Attributes and Constructor:**
    - Declare the attribute `accountRequest` as a final dependency;
    - Use constructor injection to initialise the `accountRequest` field.

- **Methods:**
    - Implement the `verifyAccount(String accountNumber)` method to validate account existence via the `AccountRequest`
      service:
        - Fetch account details using `accountRequest.getUserAccount(accountNumber)`;
        - Log the port information from the response;
        - Throw an `InvalidCustomerAccountException` if the fetched data is null;
        - Return `true` upon successful verification.
    - Implement the `accountsAreValid(String accountFrom, String accountTo)` method to validate two accounts:
        - Ensure both `accountFrom` and `accountTo` are valid by invoking `verifyAccount()`.

---

## Requirements for `BaseService` Class:

- **Class Definition:**
    - Annotate the class with `@Service` to indicate it as a Spring Service component.

- **Attributes:**
    - Declare and initialise the `mapper` attribute as a `ModelMapper` instance.

- **Constructors:**
    - Provide a default constructor to initialise the `mapper` field.

---

## Requirements for `TransactionService` Class:

- **Class Definition:**
    - Annotate the class with `@Service` to indicate it as a Spring Service component;
    - Extend the `BaseService` class to reuse its functionality.

- **Dependencies and Constructor Injection:**
    - Autowire the `TransactionRepository` and `TransferService` dependencies for database and transfer-related
      operations.

- **Transactional Methods:**
    - Implement the `create(TransactionRequestDTO dto)` method to persist a new transaction:
        - Map the DTO to a `Transaction` entity;
        - Set the current UTC date and time for the transaction;
        - Save the transaction and return a mapped `TransactionDTO`.
    - Implement the `getAccountStatement(String accountNumber)` method to fetch account statements:
        - Retrieve all transactions for the account and calculate the balance.
    - Implement the `getAllTransactions(String accountNumber)` private method to combine direct transactions and
      transfers:
        - Call the helper methods `getTransactions()` and `getTransfers()`.
    - Implement the `getTransactions(String accountNumber)` private method:
        - Fetch transactions using `transactionRepository.getTransactionsByAccountNumber()` and map them to DTOs.
    - Implement the `getTransfers(String accountNumber)` private method:
        - Fetch transfers using `transferService.getAllByAccountNumber()` and map them to DTOs.

---

## Requirements for `TransferService` Class:

- **Class Definition:**
    - Annotate the class with `@Service` to indicate it as a Spring Service component;
    - Extend the `BaseService` class to reuse its functionality.

- **Dependencies and Constructor Injection:**
    - Autowire the `TransferRepository` and `AccountValidationService` dependencies for database and validation-related
      operations.

- **Transactional Methods:**
    - Implement the `processTransfer(TransferRequestDTO dto, AccountStatementDTO accountStatementDto)` method:
        - Validate the accounts involved in the transfer using `AccountValidationService`;
        - Verify the balance in the `AccountStatementDTO` is sufficient;
        - Map the request DTO to a `Transfer` entity, set the current UTC date and the appropriate operation type;
        - Save the transfer and map it to a `TransferResponseDTO`.
    - Implement the `getAllByAccountNumber(String accountNumber)` method to fetch all transfers for an account:
        - Fetch transfers using `transferRepository.getTransfersByAccountFrom()`;
        - Map the transfers to DTOs and adjust operations based on the account's role (`Transfer_Sent` or
          `Transfer_Received`).

---

## Requirements for `TransactionController` Class:

- **Class Definition:**
    - Annotate the class with `@RestController` to define it as a RESTful controller;
    - Use `@RequestMapping(value = "/transactions")` to map the base URL path for transaction-related endpoints;
    - Extend `BaseController` to inherit common response handling methods.

- **Dependencies and Injection:**
    - Autowire `TransactionService` and `AccountValidationService` for transactional operations and account validation.

- **Endpoints:**
    - Implement a `POST` method to create a new transaction:
        - Use `@PostMapping()` to map the endpoint;
        - Validate the account using `AccountValidationService.verifyAccount()`;
        - Pass the `TransactionRequestDTO` to `TransactionService.create()`;
        - Return a success response with `HttpStatus.CREATED`.
    - Implement a `GET` method to retrieve account statements:
        - Use `@GetMapping("/{accountNumber}")` to map the endpoint;
        - Validate the account using `AccountValidationService.verifyAccount()`;
        - Retrieve the statement using `TransactionService.getAccountStatement()`;
        - Return a success response with `HttpStatus.OK`.

---

## Requirements for `TransferController` Class:

- **Class Definition:**
    - Annotate the class with `@RestController` to define it as a RESTful controller;
    - Use `@RequestMapping("/transfers")` to map the base URL path for transfer-related endpoints;
    - Extend `BaseController` to inherit common response handling methods.

- **Dependencies and Injection:**
    - Autowire `TransferService` and `TransactionService` for transfer and transactional operations.

- **Endpoints:**
    - Implement a `POST` method to create a new transfer:
        - Use `@PostMapping()` to map the endpoint;
        - Fetch account statement using `TransactionService.getAccountStatement()`;
        - Process the transfer using `TransferService.processTransfer()`;
        - Return a success response with `HttpStatus.CREATED` if the transfer is successful;
        - Return an error response if the balance is insufficient.
    - Implement a `GET` method to retrieve transfer history:
        - Use `@GetMapping("{accountNumber}")` to map the endpoint;
        - Retrieve the transfer history using `TransferService.getAllByAccountNumber()`;
        - Return a success response if transfers are found, or an error response if no transfers are available.

---

## Requirements for `BaseController` Class:

- **Class Definition:**
    - Define the class as a base controller for common response handling across other controllers.

- **Dependencies and Injection:**
    - Autowire the `Environment` dependency to access application properties.

- **Methods:**
    - Implement `getPort()` to fetch the server's running port from application properties.
    - Implement `getResponseError(String message, HttpStatus status)` to create an error response including the server
      port.
    - Implement `getResponseSuccess(Object data, String message, HttpStatus status)` to create a success response with
      data, a message, and the server port.
    - Implement `getResponseSuccess(Object data, HttpStatus status)` to create a success response with data and the
      server port.

---

## Requirements for `ResilienceTestController` Class:

- **Class Definition:**
    - Annotate the class with `@RestController` to define it as a RESTful controller;
    - Use `@RequestMapping("resilience")` to map the base URL path for resilience testing endpoints;
    - Extend `BaseController` to inherit common response handling methods.

- **Dependencies and Constructor Injection:**
    - Define a `RestTemplate` dependency for making HTTP requests;
    - Inject the `RestTemplate` dependency through the constructor.

- **Logging:**
    - Use `LoggerFactory.getLogger()` to instantiate a logger for logging resilience-related events.

- **Endpoints:**
    - Implement a `GET` method to simulate resilience behaviour:
        - Use `@GetMapping` to map the endpoint;
        - Annotate with `@Retry(name = "account", fallbackMethod = "fallbackMethod")` to enable retry mechanism and
          fallback for failures;
        - Make an HTTP call to another service using `RestTemplate.getForEntity()`;
        - Log the receipt of the request using the logger.
    - Implement a `fallbackMethod(Exception error)` method to handle errors:
        - Return an error response with a "Service Unavailable" message and `HttpStatus.OK`.

---

## Requirements for `GlobalExceptionHandler` Class:

- **Class Definition:**
    - Annotate the class with `@RestController` and `@ControllerAdvice` to define it as a global exception handler for
      REST controllers.

- **Dependencies and Constructor:**
    - Declare the `Environment` dependency to access environment properties;
    - Use constructor injection to initialise the `Environment` field.

- **Methods:**
    - Implement `getPort()` to fetch the server's running port from application properties.
    - Implement `handleGenericException(Exception ex)` to handle general exceptions:
        - Annotate with `@ExceptionHandler(Exception.class)`;
        - Return a `ResponseEntity` with `HttpStatus.INTERNAL_SERVER_ERROR` and a `ResponseDTO` containing the
          exception's message and the server port.
    - Implement `handleInvalidCustomerAccountException(InvalidCustomerAccountException ex)` to handle specific
      exceptions:
        - Annotate with `@ExceptionHandler(InvalidCustomerAccountException.class)`;
        - Return a `ResponseEntity` with `HttpStatus.NOT_FOUND` and a `ResponseDTO` containing the exception's message
          and the server port.

---

## Requirements for `InvalidCustomerAccountException` Class:

- **Class Definition:**
    - Define the class as a custom exception extending `RuntimeException`.

- **Constructors:**
    - Provide a constructor to initialise the exception with a custom error message.

---

## Requirements for `AccountRequest` Interface:

- **Class Definition:**
    - Annotate the interface with `@FeignClient(name = "account-service")` to enable communication with the account
      service via Feign.

- **Endpoints:**
    - Define a `GET` endpoint method `ResponseDTO getUserAccount(String account)`:
        - Use `@GetMapping("/customers/validate/{account}")` to map the endpoint;
        - Use `@PathVariable` to extract the account parameter from the URL.

---

## Requirements for `BeanConfig` Class:

- **Class Definition:**
    - Annotate the class with `@Configuration` to define it as a configuration class for Spring beans.

- **Bean Definitions:**
    - Provide a `@Bean` method to create and return a `ModelMapper` instance.
    - Provide a `@Bean` method to create and return a `RestTemplate` instance.

---

## Requirements for `ResilienceMessages` Utility Class:

- **Class Definition:**
    - Define the class as `final` to prevent extension;
    - Include a private constructor to prevent instantiation;
    - Throw an `UnsupportedOperationException` if instantiation is attempted.

- **Constants:**
    - Define the following `public static final` string constants:
        - `REQUEST_RECEIVED` with the value `"Request received!"`;
        - `SERVICE_UNAVAILABLE` with the value `"Service unavailable."`.

---

## Requirements for `TransactionMessages` Utility Class:

- **Class Definition:**
    - Define the class as `final` to prevent extension;
    - Include a private constructor to prevent instantiation;
    - Throw an `IllegalStateException` if instantiation is attempted.

- **Constants:**
    - Define the following `public static final` string constants:
        - `TRANSACTION_REGISTRATION` with the value `" successfully registered!"`.

---

## Requirements for `TransferMessages` Utility Class:

- **Class Definition:**
    - Define the class as `final` to prevent extension;
    - Include a private constructor to prevent instantiation;
    - Throw an `UnsupportedOperationException` if instantiation is attempted.

- **Constants:**
    - Define the following `public static final` string constants:
        - `INSUFFICIENT_BALANCE` with the value `"Insufficient funds available."`;
        - `NO_TRANSFERS_FOUND` with the value `"No transfer records found for the specified account."`.

---