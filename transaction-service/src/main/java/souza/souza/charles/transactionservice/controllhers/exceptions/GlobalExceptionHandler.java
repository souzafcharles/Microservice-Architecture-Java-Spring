package souza.souza.charles.transactionservice.controllhers.exceptions;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import souza.souza.charles.transactionservice.dtos.ResponseDTO;
import souza.souza.charles.transactionservice.exceptions.InvalidCustomerAccountException;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    private final Environment environment;

    public GlobalExceptionHandler(Environment environment) {
        this.environment = environment;
    }

    private String getPort() {
        return environment.getProperty("local.server.port");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseDTO.builder()
                        .message(ex.getMessage())
                        .port(getPort())
                        .build());
    }

    @ExceptionHandler(InvalidCustomerAccountException.class)
    public ResponseEntity<ResponseDTO> handleInvalidCustomerAccountException(InvalidCustomerAccountException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ResponseDTO.builder()
                        .message(ex.getMessage())
                        .port(getPort())
                        .build());
    }

}