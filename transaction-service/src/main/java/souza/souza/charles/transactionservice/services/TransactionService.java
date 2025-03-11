package souza.souza.charles.transactionservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import souza.souza.charles.transactionservice.dtos.TransactionRequestDTO;
import souza.souza.charles.transactionservice.entities.Transaction;
import souza.souza.charles.transactionservice.repositories.TransactionRepository;


import java.io.Serializable;
import java.time.LocalDateTime;

@Service
public class TransactionService extends BaseService implements Serializable {

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public Object save(TransactionRequestDTO dto){
        Transaction newTransaction = mapper.map(dto, Transaction.class);
        newTransaction.setDate(LocalDateTime.now());
        return transactionRepository.save(newTransaction);
    }
}