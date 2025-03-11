package souza.souza.charles.transactionservice.services;

import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import souza.souza.charles.transactionservice.dtos.AccountStatementDTO;
import souza.souza.charles.transactionservice.dtos.TransactionDTO;
import souza.souza.charles.transactionservice.dtos.TransactionRequestDTO;
import souza.souza.charles.transactionservice.dtos.TransferResponseDTO;
import souza.souza.charles.transactionservice.entities.Transaction;
import souza.souza.charles.transactionservice.repositories.TransactionRepository;


import java.io.Serializable;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService extends BaseService implements Serializable {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransferService transferService;

    @Transactional
    public Object create(TransactionRequestDTO dto) {
        Transaction newTransaction = mapper.map(dto, Transaction.class);
        newTransaction.setDate(LocalDateTime.now((ZoneId.of("UTC"))));
        return transactionRepository.save(newTransaction);
    }

    @Transactional(readOnly = true)
    public AccountStatementDTO getAccountStatement(String accountNumber) {
        AccountStatementDTO dto = new AccountStatementDTO();
        dto.setAccountNumber(accountNumber);
        dto.setTransactions(getAllTransactions(accountNumber));
        dto.calculateBalance();

        return dto;
    }

    private List<TransactionDTO> getAllTransactions(String accountNumber) {
        List<TransactionDTO> transactions = new ArrayList<>();
        transactions.addAll(getTransactions(accountNumber));
        transactions.addAll(getTransfers(accountNumber));
        return transactions;
    }

    private List<TransactionDTO> getTransactions(String accountNumber) {
        var transactions = transactionRepository.getTransactionsByAccountNumber(accountNumber);

        Type listType = new TypeToken<List<TransactionDTO>>() {
        }.getType();
        return mapper.map(transactions, listType);
    }

    private List<TransactionDTO> getTransfers(String accountNumber) {
        var transfers = transferService.getAllByAccountNumber(accountNumber);
        Type listType = new TypeToken<List<TransferResponseDTO>>() {
        }.getType();
        return mapper.map(transfers, listType);
    }
}