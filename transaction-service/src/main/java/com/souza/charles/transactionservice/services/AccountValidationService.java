package com.souza.charles.transactionservice.services;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.souza.charles.transactionservice.dtos.ResponseDTO;
import com.souza.charles.transactionservice.exceptions.InvalidCustomerAccountException;
import com.souza.charles.transactionservice.request.AccountRequest;

import java.util.Objects;

@Service
@Scope("singleton")
public class AccountValidationService {

    private final AccountRequest accountRequest;

    public AccountValidationService(AccountRequest accountRequest) {
        this.accountRequest = accountRequest;
    }

    public boolean verifyAccount(String accountNumber) {
        ResponseDTO responseDTO = accountRequest.getUserAccount(accountNumber);

        if (Objects.isNull(responseDTO.getData()))
            throw new InvalidCustomerAccountException(responseDTO.getMessage());

        return true;
    }

    public boolean accountsAreValid(String accountFrom, String accountTo) {
        return verifyAccount(accountFrom) && verifyAccount(accountTo);
    }
}