package souza.souza.charles.transactionservice.services;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import souza.souza.charles.transactionservice.dtos.ResponseDTO;
import souza.souza.charles.transactionservice.exceptions.InvalidUserAccountException;
import souza.souza.charles.transactionservice.request.AccountRequest;

import java.io.Serializable;
import java.util.Objects;

@Service
@Scope("singleton")
public class AccountValidationService implements Serializable {

    private final AccountRequest accountRequest;

    public AccountValidationService(AccountRequest accountRequest) {
        this.accountRequest = accountRequest;
    }

    public boolean verifyAccount(String accountNumber) {
        ResponseDTO responseDTO = accountRequest.getUserAccount(accountNumber);

        if (Objects.isNull(responseDTO.getData()))
            throw new InvalidUserAccountException(responseDTO.getMessage());

        return true;
    }

    public boolean accountsAreValid(String accountFrom, String accountTo) {
        if (verifyAccount(accountFrom) && verifyAccount(accountTo))
            return true;

        return false;
    }
}