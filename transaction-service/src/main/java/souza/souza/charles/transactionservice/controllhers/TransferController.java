package souza.souza.charles.transactionservice.controllhers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import souza.souza.charles.transactionservice.dtos.AccountStatementDTO;
import souza.souza.charles.transactionservice.dtos.TransferRequestDTO;
import souza.souza.charles.transactionservice.services.AccountValidationService;
import souza.souza.charles.transactionservice.services.TransactionService;
import souza.souza.charles.transactionservice.services.TransferService;
import souza.souza.charles.transactionservice.utils.TransferMessages;

import java.io.Serializable;

@RestController
@RequestMapping("/transfers")
public class TransferController extends BaseController implements Serializable {

    @Autowired
    private TransferService transferService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private AccountValidationService accountValidationService;

    @PostMapping()
    public ResponseEntity create(@RequestBody TransferRequestDTO dto) {
        accountValidationService.accountsAreValid(dto.getAccountFrom(), dto.getAccountTo());
        return getResponseEntity(dto);
    }

    @GetMapping("{accountNumber}")
    public ResponseEntity getAllByAccountNumber(@PathVariable String accountNumber){
        var transferHistory = transferService.getAllByAccountNumber(accountNumber);
        if(transferHistory.size() > 0){
            return getResponseSuccess(transferHistory, HttpStatus.OK);
        }
        return getResponseError(TransferMessages.NO_TRANSFERS_FOUND, HttpStatus.OK);
    }

    private ResponseEntity getResponseEntity(TransferRequestDTO transferRequestDto){
        AccountStatementDTO accountStatementDto = transactionService
                .getAccountStatement(transferRequestDto.getAccountFrom());
        int currentBalance = accountStatementDto.getBalance().compareTo(transferRequestDto.getValue());
        if(currentBalance > 0 || currentBalance == 0){
            var response = transferService.create(transferRequestDto);
            return getResponseSuccess(response, HttpStatus.CREATED);
        } else {
            return getResponseError(TransferMessages.INSUFFICIENT_BALANCE, HttpStatus.OK);
        }
    }
}