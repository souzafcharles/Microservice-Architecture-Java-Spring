package souza.souza.charles.transactionservice.controllhers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import souza.souza.charles.transactionservice.dtos.TransactionRequestDTO;
import souza.souza.charles.transactionservice.services.TransactionService;
import souza.souza.charles.transactionservice.utils.TransactionMessages;

import java.io.Serializable;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionController extends BaseController implements Serializable {

    @Autowired
    private TransactionService transactionService;

    @PostMapping()
    public ResponseEntity create(@RequestBody TransactionRequestDTO transactionRequestDTO) {
        String message = transactionRequestDTO.getOperation() + TransactionMessages.TRANSACTION_REGISTRATION;
        return getResponseSuccess(transactionService.create(transactionRequestDTO), message, HttpStatus.CREATED);
    }
}