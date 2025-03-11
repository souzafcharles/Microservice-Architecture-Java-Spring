package souza.souza.charles.transactionservice.controllhers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import souza.souza.charles.transactionservice.dtos.TransactionRequestDTO;
import souza.souza.charles.transactionservice.services.AccountValidationService;
import souza.souza.charles.transactionservice.services.TransactionService;
import souza.souza.charles.transactionservice.utils.TransactionMessages;

import java.io.Serializable;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionController extends BaseController implements Serializable {

    @Autowired
    private TransactionService transactionService;

    private final AccountValidationService accountValidationService;

    public TransactionController(AccountValidationService accountValidationService) {
        this.accountValidationService = accountValidationService;
    }

    @PostMapping()
    public ResponseEntity create(@RequestBody TransactionRequestDTO transactionRequestDTO) {
        accountValidationService.verifyAccount(transactionRequestDTO.getAccountNumber());
        String message = transactionRequestDTO.getOperation() + TransactionMessages.TRANSACTION_REGISTRATION;
        return getResponseSuccess(transactionService.create(transactionRequestDTO), message, HttpStatus.CREATED);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity statement(@PathVariable String accountNumber) {
        accountValidationService.verifyAccount(accountNumber);
        return getResponseSuccess(transactionService.getAccountStatement(accountNumber), HttpStatus.OK);
    }
}