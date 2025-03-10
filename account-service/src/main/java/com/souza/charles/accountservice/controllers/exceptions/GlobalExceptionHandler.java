package com.souza.charles.accountservice.controllers.exceptions;

import com.souza.charles.accountservice.dtos.ResponseDTO;
import com.souza.charles.accountservice.exceptions.InvalidAccountException;
import com.souza.charles.accountservice.exceptions.CustomerNotFoundException;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

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

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleCustomerNotFoundException(CustomerNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ResponseDTO.builder()
                        .message(ex.getMessage())
                        .port(getPort())
                        .build());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseDTO> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ResponseDTO.builder()
                        .message("Cannot delete customer due to related data.")
                        .port(getPort())
                        .build());

    }

    @ExceptionHandler(InvalidAccountException.class)
    public ResponseEntity<ResponseDTO> handleInvalidAccountException(InvalidAccountException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ResponseDTO.builder()
                        .message(ex.getMessage())
                        .port(getPort())
                        .build());
    }

}