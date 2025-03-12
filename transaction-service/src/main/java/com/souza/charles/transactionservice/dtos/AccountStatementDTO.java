package com.souza.charles.transactionservice.dtos;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class AccountStatementDTO implements Serializable {

    private String accountNumber;
    private List<TransactionDTO> transactions;
    private BigDecimal balance;

    public void calculateBalance(){
        this.balance = BigDecimal.ZERO;
        for(TransactionDTO transaction : transactions){
            this.balance = balance.add(transaction.getValue());
        }
    }
}