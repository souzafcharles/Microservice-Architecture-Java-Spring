package com.souza.charles.transactionservice.services;

import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.souza.charles.transactionservice.dtos.AccountStatementDTO;
import com.souza.charles.transactionservice.dtos.TransactionDTO;
import com.souza.charles.transactionservice.dtos.TransactionRequestDTO;
import com.souza.charles.transactionservice.dtos.TransferResponseDTO;
import com.souza.charles.transactionservice.entities.Transaction;
import com.souza.charles.transactionservice.repositories.TransactionRepository;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService extends BaseService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransferService transferService;

    @Transactional
    public TransactionDTO create(TransactionRequestDTO dto) {
        Transaction newTransaction = mapper.map(dto, Transaction.class);
        newTransaction.setDate(LocalDateTime.now((ZoneId.of("UTC"))));
        return mapper.map(transactionRepository.save(newTransaction), TransactionDTO.class);
    }

    @Transactional(readOnly = true)
    public AccountStatementDTO getAccountStatement(String accountNumber) {
        AccountStatementDTO dto = new AccountStatementDTO();
        dto.setAccountNumber(accountNumber);
        dto.setTransactions(getAllTransactions(accountNumber));
        dto.calculateBalance();
        return dto;
    }

    @Transactional(readOnly = true)
    private List < TransactionDTO > getAllTransactions(String accountNumber) {
        List < TransactionDTO > transactions = new ArrayList < > ();
        transactions.addAll(getTransactions(accountNumber));
        transactions.addAll(getTransfers(accountNumber));
        return transactions;
    }

    @Transactional(readOnly = true)
    private List < TransactionDTO > getTransactions(String accountNumber) {
        var transactions = transactionRepository.getTransactionsByAccountNumber(accountNumber);
        Type listType = new TypeToken < List < TransactionDTO >> () {}.getType();
        return mapper.map(transactions, listType);
    }

    @Transactional(readOnly = true)
    private List < TransactionDTO > getTransfers(String accountNumber) {
        var transfers = transferService.getAllByAccountNumber(accountNumber);
        Type listType = new TypeToken < List < TransferResponseDTO >> () {}.getType();
        return mapper.map(transfers, listType);
    }
}