package com.souza.charles.transactionservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import com.souza.charles.transactionservice.entities.Transfer;

import java.util.List;

@Repository
public interface TransferRepository extends MongoRepository<Transfer, String> {

    @Query("{$or: [{'accountFrom': ?0}, {'accountTo': ?0}]}")
    List<Transfer> getTransfersByAccountFrom(String accountNumber);
}