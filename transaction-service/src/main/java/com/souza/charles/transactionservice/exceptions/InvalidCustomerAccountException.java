package com.souza.charles.transactionservice.exceptions;

public class InvalidCustomerAccountException extends RuntimeException {
    public InvalidCustomerAccountException(String message) {
        super(message);
    }
}