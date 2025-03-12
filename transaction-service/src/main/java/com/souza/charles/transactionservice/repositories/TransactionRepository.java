package com.souza.charles.transactionservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.souza.charles.transactionservice.entities.Transaction;


import java.util.List;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {

    List<Transaction> getTransactionsByAccountNumber(String accountFrom);
}